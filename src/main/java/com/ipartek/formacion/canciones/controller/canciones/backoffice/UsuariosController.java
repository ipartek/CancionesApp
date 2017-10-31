package com.ipartek.formacion.canciones.controller.canciones.backoffice;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipartek.formacion.canciones.modelo.dao.UsuarioDAO;
import com.ipartek.formacion.canciones.modelo.pojo.Usuario;

/**
 * Servlet implementation class CancionesController
 */
@WebServlet("/usuarios")
public class UsuariosController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private int accion = Acciones.LISTAR;
	private UsuarioDAO dao;

	private String msg; // Mensaje para el usuario
	private String view; // vista para el forward
	private static final String VIEW_INDEX = "usuarios/index.jsp";
	private static final String VIEW_FORM = "usuarios/form.jsp";

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		dao = UsuarioDAO.getInstance();
	}

	@Override
	public void destroy() {
		super.destroy();
		dao = null;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	private void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			// determinar la accion a realizar
			accion = (request.getParameter("accion") != null) ? Integer.parseInt(request.getParameter("accion"))
					: Acciones.LISTAR;

			switch (accion) {
			case Acciones.LISTAR:
				listar(request);
				break;
			case Acciones.ELIMINAR:
				eliminar(request);
				break;
			case Acciones.MOSTRAR_FORMULARIO:
				mostrarFormulario(request);
				break;
			case Acciones.CREAR_MODIFICAR:
				crearModificar(request);
				break;
			case Acciones.LOGIN:
				login(request);
				break;
			// TODO resto de acciones

			default:
				break;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			request.setAttribute("msg", msg);
			request.getRequestDispatcher(view).forward(request, response);
		}

	}

	private void login(HttpServletRequest request) {

		String usuario = request.getParameter("nombre");
		String password = request.getParameter("password");

		request.setAttribute("usuario", dao.findByUserPwd(usuario, password));
		view = VIEW_INDEX;

	}

	private void crearModificar(HttpServletRequest request) {
		// recoger parametros formulario
		int id = Integer.parseInt(request.getParameter("id"));
		String nombre = request.getParameter("nombre");
		String pass = request.getParameter("password");
		String email = request.getParameter("email");
		String avatar = request.getParameter("avatar");

		Usuario usuario = new Usuario(id, nombre, pass, email, avatar);
		if (id == -1) {
			if (dao.create(usuario)) {
				msg = "Usuario Creado con exito";
				listar(request);
			} else {
				msg = "No se puede crear Canción";
				request.setAttribute("usuario", usuario);
				view = VIEW_FORM;
			}
		} else {
			if (dao.update(usuario, id)) {
				msg = "Usuario Modificado con exito";
				listar(request);
			} else {
				msg = "No se puede Modifir la canción";
				request.setAttribute("usuario", usuario);
				view = VIEW_FORM;
			}
		}

	}

	private void mostrarFormulario(HttpServletRequest request) {

		Usuario usuario = null;
		if (request.getParameter("id") == null) {
			usuario = new Usuario();
		} else {
			int id = Integer.parseInt(request.getParameter("id"));
			usuario = dao.findById(id);
		}

		request.setAttribute("usuario", usuario);
		view = VIEW_FORM;
	}

	private void eliminar(HttpServletRequest request) {

		int id = Integer.parseInt(request.getParameter("id"));

		if (dao.delete(id)) {
			msg = "Eliminada con Exito la Cancion(" + id + ")";
		} else {
			msg = "NO se Eliminó la Cancion(" + id + ")";
		}

		listar(request);
	}

	private void listar(HttpServletRequest request) {
		request.setAttribute("usuarios", dao.findAll());
		view = VIEW_INDEX;
	}

}

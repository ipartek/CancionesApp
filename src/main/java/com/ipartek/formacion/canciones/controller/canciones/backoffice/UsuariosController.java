package com.ipartek.formacion.canciones.controller.canciones.backoffice;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipartek.formacion.canciones.controller.canciones.Alert;
import com.ipartek.formacion.canciones.modelo.dao.UsuarioDAO;
import com.ipartek.formacion.canciones.modelo.pojo.Usuario;

/**
 * Servlet implementation class UsuariosController
 */
@WebServlet("/usuarios")
public class UsuariosController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private int accion = Acciones.LISTAR;
	private UsuarioDAO dao;

	private Alert alert; // Alertas para el usuario
	private String view; // Vista para el forward
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

			alert = null;
			// Determinar la cancion a realizar
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

			case Acciones.BUSCAR:
				buscar(request);
				break;

			default:
				break;
			}

		} catch (Exception e) {

		} finally {
			request.setAttribute("alert", alert);
			request.getRequestDispatcher(view).forward(request, response);
		}

	}

	private void buscar(HttpServletRequest request) {
		// TODO Auto-generated method stub

	}

	private void crearModificar(HttpServletRequest request) {
		// recoger parametros
		int id = Integer.parseInt(request.getParameter("id"));
		String nombre = request.getParameter("nombre");
		String pass = request.getParameter("pass");
		String email = request.getParameter("email");
		String avatar = request.getParameter("avatar");

		Usuario usuario = new Usuario(id, nombre, pass, email, avatar);

		if (id == -1) {
			if (dao.create(usuario)) {
				alert = new Alert(Alert.TIPO_SUCCESS, "Usuario creado con exito");
				listar(request);
			} else {
				alert = new Alert(Alert.TIPO_DANGER, "No se puede crear el usuario");
				request.setAttribute("usuario", usuario);
				view = VIEW_FORM;
			}
		} else {
			if (dao.update(usuario, id)) {
				alert = new Alert(Alert.TIPO_SUCCESS, "Usuario modificado con exito");
				listar(request);
			} else {
				alert = new Alert(Alert.TIPO_DANGER, "No se ha podido modificar el usuario");
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
			alert = new Alert(Alert.TIPO_SUCCESS, "El usuario(" + id + ") ha sido eliminado con exito");
		} else {
			alert = new Alert(Alert.TIPO_DANGER, "NO se ha podido eliminar el usuario(" + id + ")");
		}
		listar(request);

	}

	private void listar(HttpServletRequest request) {
		request.setAttribute("usuarios", dao.findAll());
		view = VIEW_INDEX;

	}

}
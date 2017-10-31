package com.ipartek.formacion.canciones.controller.canciones.backoffice;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipartek.formacion.canciones.excepciones.CancionException;
import com.ipartek.formacion.canciones.modelo.dao.CancionDAO;
import com.ipartek.formacion.canciones.modelo.pojo.Cancion;

/**
 * Servlet implementation class CancionesController
 */
@WebServlet("/backoffice/canciones")
public class CancionesController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private int accion = Acciones.LISTAR;
	private CancionDAO dao;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		dao = CancionDAO.getInstance();
	}

	private String msg; // mensaje para el usuario
	private String view; // Vista para el forward
	private static final String VIEW_INDEX = "canciones/index.jsp";
	private static final String VIEW_FORM = "canciones/form.jsp";

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
			e.printStackTrace();
		} finally {
			request.setAttribute("msg", msg);
			request.getRequestDispatcher(view).forward(request, response);
		}

	}

	/**
	 * Busca canciones por la coincidencia del nombre o el artista
	 *
	 * @param request
	 */
	private void buscar(HttpServletRequest request) {
		ArrayList<Cancion> canciones = new ArrayList<Cancion>();

		String busqueda = request.getParameter("busqueda");

		if ((dao.busqueda(request.getParameter("busqueda")) == null)) {
			msg = "No existe ese nombre en nuestra lista de canciones";
		} else {
			canciones = (ArrayList<Cancion>) dao.busqueda(busqueda);
		}
		request.setAttribute("listado", canciones);
		view = VIEW_INDEX;
	}

	private void crearModificar(HttpServletRequest request) {
		// recoger parametros
		int id = Integer.parseInt(request.getParameter("id"));
		String nombre = request.getParameter("nombre");
		String artista = request.getParameter("artista");
		String duracion = request.getParameter("duracion");
		String cover = request.getParameter("cover");

		Cancion cancion;
		try {
			cancion = new Cancion(id, nombre, artista, duracion, cover);

			if (id == -1) {
				if (dao.create(cancion)) {
					msg = "Cancion creada con exito";
					listar(request);
				} else {
					msg = "No se puede crear cancion";
					request.setAttribute("cancion", cancion);
					view = VIEW_FORM;
				}
			} else {
				if (dao.update(cancion, id)) {
					msg = "Cancion modificada con exito";
					listar(request);
				} else {
					msg = "No se puede modificar la cancion";
					request.setAttribute("cancion", cancion);
					view = VIEW_FORM;
				}
			}
		} catch (CancionException e) {
			msg = "El formato de la duracion " + duracion + " no es correcto";
			request.setAttribute("cancion", new Cancion(id, nombre, artista, cover));
			view = VIEW_FORM;
		}

	}

	private void mostrarFormulario(HttpServletRequest request) {

		Cancion cancion = null;

		if (request.getParameter("id") == null) {
			cancion = new Cancion();
		} else {
			int id = Integer.parseInt(request.getParameter("id"));
			cancion = dao.findById(id);
		}
		request.setAttribute("cancion", cancion);
		view = VIEW_FORM;

	}

	private void eliminar(HttpServletRequest request) {

		int id = Integer.parseInt(request.getParameter("id"));
		if (dao.delete(id)) {
			msg = "Eliminada con Éxito la Canción(" + id + ")";
		} else {
			msg = "NO se Eliminó la Canción(" + id + ")";
		}
		listar(request);

	}

	private void listar(HttpServletRequest request) {
		request.setAttribute("listado", dao.findAll());
		view = VIEW_INDEX;

	}

}

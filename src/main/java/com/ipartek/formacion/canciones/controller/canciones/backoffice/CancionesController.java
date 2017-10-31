package com.ipartek.formacion.canciones.controller.canciones.backoffice;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipartek.formacion.canciones.controller.canciones.Alert;
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

	private Alert alert; // Alertas para el usuario
	private String view; // vista para el forward
	private static final String VIEW_INDEX = "canciones/index.jsp";
	private static final String VIEW_FORM = "canciones/form.jsp";

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		dao = CancionDAO.getInstance();
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
			case Acciones.BUSCAR:
				buscar(request);
				break;

			default:
				break;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			request.setAttribute("alert", alert);
			request.getRequestDispatcher(view).forward(request, response);
		}

	}

	/**
	 * Busca Canciones por la coincidencia del 'nombre' o 'artista'
	 *
	 * @param request
	 */
	private void buscar(HttpServletRequest request) {
		String criterio = request.getParameter("criterio");
		request.setAttribute("listado", dao.findByNameOrArtist(criterio));
		view = VIEW_INDEX;

	}

	private void crearModificar(HttpServletRequest request) {
		// recoger parametros formulario
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
					alert = new Alert(Alert.TIPO_SUCCESS, "Canción Creada con exito");
					listar(request);
				} else {
					alert = new Alert(Alert.TIPO_DANGER, "No se puede crear Canción");
					request.setAttribute("cancion", cancion);
					view = VIEW_FORM;
				}
			} else {
				if (dao.update(cancion, id)) {
					alert = new Alert(Alert.TIPO_SUCCESS, "Canción Modificada con exito");
					listar(request);
				} else {
					alert = new Alert(Alert.TIPO_DANGER, "No se puede Modificar la canción");
					request.setAttribute("cancion", cancion);
					view = VIEW_FORM;
				}
			}

		} catch (CancionException e) {
			alert = new Alert(Alert.TIPO_DANGER, "El formato de la duracion " + duracion + " no es correcto");
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
			alert = new Alert(Alert.TIPO_SUCCESS, "Eliminada con Exito la Cancion(" + id + ")");
		} else {
			alert = new Alert(Alert.TIPO_DANGER, "NO se Eliminó la Cancion(" + id + ")");
		}

		listar(request);
	}

	private void listar(HttpServletRequest request) {
		request.setAttribute("listado", dao.findAll());
		view = VIEW_INDEX;
	}

}
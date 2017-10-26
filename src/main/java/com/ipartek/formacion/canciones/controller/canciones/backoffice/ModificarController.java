package com.ipartek.formacion.canciones.controller.canciones.backoffice;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipartek.formacion.canciones.modelo.ModeloCancionImpl;
import com.ipartek.formacion.canciones.pojo.Cancion;

/**
 * Servlet implementation class ModificarController
 */
@WebServlet("/backoffice/modificar")
public class ModificarController extends HttpServlet {
	private static final long serialVersionUID = 1L;


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ModeloCancionImpl model = ModeloCancionImpl.getInstance();
		String mensaje = "";
		String id = request.getParameter("id");
		
		if(id.equals(null)) {
			mensaje = "Error al enviar cancion";		
		} else {
			mensaje = "Se ha enviado cancion";
		}
		
		request.setAttribute("cancion", model.getById(Long.parseLong(id)) );
		request.setAttribute("mensaje", mensaje );
		
		RequestDispatcher dispatch = request.getRequestDispatcher("modificar.jsp");
		dispatch.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		ModeloCancionImpl model = ModeloCancionImpl.getInstance();
		String mensaje = "Todo correcto";
		
		String id = request.getParameter("id");
		String nombre = request.getParameter("nombre");
		String artista = request.getParameter("artista");
		String duracion = request.getParameter("duracion");
		String sImagen = request.getParameter("sImagen");
		
		Cancion c;
		try {
			c = model.getById(Long.parseLong(id));
			c.setNombre(nombre);
			c.setArtista(artista);
			c.setDuracion(duracion);
			c.setCover(sImagen);
			
			model.update(c, Long.parseLong(id));
		} catch (Exception e) {
			mensaje = "Ha habido un error";
			e.printStackTrace();
		}
		
		request.setAttribute("listado", model.getAll() );
		request.setAttribute("mensaje", mensaje );
		
		RequestDispatcher dispatch = request.getRequestDispatcher("listado.jsp");
		dispatch.forward(request, response);
		
	}

}

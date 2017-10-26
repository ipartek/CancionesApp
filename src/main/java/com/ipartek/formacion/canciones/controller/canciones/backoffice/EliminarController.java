package com.ipartek.formacion.canciones.controller.canciones.backoffice;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipartek.formacion.canciones.modelo.ModeloCancionImpl;

/**
 * Servlet implementation class EliminarController
 */
@WebServlet("/backoffice/eliminar")
public class EliminarController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ModeloCancionImpl model = ModeloCancionImpl.getInstance();
		String mensaje = "";
		
		//recoger parametro
		String id = request.getParameter("id");
		if(id != null) {
			//llamar modelo para eliminar cancion
			if(model.delete(Long.parseLong(id))) {
				mensaje = "Cancion eliminada con exito";
			} else {
				mensaje = "Uppssss, no se puede eliminar";
			}
		} else {
			if(model.deleteAll()) {
				mensaje = "Se han eliminado todas las canciones";
			} else {
				mensaje = "No se han eliminado las canciones";
			}
		}
		
		
		//conseguir listado canciones y pasar como atributo
		
		request.setAttribute("listado", model.getAll() );
		request.setAttribute("mensaje", mensaje );
		
		//ir a listado.jsp
		
		RequestDispatcher dispatch = request.getRequestDispatcher("listado.jsp");
		dispatch.forward(request, response);
	}


}

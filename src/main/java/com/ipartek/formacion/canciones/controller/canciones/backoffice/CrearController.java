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
 * Servlet implementation class CrearController
 */
@WebServlet("/backoffice/crear")
public class CrearController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ModeloCancionImpl model = ModeloCancionImpl.getInstance();
		
		//recoger PARAMETROS del formulario	
		String nombre = request.getParameter("nombre");
		String artista = request.getParameter("artista");
		String duracion = request.getParameter("duracion");
		String sImagen = request.getParameter("sImagen");
		
		String mensajeUsuario = "";
		
		//Validar nombre y artista
		if(nombre.trim().length() == 0 || artista.trim().length() == 0) {
			mensajeUsuario = "No es correcto el nombre o artista";
		}
		if("".equals(sImagen)) {
			sImagen = "img/defaultalbum.png";
		}
		//creamos la Cancion
		Cancion c = null;
		try {
			c = new Cancion(nombre, artista, duracion, sImagen);
			
			//guardar la Cancion
			if(mensajeUsuario == "") {
				if ( model.insert(c) ) {
					mensajeUsuario = "Yeahhhh tenemos una nueva canción";
				}else {
					mensajeUsuario = "Uppss ha surgido un problema";
				}
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		//enviamos ATRIBUTOs en la request para la JSP
		request.setAttribute("listado", model.getAll() );
		request.setAttribute("mensaje", mensajeUsuario );
		
		
		//vamos a la Vista
		RequestDispatcher dispatch = request.getRequestDispatcher("listado.jsp");
		dispatch.forward(request, response);
		
	}

}

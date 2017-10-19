package com.ipartek.formacion.canciones.controller.canciones.backoffice;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipartek.formacion.canciones.modelo.ModeloCancion;
import com.ipartek.formacion.canciones.modelo.ModeloCancionMySQLImpl;
import com.ipartek.formacion.canciones.pojo.Cancion;

/**
 * Servlet implementation class ListarController
 */
@WebServlet("/backoffice/listar")
public class ListarController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
   
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//recuperar canciones
		ModeloCancion modelo = ModeloCancionMySQLImpl.getInstance();
		
		ArrayList<Cancion> canciones = modelo.getAll();
		
		//pasar ATRIBUTO listado en la request para la JSP
		request.setAttribute("listado", canciones);
		
		//ir a la listar.jsp
		RequestDispatcher dispatch = request.getRequestDispatcher( "/backoffice/listado.jsp");
		dispatch.forward(request, response);
		
		
	}

}

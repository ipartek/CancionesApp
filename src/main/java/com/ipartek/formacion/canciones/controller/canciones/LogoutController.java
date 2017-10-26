package com.ipartek.formacion.canciones.controller.canciones;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LogoutController
 */
@WebServlet("/logout")
public class LogoutController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			HttpSession session = request.getSession();
			session.invalidate();
			session = null;
		}catch (Exception e) {
			e.printStackTrace();
		}finally {		
			
			// request.getRequestDispatcher("canciones/login.jsp").forward(request, response);
			String msg = URLEncoder.encode("Gracias por visitarnos, vuelve pronto", "UTF-8");
			response.sendRedirect("login.jsp?msg=" + msg);			
			
		}	
			
	}//doGet

}

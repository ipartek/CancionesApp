package com.ipartek.formacion.canciones.controller.canciones;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ipartek.formacion.canciones.modelo.ModeloUsuario;
import com.ipartek.formacion.canciones.pojo.Usuario;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/login")
public class LoginController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final int EXPIRED_TIME = 60*5;  // 5min
	private static final int EXPIRED_TIME_COOKIES = 60*60*24*365;  // 1 year
	private static final String VIEW_LOGIN = "login.jsp";
	private static final String VIEW_HOME  = "backoffice/index.jsp";
	private static ModeloUsuario model = null;
	private static String msg = "Error inesperado, sentimos las molestias :-(";
	
	
	@Override
	public void init(ServletConfig config) throws ServletException {		
		super.init(config);
		model = ModeloUsuario.getInstance();
	}
	
	@Override
	public void destroy() {	
		super.destroy();
		model = null;
	}
	
   
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request,response);
	}

	private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
		
				
		//recoger parametros
		String nombre = request.getParameter("nombre");
		String password = request.getParameter("password");
		String remenberme = request.getParameter("remenberme");
		
		String view = VIEW_LOGIN;
		
		try {
			HttpSession session = request.getSession(true);
			session.setMaxInactiveInterval(EXPIRED_TIME);			
			
			Usuario usuario = model.validar(nombre, password);
			
			if ( usuario != null ) {
				msg = String.format("Ongi Etorri (%s)%s",usuario.getId(), usuario.getNombre());
				view = VIEW_HOME;
				
				//guardar usuario en session
				session.setAttribute("usuario_logeado", usuario);			
				
				gestionCookies(remenberme,usuario, response );				
				
			}else {
				msg = "Lo sentimos pero las credenciales no son correctas, prueba de nuevo por favor.";
				session.setAttribute("usuario_logeado", null);
			}			
			
		}catch (Exception e) {
			e.printStackTrace();
			msg = "Exception " + e.getMessage();
		}finally {
			request.setAttribute("msg", msg);
			request.getRequestDispatcher(view).forward(request, response);
		}
		
	}

	private void gestionCookies(String remenberme, Usuario usuario, HttpServletResponse response) {
		Cookie cNombre = null;
		Cookie cAvatar = null;
		Cookie cUltVisita = null;
		
		if ( remenberme == null ) {
			
			cNombre = new Cookie("cNombre", "" );
			cNombre.setMaxAge(0);			
						
			cUltVisita = new Cookie("cUltVisita", "" );
			cUltVisita.setMaxAge(0);		
			
			cAvatar = new Cookie("cAvatar", "" );
			cAvatar.setMaxAge(0);
			
		}else {			
			cNombre = new Cookie("cNombre", usuario.getNombre() );
			cNombre.setMaxAge(EXPIRED_TIME_COOKIES);
						
			cUltVisita = new Cookie("cUltVisita", String.valueOf(System.currentTimeMillis()) );
			cUltVisita.setMaxAge(EXPIRED_TIME_COOKIES);		
			
			cAvatar = new Cookie("cAvatar", usuario.getAvatar() );
			cAvatar.setMaxAge(EXPIRED_TIME_COOKIES);
		}	
		response.addCookie(cNombre);
		response.addCookie(cUltVisita);
		response.addCookie(cAvatar);
			
	}

}

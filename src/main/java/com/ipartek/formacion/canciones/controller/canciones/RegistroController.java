package com.ipartek.formacion.canciones.controller.canciones;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipartek.formacion.canciones.modelo.ModeloUsuario;
import com.ipartek.formacion.canciones.modelo.pojo.Usuario;

/**
 * Servlet implementation class RegistroController
 */
@WebServlet("/registro")
public class RegistroController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static ModeloUsuario model = null;
       
    
	@Override
	public void init(ServletConfig config) throws ServletException {
			super.init(config);
			model = ModeloUsuario.getInstance();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);				
	}
	
	//INSERT INTO `spoty`.`usuario` (`nombre`, `pass`, `email`) VALUES ('manola', '123456', 'manola.portugesa@gmail.com');
	private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String msg = "Ooops";
		String view = "login.jsp";
		try {
			
			//recoger parametros
			String nombre = request.getParameter("nombre");
			String pass = request.getParameter("pass");
			String pass2 = request.getParameter("pass2");
			String email = request.getParameter("email");
			String avatar = request.getParameter("avatar");
			
			//validar
			if (    "".equals(nombre) || 
					"".equals(pass)   ||
					"".equals(pass2)  ||
					"".equals(email) 
				) {                                      // comprobación campos obligatorios
				
				msg = "Faltan Campos Obligatorios";
				view = "canciones/registro.jsp";
				
			}else if ( !pass2.equals(pass)) {            // No coindicen contraseñas
				msg = "No coindiden las contraseñas";
				view = "canciones/registro.jsp";
				
			}else {                                     // parametros obligatorios rellenos
				
				Usuario u = new Usuario(nombre, pass, email);
				u.setAvatar(avatar);
				model.insertar(u);
				msg = String.format("Gracias por registrate (%s)%s", u.getId(), u.getNombre());
				
			}		
			
			
			
		}catch (Exception e) {
			msg = "Excepción " + e.getMessage();
			e.printStackTrace();
		}finally {
			request.setAttribute("msg", msg);
			request.getRequestDispatcher(view).forward(request, response);
			
		}
		
	}

}

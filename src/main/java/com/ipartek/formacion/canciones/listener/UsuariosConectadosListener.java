package com.ipartek.formacion.canciones.listener;

import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

import com.ipartek.formacion.canciones.modelo.pojo.Usuario;

/**
 * Application Lifecycle Listener implementation class UsuariosConectadosListener
 *
 */
@WebListener
public class UsuariosConectadosListener implements HttpSessionAttributeListener {

   
	private static ArrayList<Usuario> usuarios = null;
	private ServletContext appContext = null;
	
	public UsuariosConectadosListener(){
		usuarios = new ArrayList<Usuario>();
	}
	
	synchronized void addUser( Usuario u, ServletContext ctx) {
		usuarios.add(u);
		ctx.setAttribute("usuarios_logeados", usuarios);
	}
	
	synchronized void deleteUser( Usuario u, ServletContext ctx) {
		usuarios.remove(u);
		ctx.setAttribute("usuarios_logeados", usuarios);
	}
	
	/**
     * @see HttpSessionAttributeListener#attributeAdded(HttpSessionBindingEvent)
     */
    public void attributeAdded(HttpSessionBindingEvent se)  { 
    	
        System.out.println("Atributo añadido " + se.getName() );   
        
        appContext = se.getSession().getServletContext();
        
        if ( "usuario_logeado".equals(se.getName()) ) {
        	addUser( (Usuario)se.getValue(), appContext);
        }       
    }

	/**
     * @see HttpSessionAttributeListener#attributeRemoved(HttpSessionBindingEvent)
     */
    public void attributeRemoved(HttpSessionBindingEvent se)  { 
    	System.out.println("Atributo eliminado " + se.getName());
    	
    	appContext = se.getSession().getServletContext();
    	if ( "usuario_logeado".equals(se.getName()) ) {
         	deleteUser( (Usuario)se.getValue(), appContext);
        } 
    }

	/**
     * @see HttpSessionAttributeListener#attributeReplaced(HttpSessionBindingEvent)
     */
    public void attributeReplaced(HttpSessionBindingEvent se)  { 
    	System.out.println("Atributo reemplazado " + se.getName());
    }
	
}

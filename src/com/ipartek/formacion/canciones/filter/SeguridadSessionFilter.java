package com.ipartek.formacion.canciones.filter;

import java.io.IOException;
import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ipartek.formacion.canciones.pojo.Usuario;

/**
 * Servlet Filter implementation class SeguridadSessionFilter
 */
@WebFilter(dispatcherTypes = {DispatcherType.REQUEST }
					, urlPatterns = { "/backoffice/*" })
public class SeguridadSessionFilter implements Filter {

    
	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest httpRequest   = (HttpServletRequest)request;
		HttpServletResponse httpResponse = (HttpServletResponse)response;
		
		
		System.out.println("Filtrando: " + httpRequest.getRequestURL() );
		
		///comprobar que haya hecho login
		HttpSession session = httpRequest.getSession();
		Usuario u = (Usuario)session.getAttribute("usuario_logeado");
		if ( u == null ) {
			httpResponse.sendRedirect( httpRequest.getContextPath() + "/login.jsp?msg=Debes+estar+logeado");
		}			
		
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}

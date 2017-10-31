<%@page import="java.util.ArrayList"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@page import="com.ipartek.formacion.canciones.modelo.pojo.Cancion"%>

<%@include file="../includes/header.jsp" %>
<%@include file="../includes/navbar.jsp" %>

      <!-- Main component for a primary marketing message or call to action -->
      <div class="jumbotron">
        <h1>Top 5</h1>        
        
           
        
        * Declarar 5 canciones en una coleccion <br>
        * recorrerlas con un c:forEach<br>
        * Si la duracion es mayor que 1:00 poner en rojo usando "c:if"<br>
        
         <%
         	
        	ArrayList<Cancion> top5 = new ArrayList<Cancion>();
        	top5.add(new Cancion("Manolo Bombo","Manolo","0:34","http://cdn.20m.es/img2/recortes/2017/06/07/488302-944-605.jpg"));
        	top5.add(new Cancion("Bailare sobre tu timba","Siniestro Total","0:33","http://lafonoteca.net/wp-content/uploads/2010/08/Siniestro_Total-Bailare_Sobre_Tu_Tumba-Frontal-600x600.jpg"));
        	top5.add(new Cancion("Macarena","Los del Rio","1:00","https://img.buzzfeed.com/buzzfeed-static/static/2016-09/20/5/asset/buzzfeed-prod-web04/sub-buzz-19815-1474364074-6.png?downsize=715:*&output-format=auto&output-quality=auto"));
        	top5.add(new Cancion("El tractor Amarillo","La Orquesta","1:01","https://previews.123rf.com/images/stefan77/stefan771308/stefan77130800004/21490683-Tractor-amarillo-sobre-un-fondo-blanco-Foto-de-archivo.jpg"));
        	top5.add(new Cancion("La Barbacoa","Georgie Dann","9:34","http://www.sufridoresencasa.com/wp-content/uploads/2013/08/Georgie-Dann-La-Barbacoa.jpg"));
        	
        	//guardarlo en el contexto de la pagina
        	pageContext.setAttribute("top5", top5);
        	
        %>
            
        <ol data-list="user">    
        <c:forEach items="${pageScope.top5}" var="cancion" varStatus="iteration">
        	<li>
        			<span class="id">${iteration.index+1}</span>
        	 		<img src="${cancion.cover}" alt="imagen album de ${cancion.nombre}">
        	 		<div class="content-data">
        	 			<span class="name">${cancion.nombre}</span>
        	 			<span class="email">${cancion.artista}</span>
        	 		</div>
        	 		<div class="content-pass">
        	 			<span class="${ cancion.getDuracionSegundos()>60 ?'text-red':'normal'}">${cancion.duracion}</span>        	 			
        	 		</div>
        	 </li>		
        </c:forEach>    
        </ol>    
            
            
        
        
        <hr>
        <% 
            Cancion c1 = new Cancion("Scriptlep","Manolo","9:34");
        	out.print(c1);
        %>
        <hr>
        
        
        
        <!-- Para poder usar jsp:useBean debe tener un constructor por defecto  -->
        <jsp:useBean id="c2" class="com.ipartek.formacion.canciones.modelo.pojo.Cancion">
        	 <jsp:setProperty name="c2" property="nombre" value="Bailere sobre tu tumba" />
        	 <jsp:setProperty name="c2" property="artista" value="Siniestro Total" />
        	 <jsp:setProperty name="c2" property="duracion" value="3:23" />
        </jsp:useBean>     	     	
     	<c:out value="${c2}"></c:out>
        
        
      </div>

<%@include file="../includes/footer.jsp" %>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.util.ArrayList"%>

<%@include file="../../includes/header.jsp" %>
<%@include file="../../includes/navbar.jsp" %>


		<c:if test="${!empty requestScope.mensaje}">        
     		<p>${requestScope.mensaje}</p>
     	</c:if>

	<h1>Listado Canciones</h1>
	
	<c:if test="${!empty listado}">
		<table class="ordenable table table-striped table-bordered">
			<caption>Listado Canciones</caption>	
			<thead>
				<tr>
					<th>Cover</th>
					<th>Nombre</th>
					<th>Artista</th>
					<th>Duración</th>
					<th>Operaciones</th>				
				</tr>
			</thead>		
			<tbody>
				<c:forEach items="${listado}" var="c">		
					<tr>
						<td>
							<img class="cover" src="${c.cover}" alt="Imagen del álbum" class="cover" />
						</td>				
						<td>${c.nombre}</td>				
						<td>${c.artista}</td>
						<td>${c.duracion}</td>				
						<td>				
							<a href="modificar?id=${c.id}">  Modificar  </a>
							<a href="eliminar?id=${c.id}"><i class="fa fa-trash" aria-hidden="true"></i></a>
						</td>				
					</tr>
				</c:forEach>		
			</tbody>
		</table>
	</c:if>
	
	
	<c:if test="${empty listado}">
		<h3>No hay canciones</h3>
	</c:if>	
	
	

	<h2>Crear Cancion</h2>
	<form action="crear" method="post">

		<input type="text" name="nombre" placeholder="Nombre de la Cancion" required> 
		<br> <br> 
		<input type="text" name="artista" placeholder="Artista o Album" required> <br>
		<br> 
		<input type="text" name="duracion" placeholder="00:00" required> <br> <br>
		<input type="text" name="sImagen" placeholder="Inserta URL de la imagen"> <br>
		<br> <input type="submit" value="Crear Cancion Nueva"> <br> <br>
		<a href="eliminar?">Eliminar todas las canciones</a>
		

	</form>


<%@include file="../../includes/footer.jsp" %>
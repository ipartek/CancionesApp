<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="com.ipartek.formacion.canciones.modelo.pojo.Cancion"%>
<%@page import="java.util.ArrayList"%>

<%@include file="../../includes/header.jsp" %>
<%@include file="../../includes/navbar.jsp" %>
<%@include file="../../includes/alerts.jsp" %>

	<h1>Listado Canciones</h1>
	<a class="btn btn-primary" href="${pageContext.request.contextPath}/backoffice/canciones?accion=<%= Acciones.MOSTRAR_FORMULARIO%>">Crear Cancion</a>
	
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
							<a href="${pageContext.request.contextPath}/backoffice/canciones?id=${c.id}&accion=<%= Acciones.MOSTRAR_FORMULARIO%>"><img class="cover" src="${c.cover}" alt="Imagen del álbum" class="cover" /></a>
						</td>				
						<td>${c.nombre}</td>				
						<td>${c.artista}</td>
						<td>${c.duracion}</td>				
						<td>				
							<a href="${pageContext.request.contextPath}/backoffice/canciones?id=${c.id}&accion=<%= Acciones.MOSTRAR_FORMULARIO%>">  Modificar  </a>
							<a href="${pageContext.request.contextPath}/backoffice/canciones?id=${c.id}&accion=<%= Acciones.ELIMINAR%>"><i class="fa fa-trash" aria-hidden="true"></i></a>
						</td>				
					</tr>
				</c:forEach>		
			</tbody>
		</table>
	</c:if>
	
	
	<c:if test="${empty listado}">
		<h3>No hay canciones</h3>
	</c:if>	
	
	



<%@include file="../../includes/footer.jsp" %>
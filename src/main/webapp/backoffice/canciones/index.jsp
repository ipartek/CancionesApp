<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.util.ArrayList"%>

<%@include file="../../includes/header.jsp" %>
<%@include file="../../includes/navbar.jsp" %>

<%@include file="../../includes/alerts.jsp" %>


	<h1>Listado Canciones</h1>
	<a href="${pageContext.request.contextPath}/backoffice/canciones?accion=<%=Acciones.MOSTRAR_FORMULARIO%>" class="btn btn-primary">CREAR CANCION</a>
	<c:if test="${!empty listado}">
		<table class="ordenable table table-striped table-bordered">
			<caption>Listado Canciones</caption>	
			<thead>
				<tr>
					<th>Cover</th>
					<th>Nombre</th>
					<th>Artista</th>
					<th>Duraci�n</th>
					<th>Operaciones</th>				
				</tr>
			</thead>		
			<tbody>
				<c:forEach items="${listado}" var="c">		
					<tr>
						<td>
							<a href="${pageContext.request.contextPath}/backoffice/canciones?accion=<%=Acciones.MOSTRAR_FORMULARIO%>&id=${c.id}">
							<img class="cover" src="${c.cover}" alt="Imagen del �lbum" class="cover" /></a>
						</td>				
						<td>${c.nombre}</td>				
						<td>${c.artista}</td>
						<td>${c.duracion}</td>				
						<td>				
							<a href="${pageContext.request.contextPath}/backoffice/canciones?accion=<%=Acciones.MOSTRAR_FORMULARIO%>&id=${c.id}"><i class="fa fa-plus-square" aria-hidden="true"></i></a>
							<a href="${pageContext.request.contextPath}/backoffice/canciones?id=${c.id}&accion=<%=Acciones.ELIMINAR%>"><i class="fa fa-trash" aria-hidden="true"></i></a>
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
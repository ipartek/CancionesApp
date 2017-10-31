<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.util.ArrayList"%>

<%@include file="../../includes/header.jsp" %>
<%@include file="../../includes/navbar.jsp" %>
<%@include file="../../includes/alerts.jsp" %>

	<h1>Listado Usuarios</h1>
	
	<a class="btn btn-primary" href="${pageContext.request.contextPath}/backoffice/usuarios?accion=<%=Acciones.MOSTRAR_FORMULARIO%>">
		Nuevo Usuario
	</a>	
	
	
	<c:if test="${!empty listado}">
		<table class="ordenable table table-striped table-bordered">
			<caption>Listado Usuarios</caption>	
			<thead>
				<tr>
					<th>Avatar</th>
					<th>Nombre</th>
					<th>Pass</th>
					<th>Email</th>
					<th>Operaciones</th>				
				</tr>
			</thead>		
			<tbody>
				<c:forEach items="${listado}" var="c">		
					<tr>
						<td>
							<a href="${pageContext.request.contextPath}/backoffice/usuarios?id=${c.id}&accion=<%=Acciones.MOSTRAR_FORMULARIO%>">
								<img class="cover" src="${c.avatar}" alt="Imagen del álbum" class="cover" />
							</a>	
						</td>				
						<td>${c.nombre}</td>				
						<td>${c.pass}</td>
						<td>${c.email}</td>				
						<td>				
							<a href="${pageContext.request.contextPath}/backoffice/usuarios?id=${c.id}&accion=<%=Acciones.MOSTRAR_FORMULARIO%>">  Modificar  </a>
							<a href="${pageContext.request.contextPath}/backoffice/usuarios?id=${c.id}&accion=<%=Acciones.ELIMINAR%>"><i class="fa fa-trash" aria-hidden="true"></i></a>
						</td>				
					</tr>
				</c:forEach>		
			</tbody>
		</table>
	</c:if>
	
	
	<c:if test="${empty listado}">
		<h3>No hay usuarios</h3>
	</c:if>	
	
	

	


<%@include file="../../includes/footer.jsp" %>
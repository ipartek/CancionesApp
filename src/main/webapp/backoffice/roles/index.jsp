<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.util.ArrayList"%>

<%@include file="../../includes/header.jsp" %>
<%@include file="../../includes/navbar.jsp" %>
<%@include file="../../includes/alerts.jsp" %>
		
	<h1>Listado Roles</h1>
	
	<a class="btn btn-primary" href="${pageContext.request.contextPath}/backoffice/roles?accion=<%=Acciones.MOSTRAR_FORMULARIO%>">
		Nueva Registro							
	</a>	
	
	
	<c:if test="${!empty listado}">
		<table class="ordenable table table-striped table-bordered">
			<caption>Listado Roles</caption>	
			<thead>
				<tr>
					<th>id</th>
					<th>Nombre</th>								
					<th>Operaciones</th>
				</tr>
			</thead>		
			<tbody>
				<c:forEach items="${listado}" var="rol">		
					<tr>
						<td>${rol.id}</td>				
						<td>${rol.nombre}</td>								
						<td>				
							<a href="${pageContext.request.contextPath}/backoffice/roles?id=${rol.id}&accion=<%=Acciones.MOSTRAR_FORMULARIO%>">  Modificar  </a>
							<a href="${pageContext.request.contextPath}/backoffice/roles?id=${rol.id}&accion=<%=Acciones.ELIMINAR%>"><i class="fa fa-trash" aria-hidden="true"></i></a>
						</td>				
					</tr>
				</c:forEach>		
			</tbody>
		</table>
	</c:if>
	
	
	<c:if test="${empty listado}">
		<h3>No hay Registros</h3>
		<p><a href="${pageContext.request.contextPath}/backoffice/roles?accion=<%=Acciones.MOSTRAR_FORMULARIO%>">¿ Quieres crear uno nuevo ?</a></p>
	</c:if>	
	
	

	


<%@include file="../../includes/footer.jsp" %>
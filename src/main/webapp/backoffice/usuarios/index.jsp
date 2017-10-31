<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.util.ArrayList"%>

<%@include file="../../includes/header.jsp" %>
<%@include file="../../includes/navbar.jsp" %>

<%@include file="../../includes/alerts.jsp" %>


		

<style>
.cover {
	width: 150px;
}

p {
	color: red;
}

.red {
	color: red;
	text-decoration: none;
}

.boton {
    text-shadow: 0px 1px rgba(0, 0, 0, 0.2);
    text-align: center;
    text-decoration: none;
    font-family: 'Helvetica Neue', Helvetica, sans-serif;
    display: inline-block;
    color: #FFF;
    background: #7F8C8D;
    padding: 6px 30px;
    white-space: nowrap;
    -webkit-border-radius: 5px;
    -moz-border-radius: 5px;
    border-radius: 5px;
    margin: 10px 5px;
    -webkit-transition: all 0.2s ease-in-out;
    -ms-transition: all 0.2s ease-in-out;
    -moz-transition: all 0.2s ease-in-out;
    -o-transition: all 0.2s ease-in-out;
    transition: all 0.2s ease-in-out;
    float: right;
}

.azul {
    background: #3498DB;
}
</style>



<a href="usuarios?accion=<%=Acciones.MOSTRAR_FORMULARIO%>" class="boton azul">Crear nuevo usuario</a>

	<h1>Listado Usuarios</h1>
	
	<c:if test="${!empty listado}">
		<table class="ordenable table table-striped table-bordered">
			<caption>Listado Usuarios</caption>	
			<thead>
				<tr>
					<th>Avatar</th>
					<th>Nombre</th>
					<th>Email</th>
					<th>Password</th>
					<th>Operaciones</th>				
				</tr>
			</thead>		
			<tbody>
				<c:forEach items="${listado}" var="u">		
					<tr>
						<td>
						<a href="canciones?accion=<%=Acciones.MOSTRAR_FORMULARIO%>&id=${u.id}"><img src="${u.avatar}" alt="Imagen del álbum" class="cover" /></a>
							
						</td>				
						<td>${u.nombre}</td>				
						<td>${u.email}</td>
						<td>${u.password}</td>				
						<td>				
							<a href="usuarios?accion=<%=Acciones.MOSTRAR_FORMULARIO%>&id=${u.id}">MODIFICAR</a>
							<a class="red"
							href="canciones?id=${u.id}&accion=<%= Acciones.ELIMINAR %>"><i class="fa fa-trash" aria-hidden="true"></i></a>
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
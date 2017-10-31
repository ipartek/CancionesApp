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



<a href="canciones?accion=<%=Acciones.MOSTRAR_FORMULARIO%>" class="boton azul">Crear nueva cancion</a>

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
						<a href="canciones?accion=<%=Acciones.MOSTRAR_FORMULARIO%>&id=${c.id}"><img src="${c.cover}" alt="Imagen del álbum" class="cover" /></a>
							
						</td>				
						<td>${c.nombre}</td>				
						<td>${c.artista}</td>
						<td>${c.duracion}</td>				
						<td>				
							<a href="canciones?accion=<%=Acciones.MOSTRAR_FORMULARIO%>&id=${c.id}">MODIFICAR</a>
							<a class="red"
							href="canciones?id=${c.id}&accion=<%= Acciones.ELIMINAR %>"><i class="fa fa-trash" aria-hidden="true"></i></a>
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
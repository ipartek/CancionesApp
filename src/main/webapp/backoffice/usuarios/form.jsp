<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.util.ArrayList"%>

<%@include file="../../includes/header.jsp" %>
<%@include file="../../includes/navbar.jsp" %>

<%@include file="../../includes/alerts.jsp" %>

<c:choose>
	<c:when test="${ usuario.id == -1 }"><h1>Crear Nuevo Usuario</h1></c:when>
	<c:otherwise><h1>Detalle ${usuario.nombre}</h1></c:otherwise>
</c:choose>

<form action="usuarios" method="post">

	<!-- campos ocultos -->
	<input type="hidden" name="id" value="${usuario.id}">
	<input type="hidden" name="accion" value="<%= Acciones.CREAR_MODIFICAR %>">
	
	<!-- campos editables por el usuario -->
	<label for="nombre">Nombre:</label>
	<input type="text" name="nombre" value="${usuario.nombre}" required>
	<br>
	
	<label for="email">E-mail:</label>
	<input type="text" name="email" value="${usuario.email}" required>
	<br>
	
	<label for="password">Password:</label>
	<input type="text" name="password" value="${usuario.password}" required>
	<br>
	
	<label for="avatar">Avatar:</label>
	<input type="text" name="avatar" value="${usuario.cover}">
	<br>
	
	
	<!-- boton submit -->
	<input type="submit" value="MODIFICAR">
</form>
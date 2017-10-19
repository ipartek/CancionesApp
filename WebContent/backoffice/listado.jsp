<%@page import="com.ipartek.formacion.canciones.pojo.Cancion"%>
<%@page import="java.util.ArrayList"%>

<%@include file="../includes/header.jsp" %>
<%@include file="../includes/navbar.jsp" %>


	<%
		//gestion de los mensajes para el Usuario
		String mensaje = (String) request.getAttribute("mensaje");
		if (mensaje != null) {
			out.print("<p>" + mensaje + "</p>");
		}
	%>

	<h1>Listado Canciones</h1>
	
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
		<%
			//recorrer el ArrayList
			ArrayList<Cancion> canciones = (ArrayList<Cancion>) request.getAttribute("listado");
			for (Cancion c : canciones) {
		%>
		<tr>
			<td>
				<img class="cover" src="<%=c.getCover() %>" alt="Imagen del álbum" class="cover" />
			</td>
			
			<td>
				<%=c.getNombre()%>				
			</td>
			
			<td>
				<%=c.getArtista()%>				
			</td>
			
			<td>	
				<%=c.getDuracion()%>			
			</td>
			
			<td>				
				<a href="modificar?id=<%=c.getId() %>">  Modificar  </a>
				<a href="eliminar?id=<%=c.getId()%>"><i class="fa fa-trash" aria-hidden="true"></i></a>
			</td>
			
		</tr>

		<%
			} // final for			
		%>
		</tbody>
	</table>
	<% 
		if(canciones.size() == 0){
	%>
		<h3>No hay canciones</h3>
	<% 	
		}
	%>
	

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


<%@include file="../includes/footer.jsp" %>
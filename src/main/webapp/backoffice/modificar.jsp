<%@page import="com.ipartek.formacion.canciones.pojo.Cancion"%>
<!DOCTYPE>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Modificado | Canciones Web</title>
	
	<link rel="stylesheet" type="text/css" href="css/styles.css">

</head>
<body>
	<%
		//gestion de los mensajes para el Usuario
		String mensaje = (String) request.getAttribute("mensaje");
		if (mensaje != null) {
			out.print("<p>" + mensaje + "</p>");
		}
	%>

	<%
		Cancion cancion = (Cancion) request.getAttribute("cancion");
	%>	

	<h2>Modificar Cancion</h2>
	<form action="modificar" method="post">
		
		<label for="id">Identificador:</label>
		<input type="text" name="id" value="<%=cancion.getId() %>" readonly> 
		<br> <br>
		<label for="nombre">Nombre:</label> 
		<input type="text" name="nombre" value="<%=cancion.getNombre() %>" required> 
		<br> <br> 
		<label for="artista">Artista:</label>
		<input type="text" name="artista" value="<%=cancion.getArtista() %>" required>
		<br> <br>  
		<label for="duracion">Duración:</label>
		<input type="text" name="duracion" value="<%=cancion.getDuracion()%>" required> <br>
		 <br>
		 <label for="sImagen">URL de imagen:</label>
		<input type="text" name="sImagen" value="<%=cancion.getCover() %>" > <br>
		<br> <input type="submit" value="Modificar cancion"> <br> <br>	

	</form>




</body>
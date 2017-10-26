<%@page import="com.ipartek.formacion.canciones.pojo.Cancion"%>

<h1>Detalle Cancion</h1>

<%
	//recoger atributos
	String msg = (String)request.getAttribute("mensaje");
    Cancion c = (Cancion)request.getAttribute("cancion");

%>

<p style="color:green;"><%=msg%> </p>


TODO terminar de pintar los atributos de la Cancion
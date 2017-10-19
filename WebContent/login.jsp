<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@page errorPage="error.jsp" %>

<!DOCTYPE html>
<html lang="es">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">    
    <link rel="icon" href="${pageContext.request.contextPath}/img/favicon.ico">

    <title>Login | Canciones Web</title>

    <!-- Bootstrap core CSS -->
    <link href="${pageContext.request.contextPath}/vendors/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/vendors/bootstrap/css/bootstrap-theme.min.css" rel="stylesheet">  
    
    <link href="${pageContext.request.contextPath}/css/login.css" rel="stylesheet">
</head>
<body>    


 <div class="container">
        <div class="card card-container">         
        
        
        	<!-- parametro que viene en la URL-->
        	<%
        	String msgUrl =	request.getParameter("msg");
        	if ( msgUrl != null ){
        		out.print("<p>" + msgUrl + "</p>");
        	}
        	%>
        	
        	<!-- Atributo que viene del Servlet -->        
        	<p>${msg}</p>
           
           <c:choose>
			    <c:when test="${empty cookie['cAvatar']}">
			        <img id="profile-img" class="profile-img-card" src="${pageContext.request.contextPath}/img/avatar_2x.png" />
			    </c:when>
			    <c:otherwise>
			        <img id="profile-img" class="profile-img-card" src="${cookie['cAvatar'].value}" />
			    </c:otherwise>
			</c:choose>
            
            <p id="profile-name" class="profile-name-card"></p>
            
            <form class="form-signin" action="${pageContext.request.contextPath}/login" method="post">
                <span id="reauth-email" class="reauth-email"></span>
                
                <input type="text"    
                       name="nombre"
                       value="${cookie['cNombre'].value}"  
                       tabindex="1"   
                       class="form-control" 
                       placeholder="Nombre Usuario" 
                       required 
                       autofocus>
                
                <input type="password" name="password" tabindex="2"  id="inputPassword" class="form-control" placeholder="Password" required>
                <div id="remember" class="checkbox">
                    <label>
                        <input type="checkbox" name="remenberme" value="true" tabindex="3" checked> Remember me
                    </label>
                </div>
                <button class="btn btn-lg btn-primary btn-block btn-signin" tabindex="4" type="submit">Login</button>
            </form><!-- /form -->
            
            <a href="${pageContext.request.contextPath}/registro.jsp" class="forgot-password">
                Registrate
            </a>
        </div><!-- /card-container -->
        
        <p class="fecha">Ultima visita: ${cookie['cUltVisita'].value}</p>
        
        
    </div><!-- /container -->
    
 </body>
 </html>   
    
    
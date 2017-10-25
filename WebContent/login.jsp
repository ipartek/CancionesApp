<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@page errorPage="error.jsp" %>


<c:set var="language" value="${ not empty cookie['cIdioma'] ? cookie['cIdioma'] : 'es_ES' }"></c:set>
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="i18nmessages" /> 

<!DOCTYPE html>
<html lang="${language}">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">    
    <link rel="icon" href="${pageContext.request.contextPath}/img/favicon.ico">

    <title>Login | Canciones Web</title>

	
	<link href="${pageContext.request.contextPath}/vendors/meteocons/stylesheet.css?v2" rel="stylesheet">

    <!-- Bootstrap core CSS -->
    <link href="${pageContext.request.contextPath}/vendors/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/vendors/bootstrap/css/bootstrap-theme.min.css" rel="stylesheet">  
    
    <link href="${pageContext.request.contextPath}/css/login.css" rel="stylesheet">
</head>
<body>    


 <div class="container">
 
 
 		<span class="icon-meteo" data-icon="&"></span>
 		
 	
 	
 
        <div class="card card-container">         
        
        
        	<!-- parametro que viene en la URL-->        
        	<c:if test="${!empty param.msg}">
        		<p>${param.msg}</p>
        	</c:if>
        	
        	<!-- Atributo que viene del Servlet -->
        	<c:if test="${!empty requestScope.msg}">        
        		<p>${requestScope.msg}</p>
        	</c:if>
           
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
                
                <label for="idioma">Selecciona idioma:</label>
                <select name="idioma">
                	<option value="es_ES">Castellano</option>
                	<option value="eu_ES">Euskara</option>
                </select>
                
                
                <div id="remember" class="checkbox">
                    <label>
                        <input type="checkbox" name="remenberme" value="true" tabindex="3" checked><fmt:message key="login.remenber"/>
                    </label>
                </div>
                <button class="btn btn-lg btn-primary btn-block btn-signin" tabindex="4" type="submit"><fmt:message key="login.boton"/></button>
            </form><!-- /form -->
            
            <a href="${pageContext.request.contextPath}/registro.jsp" class="forgot-password">
                Registrate
            </a>
        </div><!-- /card-container -->
        
        <c:if test="${!empty cookie['cUltVisita']}">
	        <jsp:useBean id="fecha" class="java.util.Date"/>
			<jsp:setProperty name="fecha" property="time" value="${cookie['cUltVisita'].value}"/>		
	        <p class="fecha">Ultima visita: <fmt:formatDate value="${fecha}" pattern="dd/MM/yyyy HH:mm"/> </p>
	    </c:if>    
        
        
    </div><!-- /container -->
    
 </body>
 </html>   
    
    
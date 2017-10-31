<%@page import="com.ipartek.formacion.canciones.controller.canciones.backoffice.Acciones"%>
<%@page import="com.ipartek.formacion.canciones.pojo.Usuario"%>

<div class="container"> 
 <!-- Fixed navbar -->
    <nav class="navbar navbar-default navbar-fixed-top">
      <div class="container">
      
        <div class="navbar-header">        
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="#">
        		<img alt="Brand" src="${pageContext.request.contextPath}/img/logo.png">
      	   </a>
        </div>
        
        <div id="navbar" class="navbar-collapse collapse">
          <ul class="nav navbar-nav">
            <li><a href="${pageContext.request.contextPath}/backoffice/index.jsp">Canciones Web</a></li>
            <li><a href="${pageContext.request.contextPath}/backoffice/canciones?accion=<%=Acciones.LISTAR%>">Canciones</a></li>
            <li><a href="${pageContext.request.contextPath}/backoffice/usuarios_conectados.jsp">U.Conectados</a></li>              
          </ul>
          
          <ul class="nav navbar-top-links navbar-right">
           		<li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#" aria-expanded="true">                       
                       <span class="user-name">${sessionScope.usuario_logeado.nombre}</span>
                       <i class="fa fa-user fa-fw"></i> <i class="fa fa-caret-down"></i>
                    </a>
                    <ul class="dropdown-menu dropdown-user">
                        <li><a href="${pageContext.request.contextPath}/backoffice/profile.jsp"><i class="fa fa-user fa-fw"></i> User Profile</a></li>                        
                        <li class="divider"></li>
                        <li><a href="${pageContext.request.contextPath}/logout"><i class="fa fa-sign-out fa-fw"></i> Logout</a></li>
                    </ul>
                    <!-- /.dropdown-user -->
                </li>       
          </ul>
          
          <form class="navbar-form navbar-right">
            <input type="text" class="form-control" placeholder="Buscar...">
          </form>
          
          
          
        </div><!--/.nav-collapse -->        
        
      </div>
    </nav>
<%@page import="java.util.ArrayList"%>

<%@include file="../includes/header.jsp" %>
<%@include file="../includes/navbar.jsp" %>

	<%
		ArrayList<Usuario> usuarios = (ArrayList<Usuario>)application.getAttribute("usuarios_logeados");
		
	%>

     <h1><span class="badge title"><%=usuarios.size()%></span>Usuarios Conectados</h1>
     
     <ol data-list="user">
     <%            
         for( Usuario uConectado : usuarios ){
        	 %>
        	 	<li>
        	 		<span class="id"><%=uConectado.getId()%></span>
        	 		<img src="<%=uConectado.getAvatar()%>" alt="">
        	 		<div class="content-data">
        	 			<span class="name"><%=uConectado.getNombre()%></span>
        	 			<span class="email"><%=uConectado.getEmail()%></span>
        	 		</div>
        	 		<div class="content-pass">
        	 			<span id="spanPasswrord<%=uConectado.getId()%>" data-password="<%=uConectado.getPass()%>" >*******</span>
        	 			<i onclick="showPass('<%=uConectado.getId()%>' )" class="glyphicon glyphicon-eye-open show-pass"></i>
        	 		</div>
        	 	</li>
        	 <% 
         }         
     %>    
     </ol>
     
     <script>
     
	    function showPass( id ){
	    	console.debug('showPass ' + id);    	 
	    	var span = document.getElementById("spanPasswrord"+id);
	    	span.innerHTML = span.dataset.password;
	    }
          
     	setTimeout(function(){
    	   window.location.reload(1);
    	}, 1000*10);
     
     </script>
     

<%@include file="../includes/footer.jsp" %>    
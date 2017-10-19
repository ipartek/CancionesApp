<%@include file="../includes/header.jsp" %>
<%@include file="../includes/navbar.jsp" %>


     <%
     	// Lo recogermos en el navbar.jsp     
     	// Usuario u = (Usuario)session.getAttribute("usuario_logeado");     
     %>
     
     
     
   <div class="row">
	        <div class="col-xs-12 col-sm-6 col-md-6">
	            <div class="well well-sm">
	                <div class="row">
	                    <div class="col-sm-6 col-md-4">
	                        <img src="<%= u.getAvatar() %>" alt="" class="img-rounded img-responsive" />
	                    </div>
	                    <div class="col-sm-6 col-md-8">
	                        <h3><%= u.getNombre() %></h3>
	                        <p>
	                            <i class="glyphicon glyphicon-envelope"></i> <%= u.getEmail() %>
	                            <br />
	                            <i class="glyphicon glyphicon-user"></i> <%= u.getId() %>
	                            <br />
	                            <i class="glyphicon glyphicon-lock"></i> 
	                            <span id="span_password" class="pass" data-password="<%= u.getPass() %>">*********</span> 
	                            <i id="btn_ojo" class="glyphicon glyphicon-eye-open show-pass oculto"></i>
	                        </p>
	                    </div>
	                </div>
	            </div>
	        </div>
	    </div>
	    
	    <script>
	    	var span = document.getElementById("span_password");
	    	var ojo  = document.getElementById("btn_ojo");
	    
	    	ojo.onmousedown = function(){
	    		console.debug('MouseDown event');
	    		span.innerHTML = span.dataset.password;	    		
	    	};
	    	
	    	ojo.onmouseup = function(){
	    		console.debug('MouseUp event');
	    		span.innerHTML = '*********';
	    	};	    	
	    </script>
	    
	    

<%@include file="../includes/footer.jsp" %>    
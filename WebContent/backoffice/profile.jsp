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
	                        <img src="${sessionScope.usuario_logeado.avatar}" alt="" class="img-rounded img-responsive" />
	                    </div>
	                    <div class="col-sm-6 col-md-8">
	                        <h3>${sessionScope.usuario_logeado.nombre}</h3>
	                        <p>
	                            <i class="glyphicon glyphicon-envelope"></i>${sessionScope.usuario_logeado.email}
	                            <br />
	                            <i class="glyphicon glyphicon-user"></i>${sessionScope.usuario_logeado.id}
	                            <br />
	                            <i class="glyphicon glyphicon-lock"></i> 
	                            <span id="span_password" class="pass" data-password="${sessionScope.usuario_logeado.pass}">*********</span> 
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
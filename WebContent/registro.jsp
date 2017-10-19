<%@page errorPage="error.jsp" %>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">    
    <link rel="icon" href="${pageContext.request.contextPath}/img/favicon.ico">

    <title>Canciones Web</title>

    <!-- Bootstrap core CSS -->
    <link href="${pageContext.request.contextPath}/vendors/bootstrap/css/bootstrap.min.css" rel="stylesheet">  
    <link href="${pageContext.request.contextPath}/css/register.css" rel="stylesheet">  
</head>
<body>

<hgroup>
  <h1>Registro</h1>
</hgroup>



<form action="${pageContext.request.contextPath}/registro" method="post">


	<p>${msg}</p>
	
	
  <div class="group">
    <input type="text" name="nombre"><span class="highlight"></span><span class="bar"></span>
    <label>Nombre</label>
  </div>
  <div class="group">
    <input type="password" name="pass"><span class="highlight"></span><span class="bar"></span>
    <label>Contraseña</label>
  </div>
  <div class="group">
    <input type="password" name="pass2"><span class="highlight"></span><span class="bar"></span>
    <label>Repetir contraseña</label>
  </div>
  <div class="group">
    <input type="email" name="email"><span class="highlight"></span><span class="bar"></span>
    <label>Email</label>
  </div>
  <div class="group">
    <input type="text" name="avatar"><span class="highlight"></span><span class="bar"></span>
    <label>URL avatar</label>
  </div>
  <button type="submit" class="button buttonBlue">registrarme
    <div class="ripples buttonRipples"><span class="ripplesCircle"></span></div>
  </button>
  <a href="${pageContext.request.contextPath}/login.jsp">< volver</a>
</form>

</body>
</html>
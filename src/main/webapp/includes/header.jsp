<%@page contentType="text/html; charset=UTF-8" %>

<%@page errorPage="error.jsp" %>
<%@page import="com.ipartek.formacion.canciones.modelo.pojo.Usuario"%>

<!DOCTYPE html>
<html lang="es">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">    
    <link rel="icon" href="${pageContext.request.contextPath}/img/favicon.ico">

    <title>Canciones Web</title>

    <!-- Bootstrap core CSS -->
    <link href="${pageContext.request.contextPath}/vendors/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/vendors/bootstrap/css/bootstrap-theme.min.css" rel="stylesheet">  
    
    <!-- font-awesome 4.0 -->
    <link href="${pageContext.request.contextPath}/vendors/font-awesome-4.7.0/css/font-awesome.min.css" rel="stylesheet">
    <!-- datatables -->
    <link href="${pageContext.request.contextPath}/vendors/datatables/css/datatables.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/vendors/datatables/css/dataTables.bootstrap.min.css" rel="stylesheet">
    
    <link href="${pageContext.request.contextPath}/css/custom.css" rel="stylesheet">
    
    
  
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    
  </head>

  <body>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="icon"
	href="${pageContext.request.contextPath}/img/favicon.ico">

<title>Canciones Web</title>

<link
	href="${pageContext.request.contextPath}/css/registro.css" rel="stylesheet">

<!-- Bootstrap core CSS -->
<link
	href="${pageContext.request.contextPath}/vendors/bootstrap/css/bootstrap.min.css"	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/vendors/bootstrap/css/bootstrap-theme.min.css" rel="stylesheet">
</head>
<body>


	<div class="container">
		<div class="row centered-form">
			<div
				class="col-xs-12 col-sm-8 col-md-4 col-sm-offset-2 col-md-offset-4">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">
							&iexcl;Bienvenid@! Por favor, reg&iacute;strese. <small>&iexcl;Es gratis!</small>
						</h3>
					</div>
					<div class="panel-body">
						<form role="form" action="${pageContext.request.contextPath}/registro" method="post">
						
						<p>${msg}</p>
							<div class="row">
								<div class="col-xs-12 col-sm-12 col-md-12">
									<div class="form-group">
										<input type="text" name="nombre" id="first_name"
											class="form-control input-sm" placeholder="Nombre">
									</div>
								</div>
							</div>

							<div class="form-group">
								<input type="email" name="email" id="email"
									class="form-control input-sm" placeholder="Email Address">
							</div>

							<div class="row">
								<div class="col-xs-6 col-sm-6 col-md-6">
									<div class="form-group">
										<input type="password" name="password1" id="password"
											class="form-control input-sm" placeholder="Password">
									</div>
								</div>
								<div class="col-xs-6 col-sm-6 col-md-6">
									<div class="form-group">
										<input type="password" name="password2"
											id="password_confirmation" class="form-control input-sm"
											placeholder="Confirm Password">
									</div>
								</div>
							</div>						
							<div class="row">
								<div class="col-xs-12 col-sm-12 col-md-12">
									<div class="form-group">
										<input type="text" name="avatar" id="avatar" class="form-control input-sm" placeholder="Avatar">
									</div>
								</div>
							</div>

							<input type="submit" value="Reg&iacute;strate" class="btn btn-info btn-block">

						</form>
					</div>
				</div>
			</div>
		</div>
	</div>


</body>
</html>
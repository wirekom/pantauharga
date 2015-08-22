<html>
<head>
<meta name='layout' content='frontend' />
<title><g:message code="springSecurity.login.title" /></title>
</head>

<body>
	<div class="row">
		<div class="col-md-4 col-md-offset-4" style="padding: 100px 0px 0px;">
			<div class="login-panel panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title">
						<i class="fa fa-fw fa-sign-in"></i>
						<g:message code="springSecurity.login.header" />
					</h3>
				</div>
				<div class="panel-body">

					<g:if test='${flash.message}'>
						<div class="alert alert-danger">
							<strong>Oh snap!</strong>
							${flash.message}
						</div>
					</g:if>

					<form action='${postUrl}' method='POST' id='loginForm'
						class='cssform' autocomplete='off'>

						<div class="form-group input-group">
							<span class="input-group-addon"><i class="fa fa-user"></i></span>
							<g:textField class="form-control" name='j_username' id='username'
								placeholder="${message(code: 'springSecurity.login.username.label')} " />
						</div>

						<div class="form-group input-group">
							<span class="input-group-addon"><i class="fa fa-lock"></i></span>
							<g:passwordField class="form-control" name='j_password'
								id='password'
								placeholder="${message(code: 'springSecurity.login.password.label')} " />
						</div>

						<div class="form-group">
							<label for='remember_me'> <g:message
									code="springSecurity.login.remember.me.label" /></label> <input
								type='checkbox' class='checkbox-inline'
								name='${rememberMeParameter}' id='remember_me'
								<g:if test='${hasCookie}'>checked='checked'</g:if> />
						</div>

						<g:submitButton id="submit" name="submit"
							class="btn btn-primary pull-right"
							value="${message(code: "springSecurity.login.button")}" />
					</form>
				</div>
			</div>
		</div>
	</div>
	<script type='text/javascript'>
	<!--
		(function() {
			document.forms['loginForm'].elements['j_username'].focus();
		})();
	// -->
	</script>
</body>
</html>

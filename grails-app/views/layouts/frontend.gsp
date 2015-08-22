<!DOCTYPE html>
<html>
<head>
<title><g:layoutTitle default="Pantau Harga Application" /></title>
<asset:stylesheet src="application.css" />
<g:layoutHead />
</head>
<body>
	<!-- Navigation -->
	<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target=".navbar-ex1-collapse">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="${createLink(uri: '/')}">Pantau Harga App</a>
		</div>
		<!-- Top Menu Items -->
		<ul class="nav navbar-right top-nav">
			<sec:ifLoggedIn>
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown"><i class="fa fa-user"></i> <sec:loggedInUserInfo
							field="username" /> <b class="caret"></b></a>
					<ul class="dropdown-menu">
						<li><a href="#"><i class="fa fa-fw fa-user"></i> Profile</a>
						</li>
						<li><a href="#"><i class="fa fa-fw fa-envelope"></i>
								Inbox</a></li>
						<li><a href="#"><i class="fa fa-fw fa-gear"></i> Settings</a></li>
						<li class="divider"></li>
						<li><g:remoteLink class="logout" controller="logout"
								method="post" asynchronous="false" onSuccess="location.reload()">
								<i class="fa fa-fw fa-sign-out"></i> Logout</g:remoteLink></li>
					</ul></li>
			</sec:ifLoggedIn>
			<sec:ifNotLoggedIn>
				<li><g:link controller='login' action='auth'>
						<i class="fa fa-fw fa-sign-in"></i> Login</g:link></li>
			</sec:ifNotLoggedIn>
		</ul>
	</nav>


	<div class="container-fluid">
		<g:layoutBody />
	</div>
	<!-- /.container-fluid -->

	<asset:javascript src="application.js" />
</body>
</html>
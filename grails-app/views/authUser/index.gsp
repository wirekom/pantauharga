

<%@ page import="com.pantau.user.AuthUser" %>
<!DOCTYPE html>
<html>
<head>
<meta name="layout" content="main">
<g:set var="entityName"
	value="${message(code: 'authUser.label', default: 'AuthUser')}" />
<title><g:message code="default.list.label" args="[entityName]" /></title>
</head>
<body>
	<!-- Page Heading -->
	<div class="row">
		<div class="col-lg-12">
			<ol class="breadcrumb">
				<li><i class="fa fa-dashboard"></i> <a class="home"
					href="${createLink(uri: '/')}"><g:message
							code="default.home.label" /></a></li>
				<li class="active"><i class="fa fa-plus"></i> <g:link
						class="create" action="create">
						<g:message code="default.new.label" args="[entityName]" />
					</g:link></li>
			</ol>
		</div>
	</div>
	<!-- /.row -->

	<div class="row">
		<div class="col-lg-12">
			<g:if test="${flash.message}">
				<div class="alert alert-info" role="status">${flash.message}</div>
			</g:if>
			<div class="panel panel-primary">
				<div class="panel-heading">
					<h3 class="panel-title">
						<i class="fa fa-list"></i>
						<g:message code="default.list.label" args="[entityName]" />
					</h3>
				</div>
				<table class="table table-striped table-bordered">
					<thead>
						<tr>
							
								<g:sortableColumn property="username" title="${message(code: 'authUser.username.label', default: 'Username')}" />
							
								<g:sortableColumn property="password" title="${message(code: 'authUser.password.label', default: 'Password')}" />
							
								<g:sortableColumn property="email" title="${message(code: 'authUser.email.label', default: 'Email')}" />
							
								<g:sortableColumn property="accountExpired" title="${message(code: 'authUser.accountExpired.label', default: 'Account Expired')}" />
							
								<g:sortableColumn property="accountLocked" title="${message(code: 'authUser.accountLocked.label', default: 'Account Locked')}" />
							
								<g:sortableColumn property="enabled" title="${message(code: 'authUser.enabled.label', default: 'Enabled')}" />
							
						</tr>
					</thead>
					<tbody>
						<g:each in="${authUserInstanceList}" status="i"
							var="authUserInstance">
							<tr>
								
								<td><g:link action="show" id="${authUserInstance.id}">${fieldValue(bean: authUserInstance, field: "username")}</g:link></td>
							
								<td>${fieldValue(bean: authUserInstance, field: "password")}</td>
							
								<td>${fieldValue(bean: authUserInstance, field: "email")}</td>
							
								<td><g:formatBoolean boolean="${authUserInstance.accountExpired}" /></td>
							
								<td><g:formatBoolean boolean="${authUserInstance.accountLocked}" /></td>
							
								<td><g:formatBoolean boolean="${authUserInstance.enabled}" /></td>
							
							</tr>
						</g:each>
					</tbody>
				</table>
				<div class="clearfix"></div>
				<g:paginate total="${authUserInstanceCount ?: 0}" class="pull-right" />
			</div>
		</div>
	</div>
</body>
</html>

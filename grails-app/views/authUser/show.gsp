
<%@ page import="com.pantau.user.AuthUser" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'authUser.label', default: 'AuthUser')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<!-- Page Heading -->
		<div class="row">
			<div class="col-lg-12">
				<ol class="breadcrumb">
					<li><i class="fa fa-dashboard"></i> <a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
					<li class="active"><i class="fa fa-list"></i> <g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
					<li class="active"><i class="fa fa-plus"></i> <g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
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
							<i class="fa fa-eye"></i>
							<g:message code="default.show.label" args="[entityName]" />
						</h3>
					</div>
					<table class="table table-striped table-bordered detail-view authUser">
					
						<g:if test="${authUserInstance?.username}">
						<tr><th>
							<span id="username-label" class="property-label"><g:message code="authUser.username.label" default="Username" /></span></th><td>
							
								<span class="property-value" aria-labelledby="username-label"><g:fieldValue bean="${authUserInstance}" field="username"/></span>
							
						</td></tr>
						</g:if>
					
						<g:if test="${authUserInstance?.email}">
						<tr><th>
							<span id="email-label" class="property-label"><g:message code="authUser.email.label" default="Email" /></span></th><td>
							
								<span class="property-value" aria-labelledby="email-label"><g:fieldValue bean="${authUserInstance}" field="email"/></span>
							
						</td></tr>
						</g:if>

						<g:if test="${authUserInstance?.ktp}">
							<tr><th>
								<span id="ktp-label" class="property-label"><g:message code="authUser.ktp.label"
																					   default="Ktp"/></span></th><td>

								<span class="property-value" aria-labelledby="ktp-label"><g:fieldValue
										bean="${authUserInstance}" field="ktp"/></span>

							</td></tr>
						</g:if>

						<g:if test="${authUserInstance?.nohp}">
							<tr><th>
								<span id="nohp-label" class="property-label"><g:message code="authUser.nohp.label"
																						default="Nohp"/></span></th><td>

								<span class="property-value" aria-labelledby="nohp-label"><g:fieldValue
										bean="${authUserInstance}" field="nohp"/></span>

							</td></tr>
						</g:if>
					
						<g:if test="${authUserInstance?.enabled}">
						<tr><th>
							<span id="enabled-label" class="property-label"><g:message code="authUser.enabled.label" default="Enabled" /></span></th><td>
							
								<span class="property-value" aria-labelledby="enabled-label"><g:formatBoolean boolean="${authUserInstance?.enabled}" /></span>
							
						</td></tr>
						</g:if>
					
					</table>
					<div class="clearfix"></div>
					<div class="panel-footer">
					<g:form url="[resource:authUserInstance, action:'delete']" method="DELETE">
						<fieldset class="buttons">
							<g:link class="btn btn-primary edit" action="edit" resource="${authUserInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
							<g:actionSubmit class="btn btn-primary delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
						</fieldset>
					</g:form>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>

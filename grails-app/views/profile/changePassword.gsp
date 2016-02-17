<%@ page import="com.pantau.user.ChangePassword"%>

<!DOCTYPE html>
<html>
<head>
<meta name="layout" content="main">
<g:set var="entityName"
	value="${message(code: 'changePassword.label', default: 'Change Password')}" />
<title><g:message code="default.create.label"
		args="[entityName]" /></title>
</head>
<body>
	<!-- Page Heading -->
	<div class="row">
		<div class="col-lg-12">
			<ol class="breadcrumb">
				<li><i class="fa fa-dashboard"></i> <a class="home"
					href="${createLink(uri: '/')}"><g:message
							code="default.home.label" /></a></li>
			</ol>
		</div>
	</div>
	<!-- /.row -->

	<div class="row">
		<div class="col-lg-12">
			<g:if test="${flash.message}">
				<div class="alert alert-info" role="status">
					${flash.message}
				</div>
			</g:if>
			<g:hasErrors bean="${changePasswordInstance}">
				<ul class="alert alert-danger" role="alert">
					<g:eachError bean="${changePasswordInstance}" var="error">
						<li><g:message
								error="${error}" /></li>
					</g:eachError>
				</ul>
			</g:hasErrors>
			<g:form url="[controller:'profile', action:'change']"
				role="form">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h3 class="panel-title">
							<i class="fa fa-pencil"></i>
							<g:message code="default.edit.label" args="[entityName]" />
						</h3>
					</div>

					<div class="panel-body">
						<div
							class="form-group ${hasErrors(bean: changePasswordInstance, field: 'oldPassword', 'error')} required">
							<label for="oldPassword"> <g:message
									code="changePassword.oldPassword.label" default="Old Password" />
								<span class="required-indicator">*</span>
							</label>
							<g:textField class="form-control" name="oldPassword"
								value="${changePasswordInstance?.oldPassword}" />

						</div>
						<div
							class="form-group ${hasErrors(bean: changePasswordInstance, field: 'newPassword', 'error')} required">
							<label for="newPassword"> <g:message
									code="changePassword.newPassword.label" default="New Password" />
								<span class="required-indicator">*</span>
							</label>
							<g:textField class="form-control" name="newPassword"
								value="${changePasswordInstance?.newPassword}" />

						</div>
						<div
							class="form-group ${hasErrors(bean: changePasswordInstance, field: 'retypePassword', 'error')} required">
							<label for="retypePassword"> <g:message
									code="changePassword.retypePassword.label"
									default="Retype Password" /> <span class="required-indicator">*</span>
							</label>
							<g:textField class="form-control" name="retypePassword"
								value="${changePasswordInstance?.retypePassword}" />

						</div>
					</div>

					<div class="panel-footer">
						<fieldset class="buttons">
							<g:submitButton name="create" class="btn btn-primary change"
								value="${message(code: 'default.button.change.label', default: 'Change')}" />
						</fieldset>
					</div>
				</div>
			</g:form>
			<div class="clearfix"></div>
		</div>
	</div>
</body>
</html>


<%@ page import="com.pantau.core.ComodityInput" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'comodityInput.label', default: 'ComodityInput')}" />
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
					<table class="table table-striped table-bordered detail-view comodityInput">
					
						<g:if test="${comodityInputInstance?.price}">
						<tr><th>
							<span id="price-label" class="property-label"><g:message code="comodityInput.price.label" default="Price" /></span></th><td>
							
								<span class="property-value" aria-labelledby="price-label"><g:fieldValue bean="${comodityInputInstance}" field="price"/></span>
							
						</td></tr>
						</g:if>
					
						<g:if test="${comodityInputInstance?.inputDate}">
						<tr><th>
							<span id="inputDate-label" class="property-label"><g:message code="comodityInput.inputDate.label" default="Input Date" /></span></th><td>
							
								<span class="property-value" aria-labelledby="inputDate-label"><g:formatDate date="${comodityInputInstance?.inputDate}" /></span>
							
						</td></tr>
						</g:if>
					
						<g:if test="${comodityInputInstance?.comodityName}">
						<tr><th>
							<span id="comodityName-label" class="property-label"><g:message code="comodityInput.comodityName.label" default="Comodity Name" /></span></th><td>
							
								<span class="property-value" aria-labelledby="comodityName-label"><g:link controller="comodity" action="show" id="${comodityInputInstance?.comodityName?.id}">${comodityInputInstance?.comodityName?.encodeAsHTML()}</g:link></span>
							
						</td></tr>
						</g:if>
					
						<g:if test="${comodityInputInstance?.geoTag}">
						<tr><th>
							<span id="geoTag-label" class="property-label"><g:message code="comodityInput.geoTag.label" default="Geo Tag" /></span></th><td>
							
								<span class="property-value" aria-labelledby="geoTag-label"><g:fieldValue bean="${comodityInputInstance}" field="geoTag"/></span>
							
						</td></tr>
						</g:if>
					
						<g:if test="${comodityInputInstance?.region}">
						<tr><th>
							<span id="region-label" class="property-label"><g:message code="comodityInput.region.label" default="Region" /></span></th><td>
							
								<span class="property-value" aria-labelledby="region-label"><g:link controller="region" action="show" id="${comodityInputInstance?.region?.id}">${comodityInputInstance?.region?.encodeAsHTML()}</g:link></span>
							
						</td></tr>
						</g:if>
					
						<g:if test="${comodityInputInstance?.user}">
						<tr><th>
							<span id="user-label" class="property-label"><g:message code="comodityInput.user.label" default="User" /></span></th><td>
							
								<span class="property-value" aria-labelledby="user-label"><g:link controller="authUser" action="show" id="${comodityInputInstance?.user?.id}">${comodityInputInstance?.user?.encodeAsHTML()}</g:link></span>
							
						</td></tr>
						</g:if>
					
					</table>
					<div class="clearfix"></div>
					<div class="panel-footer">
					<g:form url="[resource:comodityInputInstance, action:'delete']" method="DELETE">
						<fieldset class="buttons">
							<g:link class="btn btn-primary edit" action="edit" resource="${comodityInputInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
							<g:actionSubmit class="btn btn-primary delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
						</fieldset>
					</g:form>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>

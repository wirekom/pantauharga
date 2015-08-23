
<%@ page import="com.pantau.core.Comodity" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'comodity.label', default: 'Comodity')}" />
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
					<table class="table table-striped table-bordered detail-view comodity">
					
						<g:if test="${comodityInstance?.name}">
						<tr><th>
							<span id="name-label" class="property-label"><g:message code="comodity.name.label" default="Name" /></span></th><td>
							
								<span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${comodityInstance}" field="name"/></span>
							
						</td></tr>
						</g:if>
					
						<g:if test="${comodityInstance?.comodityList}">
						<tr><th>
							<span id="comodityList-label" class="property-label"><g:message code="comodity.comodityList.label" default="Comodity List" /></span></th><td>
							
								<g:each in="${comodityInstance.comodityList}" var="c">
								<span class="property-value" aria-labelledby="comodityList-label"><g:link controller="comodityInput" action="show" id="${c.id}">${c?.encodeAsHTML()}</g:link></span>
								</g:each>
							
						</td></tr>
						</g:if>
					
						<g:if test="${comodityInstance?.comodityType}">
						<tr><th>
							<span id="comodityType-label" class="property-label"><g:message code="comodity.comodityType.label" default="Comodity Type" /></span></th><td>
							
								<span class="property-value" aria-labelledby="comodityType-label"><g:link controller="comodityType" action="show" id="${comodityInstance?.comodityType?.id}">${comodityInstance?.comodityType?.encodeAsHTML()}</g:link></span>
							
						</td></tr>
						</g:if>
					
						<g:if test="${comodityInstance?.sku}">
						<tr><th>
							<span id="sku-label" class="property-label"><g:message code="comodity.sku.label" default="Sku" /></span></th><td>
							
								<span class="property-value" aria-labelledby="sku-label"><g:fieldValue bean="${comodityInstance}" field="sku"/></span>
							
						</td></tr>
						</g:if>
					
					</table>
					<div class="clearfix"></div>
					<div class="panel-footer">
					<g:form url="[resource:comodityInstance, action:'delete']" method="DELETE">
						<fieldset class="buttons">
							<g:link class="btn btn-primary edit" action="edit" resource="${comodityInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
							<g:actionSubmit class="btn btn-primary delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
						</fieldset>
					</g:form>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>

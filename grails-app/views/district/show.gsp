
<%@ page import="com.pantau.core.District" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'district.label', default: 'District')}" />
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
					<table class="table table-striped table-bordered detail-view district">
					
						<g:if test="${districtInstance?.description}">
						<tr><th>
							<span id="description-label" class="property-label"><g:message code="district.description.label" default="Description" /></span></th><td>
							
								<span class="property-value" aria-labelledby="description-label"><g:fieldValue bean="${districtInstance}" field="description"/></span>
							
						</td></tr>
						</g:if>
					
						<g:if test="${districtInstance?.geometry}">
						<tr><th>
							<span id="geometry-label" class="property-label"><g:message code="district.geometry.label" default="Geometry" /></span></th><td>
							
						</td></tr>
						</g:if>
					
						<g:if test="${districtInstance?.dateCreated}">
						<tr><th>
							<span id="dateCreated-label" class="property-label"><g:message code="district.dateCreated.label" default="Date Created" /></span></th><td>
							
								<span class="property-value" aria-labelledby="dateCreated-label"><g:formatDate date="${districtInstance?.dateCreated}" /></span>
							
						</td></tr>
						</g:if>
					
						<g:if test="${districtInstance?.lastUpdated}">
						<tr><th>
							<span id="lastUpdated-label" class="property-label"><g:message code="district.lastUpdated.label" default="Last Updated" /></span></th><td>
							
								<span class="property-value" aria-labelledby="lastUpdated-label"><g:formatDate date="${districtInstance?.lastUpdated}" /></span>
							
						</td></tr>
						</g:if>
					
						<g:if test="${districtInstance?.name}">
						<tr><th>
							<span id="name-label" class="property-label"><g:message code="district.name.label" default="Name" /></span></th><td>
							
								<span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${districtInstance}" field="name"/></span>
							
						</td></tr>
						</g:if>
					
						<g:if test="${districtInstance?.province}">
						<tr><th>
							<span id="province-label" class="property-label"><g:message code="district.province.label" default="Province" /></span></th><td>
							
								<span class="property-value" aria-labelledby="province-label"><g:link controller="province" action="show" id="${districtInstance?.province?.id}">${districtInstance?.province?.encodeAsHTML()}</g:link></span>
							
						</td></tr>
						</g:if>
					
					</table>
					<div class="clearfix"></div>
					<div class="panel-footer">
					<g:form url="[resource:districtInstance, action:'delete']" method="DELETE">
						<fieldset class="buttons">
							<g:link class="btn btn-primary edit" action="edit" resource="${districtInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
							<g:actionSubmit class="btn btn-primary delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
						</fieldset>
					</g:form>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>

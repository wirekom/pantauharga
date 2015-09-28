

<%@ page import="com.pantau.core.District" %>
<!DOCTYPE html>
<html>
<head>
<meta name="layout" content="main">
<g:set var="entityName"
	value="${message(code: 'district.label', default: 'District')}" />
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
							
								<g:sortableColumn property="description" title="${message(code: 'district.description.label', default: 'Description')}" />
							
								<g:sortableColumn property="geometry" title="${message(code: 'district.geometry.label', default: 'Geometry')}" />
							
								<g:sortableColumn property="dateCreated" title="${message(code: 'district.dateCreated.label', default: 'Date Created')}" />
							
								<g:sortableColumn property="lastUpdated" title="${message(code: 'district.lastUpdated.label', default: 'Last Updated')}" />
							
								<g:sortableColumn property="name" title="${message(code: 'district.name.label', default: 'Name')}" />
							
								<th><g:message code="district.province.label" default="Province" /></th>
							
						</tr>
					</thead>
					<tbody>
						<g:each in="${districtInstanceList}" status="i"
							var="districtInstance">
							<tr>
								
								<td><g:link action="show" id="${districtInstance.id}">${fieldValue(bean: districtInstance, field: "description")}</g:link></td>
							
								<td>${fieldValue(bean: districtInstance, field: "geometry")}</td>
							
								<td><g:formatDate date="${districtInstance.dateCreated}" /></td>
							
								<td><g:formatDate date="${districtInstance.lastUpdated}" /></td>
							
								<td>${fieldValue(bean: districtInstance, field: "name")}</td>
							
								<td>${fieldValue(bean: districtInstance, field: "province")}</td>
							
							</tr>
						</g:each>
					</tbody>
				</table>
				<div class="clearfix"></div>
				<g:paginate total="${districtInstanceCount ?: 0}" class="pull-right" />
			</div>
		</div>
	</div>
</body>
</html>

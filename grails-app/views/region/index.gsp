

<%@ page import="com.pantau.core.Region" %>
<!DOCTYPE html>
<html>
<head>
<meta name="layout" content="main">
<g:set var="entityName"
	value="${message(code: 'region.label', default: 'Region')}" />
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
							
								<g:sortableColumn property="name" title="${message(code: 'region.name.label', default: 'Name')}" />
							
								<g:sortableColumn property="geolocation" title="${message(code: 'region.geolocation.label', default: 'Geolocation')}" />
							
								<th><g:message code="region.parent.label" default="Parent" /></th>
							
						</tr>
					</thead>
					<tbody>
						<g:each in="${regionInstanceList}" status="i"
							var="regionInstance">
							<tr>
								
								<td><g:link action="show" id="${regionInstance.id}">${fieldValue(bean: regionInstance, field: "name")}</g:link></td>
							
								<td>${fieldValue(bean: regionInstance, field: "geolocation")}</td>
							
								<td>${fieldValue(bean: regionInstance, field: "parent")}</td>
							
							</tr>
						</g:each>
					</tbody>
				</table>
				<div class="clearfix"></div>
				<g:paginate total="${regionInstanceCount ?: 0}" class="pull-right" />
			</div>
		</div>
	</div>
</body>
</html>

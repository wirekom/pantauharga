

<%@ page import="com.pantau.core.Pasar" %>
<!DOCTYPE html>
<html>
<head>
<meta name="layout" content="main">
<g:set var="entityName"
	value="${message(code: 'pasar.label', default: 'Pasar')}" />
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
							
								<g:sortableColumn property="lat" title="${message(code: 'pasar.lat.label', default: 'Lat')}" />
							
								<g:sortableColumn property="lng" title="${message(code: 'pasar.lng.label', default: 'Lng')}" />
							
								<g:sortableColumn property="nama" title="${message(code: 'pasar.nama.label', default: 'Nama')}" />
							
						</tr>
					</thead>
					<tbody>
						<g:each in="${pasarInstanceList}" status="i"
							var="pasarInstance">
							<tr>
								
								<td><g:link action="show" id="${pasarInstance.id}">${fieldValue(bean: pasarInstance, field: "lat")}</g:link></td>
							
								<td>${fieldValue(bean: pasarInstance, field: "lng")}</td>
							
								<td>${fieldValue(bean: pasarInstance, field: "nama")}</td>
							
							</tr>
						</g:each>
					</tbody>
				</table>
				<div class="clearfix"></div>
				<g:paginate total="${pasarInstanceCount ?: 0}" class="pull-right" />
			</div>
		</div>
	</div>
</body>
</html>

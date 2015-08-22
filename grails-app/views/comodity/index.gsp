
<%@ page import="com.pantau.core.Comodity" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'comodity.label', default: 'Comodity')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-comodity" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-comodity" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<g:sortableColumn property="name" title="${message(code: 'comodity.name.label', default: 'Name')}" />
					
						<g:sortableColumn property="weight" title="${message(code: 'comodity.weight.label', default: 'Weight')}" />
					
						<th><g:message code="comodity.comodityType.label" default="Comodity Type" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${comodityInstanceList}" status="i" var="comodityInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${comodityInstance.id}">${fieldValue(bean: comodityInstance, field: "name")}</g:link></td>
					
						<td>${fieldValue(bean: comodityInstance, field: "weight")}</td>
					
						<td>${fieldValue(bean: comodityInstance, field: "comodityType")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${comodityInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>

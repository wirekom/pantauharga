
<%@ page import="com.pantau.core.ComodityInput" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'comodityInput.label', default: 'ComodityInput')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-comodityInput" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-comodityInput" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<g:sortableColumn property="price" title="${message(code: 'comodityInput.price.label', default: 'Price')}" />
					
						<g:sortableColumn property="inputDate" title="${message(code: 'comodityInput.inputDate.label', default: 'Input Date')}" />
					
						<th><g:message code="comodityInput.comodityName.label" default="Comodity Name" /></th>
					
						<g:sortableColumn property="geoTag" title="${message(code: 'comodityInput.geoTag.label', default: 'Geo Tag')}" />
					
						<th><g:message code="comodityInput.region.label" default="Region" /></th>
					
						<th><g:message code="comodityInput.user.label" default="User" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${comodityInputInstanceList}" status="i" var="comodityInputInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${comodityInputInstance.id}">${fieldValue(bean: comodityInputInstance, field: "price")}</g:link></td>
					
						<td><g:formatDate date="${comodityInputInstance.inputDate}" /></td>
					
						<td>${fieldValue(bean: comodityInputInstance, field: "comodityName")}</td>
					
						<td>${fieldValue(bean: comodityInputInstance, field: "geoTag")}</td>
					
						<td>${fieldValue(bean: comodityInputInstance, field: "region")}</td>
					
						<td>${fieldValue(bean: comodityInputInstance, field: "user")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${comodityInputInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>

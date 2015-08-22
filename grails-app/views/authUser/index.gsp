
<%@ page import="com.pantau.user.AuthUser" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'authUser.label', default: 'AuthUser')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-authUser" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-authUser" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<g:sortableColumn property="username" title="${message(code: 'authUser.username.label', default: 'Username')}" />
					
						<g:sortableColumn property="password" title="${message(code: 'authUser.password.label', default: 'Password')}" />
					
						<g:sortableColumn property="email" title="${message(code: 'authUser.email.label', default: 'Email')}" />
					
						<g:sortableColumn property="accountExpired" title="${message(code: 'authUser.accountExpired.label', default: 'Account Expired')}" />
					
						<g:sortableColumn property="accountLocked" title="${message(code: 'authUser.accountLocked.label', default: 'Account Locked')}" />
					
						<g:sortableColumn property="enabled" title="${message(code: 'authUser.enabled.label', default: 'Enabled')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${authUserInstanceList}" status="i" var="authUserInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${authUserInstance.id}">${fieldValue(bean: authUserInstance, field: "username")}</g:link></td>
					
						<td>${fieldValue(bean: authUserInstance, field: "password")}</td>
					
						<td>${fieldValue(bean: authUserInstance, field: "email")}</td>
					
						<td><g:formatBoolean boolean="${authUserInstance.accountExpired}" /></td>
					
						<td><g:formatBoolean boolean="${authUserInstance.accountLocked}" /></td>
					
						<td><g:formatBoolean boolean="${authUserInstance.enabled}" /></td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${authUserInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>

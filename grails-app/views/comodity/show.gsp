
<%@ page import="com.pantau.core.Comodity" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'comodity.label', default: 'Comodity')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-comodity" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-comodity" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list comodity">
			
				<g:if test="${comodityInstance?.name}">
				<li class="fieldcontain">
					<span id="name-label" class="property-label"><g:message code="comodity.name.label" default="Name" /></span>
					
						<span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${comodityInstance}" field="name"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${comodityInstance?.weight}">
				<li class="fieldcontain">
					<span id="weight-label" class="property-label"><g:message code="comodity.weight.label" default="Weight" /></span>
					
						<span class="property-value" aria-labelledby="weight-label"><g:fieldValue bean="${comodityInstance}" field="weight"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${comodityInstance?.comodityList}">
				<li class="fieldcontain">
					<span id="comodityList-label" class="property-label"><g:message code="comodity.comodityList.label" default="Comodity List" /></span>
					
						<g:each in="${comodityInstance.comodityList}" var="c">
						<span class="property-value" aria-labelledby="comodityList-label"><g:link controller="comodityInput" action="show" id="${c.id}">${c?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${comodityInstance?.comodityType}">
				<li class="fieldcontain">
					<span id="comodityType-label" class="property-label"><g:message code="comodity.comodityType.label" default="Comodity Type" /></span>
					
						<span class="property-value" aria-labelledby="comodityType-label"><g:link controller="comodityType" action="show" id="${comodityInstance?.comodityType?.id}">${comodityInstance?.comodityType?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:comodityInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${comodityInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>

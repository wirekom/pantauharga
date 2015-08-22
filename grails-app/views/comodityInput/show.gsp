
<%@ page import="com.pantau.core.ComodityInput" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'comodityInput.label', default: 'ComodityInput')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-comodityInput" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-comodityInput" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list comodityInput">
			
				<g:if test="${comodityInputInstance?.price}">
				<li class="fieldcontain">
					<span id="price-label" class="property-label"><g:message code="comodityInput.price.label" default="Price" /></span>
					
						<span class="property-value" aria-labelledby="price-label"><g:fieldValue bean="${comodityInputInstance}" field="price"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${comodityInputInstance?.inputDate}">
				<li class="fieldcontain">
					<span id="inputDate-label" class="property-label"><g:message code="comodityInput.inputDate.label" default="Input Date" /></span>
					
						<span class="property-value" aria-labelledby="inputDate-label"><g:formatDate date="${comodityInputInstance?.inputDate}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${comodityInputInstance?.comodityName}">
				<li class="fieldcontain">
					<span id="comodityName-label" class="property-label"><g:message code="comodityInput.comodityName.label" default="Comodity Name" /></span>
					
						<span class="property-value" aria-labelledby="comodityName-label"><g:link controller="comodity" action="show" id="${comodityInputInstance?.comodityName?.id}">${comodityInputInstance?.comodityName?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${comodityInputInstance?.geoTag}">
				<li class="fieldcontain">
					<span id="geoTag-label" class="property-label"><g:message code="comodityInput.geoTag.label" default="Geo Tag" /></span>
					
						<span class="property-value" aria-labelledby="geoTag-label"><g:fieldValue bean="${comodityInputInstance}" field="geoTag"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${comodityInputInstance?.region}">
				<li class="fieldcontain">
					<span id="region-label" class="property-label"><g:message code="comodityInput.region.label" default="Region" /></span>
					
						<span class="property-value" aria-labelledby="region-label"><g:link controller="region" action="show" id="${comodityInputInstance?.region?.id}">${comodityInputInstance?.region?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${comodityInputInstance?.user}">
				<li class="fieldcontain">
					<span id="user-label" class="property-label"><g:message code="comodityInput.user.label" default="User" /></span>
					
						<span class="property-value" aria-labelledby="user-label"><g:link controller="authUser" action="show" id="${comodityInputInstance?.user?.id}">${comodityInputInstance?.user?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:comodityInputInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${comodityInputInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>

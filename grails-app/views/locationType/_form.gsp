<%@ page import="com.pantau.core.LocationType" %>



<div class="fieldcontain ${hasErrors(bean: locationTypeInstance, field: 'logo', 'error')} required">
	<label for="logo">
		<g:message code="locationType.logo.label" default="Logo" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="logo" required="" value="${locationTypeInstance?.logo}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: locationTypeInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="locationType.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" required="" value="${locationTypeInstance?.name}"/>

</div>


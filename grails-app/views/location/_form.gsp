<%@ page import="com.pantau.core.Location" %>



<div class="fieldcontain ${hasErrors(bean: locationInstance, field: 'geolocation', 'error')} required">
	<label for="geolocation">
		<g:message code="location.geolocation.label" default="Geolocation" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="geolocation" required="" value="${locationInstance?.geolocation}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: locationInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="location.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" required="" value="${locationInstance?.name}"/>

</div>


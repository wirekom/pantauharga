<%@ page import="com.pantau.core.ComodityInput" %>



<div class="fieldcontain ${hasErrors(bean: comodityInputInstance, field: 'price', 'error')} required">
	<label for="price">
		<g:message code="comodityInput.price.label" default="Price" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="price" value="${fieldValue(bean: comodityInputInstance, field: 'price')}" required=""/>

</div>

<div class="fieldcontain ${hasErrors(bean: comodityInputInstance, field: 'inputDate', 'error')} required">
	<label for="inputDate">
		<g:message code="comodityInput.inputDate.label" default="Input Date" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="inputDate" precision="day"  value="${comodityInputInstance?.inputDate}"  />

</div>

<div class="fieldcontain ${hasErrors(bean: comodityInputInstance, field: 'comodityName', 'error')} required">
	<label for="comodityName">
		<g:message code="comodityInput.comodityName.label" default="Comodity Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="comodityName" name="comodityName.id" from="${com.pantau.core.Comodity.list()}" optionKey="id" required="" value="${comodityInputInstance?.comodityName?.id}" class="many-to-one"/>

</div>

<div class="fieldcontain ${hasErrors(bean: comodityInputInstance, field: 'geoTag', 'error')} required">
	<label for="geoTag">
		<g:message code="comodityInput.geoTag.label" default="Geo Tag" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="geoTag" required="" value="${comodityInputInstance?.geoTag}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: comodityInputInstance, field: 'region', 'error')} required">
	<label for="region">
		<g:message code="comodityInput.region.label" default="Region" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="region" name="region.id" from="${com.pantau.core.Region.list()}" optionKey="id" required="" value="${comodityInputInstance?.region?.id}" class="many-to-one"/>

</div>

<div class="fieldcontain ${hasErrors(bean: comodityInputInstance, field: 'user', 'error')} required">
	<label for="user">
		<g:message code="comodityInput.user.label" default="User" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="user" name="user.id" from="${com.pantau.user.AuthUser.list()}" optionKey="id" required="" value="${comodityInputInstance?.user?.id}" class="many-to-one"/>

</div>


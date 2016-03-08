<%@ page import="com.pantau.core.Pasar" %>



<div class="form-group ${hasErrors(bean: pasarInstance, field: 'lat', 'error')} required">
	<label for="lat">
		<g:message code="pasar.lat.label" default="Lat" />
		<span class="required-indicator">*</span>
	</label>
	<g:field class="form-control" name="lat" value="${fieldValue(bean: pasarInstance, field: 'lat')}" required=""/>

</div>

<div class="form-group ${hasErrors(bean: pasarInstance, field: 'lng', 'error')} required">
	<label for="lng">
		<g:message code="pasar.lng.label" default="Lng" />
		<span class="required-indicator">*</span>
	</label>
	<g:field class="form-control" name="lng" value="${fieldValue(bean: pasarInstance, field: 'lng')}" required=""/>

</div>

<div class="form-group ${hasErrors(bean: pasarInstance, field: 'nama', 'error')} required">
	<label for="nama">
		<g:message code="pasar.nama.label" default="Nama" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField class="form-control" name="nama" required="" value="${pasarInstance?.nama}"/>

</div>


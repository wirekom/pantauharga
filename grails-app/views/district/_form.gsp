<%@ page import="com.pantau.core.District" %>



<div class="form-group ${hasErrors(bean: districtInstance, field: 'description', 'error')} ">
	<label for="description">
		<g:message code="district.description.label" default="Description" />
		
	</label>
	<g:textField class="form-control" name="description" value="${districtInstance?.description}"/>

</div>

<div class="form-group ${hasErrors(bean: districtInstance, field: 'geometry', 'error')} ">
	<label for="geometry">
		<g:message code="district.geometry.label" default="Geometry" />
		
	</label>
	<input class="form-control" type="file" id="geometry" name="geometry" />

</div>

<div class="form-group ${hasErrors(bean: districtInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="district.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField class="form-control" name="name" required="" value="${districtInstance?.name}"/>

</div>

<div class="form-group ${hasErrors(bean: districtInstance, field: 'province', 'error')} required">
	<label for="province">
		<g:message code="district.province.label" default="Province" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="province" name="province.id" from="${com.pantau.core.Province.list()}" optionKey="id" required="" value="${districtInstance?.province?.id}" class="form-control many-to-one"/>

</div>


<%@ page import="com.pantau.core.Province" %>



<div class="form-group ${hasErrors(bean: provinceInstance, field: 'description', 'error')} ">
	<label for="description">
		<g:message code="province.description.label" default="Description" />
		
	</label>
	<g:textField class="form-control" name="description" value="${provinceInstance?.description}"/>

</div>

<div class="form-group ${hasErrors(bean: provinceInstance, field: 'geometry', 'error')} ">
	<label for="geometry">
		<g:message code="province.geometry.label" default="Geometry" />
		
	</label>
	<input class="form-control" type="file" id="geometry" name="geometry" />

</div>

<div class="form-group ${hasErrors(bean: provinceInstance, field: 'district', 'error')} ">
	<label for="district">
		<g:message code="province.district.label" default="District" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${provinceInstance?.district?}" var="d">
    <li><g:link controller="district" action="show" id="${d.id}">${d?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="district" action="create" params="['province.id': provinceInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'district.label', default: 'District')])}</g:link>
</li>
</ul>


</div>

<div class="form-group ${hasErrors(bean: provinceInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="province.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField class="form-control" name="name" required="" value="${provinceInstance?.name}"/>

</div>


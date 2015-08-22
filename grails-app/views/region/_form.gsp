<%@ page import="com.pantau.core.Region" %>



<div class="form-group ${hasErrors(bean: regionInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="region.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField class="form-control" name="name" required="" value="${regionInstance?.name}"/>

</div>

<div class="form-group ${hasErrors(bean: regionInstance, field: 'geolocation', 'error')} required">
	<label for="geolocation">
		<g:message code="region.geolocation.label" default="Geolocation" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField class="form-control" name="geolocation" required="" value="${regionInstance?.geolocation}"/>

</div>

<div class="form-group ${hasErrors(bean: regionInstance, field: 'children', 'error')} ">
	<label for="children">
		<g:message code="region.children.label" default="Children" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${regionInstance?.children?}" var="c">
    <li><g:link controller="region" action="show" id="${c.id}">${c?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="region" action="create" params="['region.id': regionInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'region.label', default: 'Region')])}</g:link>
</li>
</ul>


</div>

<div class="form-group ${hasErrors(bean: regionInstance, field: 'parent', 'error')} required">
	<label for="parent">
		<g:message code="region.parent.label" default="Parent" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="parent" name="parent.id" from="${com.pantau.core.Region.list()}" optionKey="id" required="" value="${regionInstance?.parent?.id}" class="form-control many-to-one"/>

</div>


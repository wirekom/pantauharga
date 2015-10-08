<%@ page import="com.pantau.core.ComodityType" %>



<div class="form-group ${hasErrors(bean: comodityTypeInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="comodityType.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField class="form-control" name="name" required="" value="${comodityTypeInstance?.name}"/>

</div>

<div class="form-group ${hasErrors(bean: comodityTypeInstance, field: 'comodity', 'error')} ">
	<label for="comodity">
		<g:message code="comodityType.comodity.label" default="Comodity" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${comodityTypeInstance?.comodity?}" var="c">
    <li><g:link controller="comodity" action="show" id="${c.id}">${c?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="comodity" action="create" params="['comodityType.id': comodityTypeInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'comodity.label', default: 'ComodityMarshaller')])}</g:link>
</li>
</ul>


</div>

<div class="form-group ${hasErrors(bean: comodityTypeInstance, field: 'weight', 'error')} required">
	<label for="weight">
		<g:message code="comodityType.weight.label" default="Weight" />
		<span class="required-indicator">*</span>
	</label>
	<g:field class="form-control" name="weight" value="${fieldValue(bean: comodityTypeInstance, field: 'weight')}" required=""/>

</div>


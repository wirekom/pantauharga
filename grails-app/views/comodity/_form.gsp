<%@ page import="com.pantau.core.Comodity" %>



<div class="form-group ${hasErrors(bean: comodityInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="comodity.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField class="form-control" name="name" required="" value="${comodityInstance?.name}"/>

</div>

<div class="form-group ${hasErrors(bean: comodityInstance, field: 'weight', 'error')} required">
	<label for="weight">
		<g:message code="comodity.weight.label" default="Weight" />
		<span class="required-indicator">*</span>
	</label>
	<g:field class="form-control" name="weight" type="number" value="${comodityInstance.weight}" required=""/>

</div>

<div class="form-group ${hasErrors(bean: comodityInstance, field: 'comodityList', 'error')} ">
	<label for="comodityList">
		<g:message code="comodity.comodityList.label" default="Comodity List" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${comodityInstance?.comodityList?}" var="c">
    <li><g:link controller="comodityInput" action="show" id="${c.id}">${c?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="comodityInput" action="create" params="['comodity.id': comodityInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'comodityInput.label', default: 'ComodityInput')])}</g:link>
</li>
</ul>


</div>

<div class="form-group ${hasErrors(bean: comodityInstance, field: 'comodityType', 'error')} required">
	<label for="comodityType">
		<g:message code="comodity.comodityType.label" default="Comodity Type" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="comodityType" name="comodityType.id" from="${com.pantau.core.ComodityType.list()}" optionKey="id" required="" value="${comodityInstance?.comodityType?.id}" class="form-control many-to-one"/>

</div>


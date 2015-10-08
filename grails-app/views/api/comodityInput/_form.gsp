<%@ page import="com.pantau.core.ComodityInput" %>



<div class="form-group ${hasErrors(bean: comodityInputInstance, field: 'price', 'error')} required">
    <label for="price">
        <g:message code="comodityInput.price.label" default="Price"/>
        <span class="required-indicator">*</span>
    </label>
    <g:field class="form-control" name="price" value="${fieldValue(bean: comodityInputInstance, field: 'price')}"
             required=""/>

</div>

<div class="form-group ${hasErrors(bean: comodityInputInstance, field: 'user', 'error')} ">
    <label for="user">
        <g:message code="comodityInput.user.label" default="User"/>

    </label>
    <g:select id="user" name="user.id" from="${com.pantau.user.AuthUser.list()}" optionKey="id"
              value="${comodityInputInstance?.user?.id}" class="form-control many-to-one" noSelection="['null': '']"/>

</div>

<div class="form-group ${hasErrors(bean: comodityInputInstance, field: 'region', 'error')} ">
    <label for="region">
        <g:message code="comodityInput.region.label" default="Region"/>

    </label>
    <g:select id="region" name="region.id" from="${com.pantau.core.Region.list()}" optionKey="id"
              value="${comodityInputInstance?.region?.id}" class="form-control many-to-one" noSelection="['null': '']"/>

</div>

<div class="form-group ${hasErrors(bean: comodityInputInstance, field: 'amount', 'error')} ">
    <label for="amount">
        <g:message code="comodityInput.amount.label" default="Amount"/>

    </label>
    <g:field class="form-control" name="amount" type="number" value="${comodityInputInstance.amount}"/>

</div>

<div class="form-group ${hasErrors(bean: comodityInputInstance, field: 'comodityName', 'error')} required">
    <label for="comodityName">
        <g:message code="comodityInput.comodityName.label" default="Comodity Name"/>
        <span class="required-indicator">*</span>
    </label>
    <g:select id="comodityName" name="comodityName.id" from="${com.pantau.core.Comodity.list()}" optionKey="id"
              required="" value="${comodityInputInstance?.comodityName?.id}" class="form-control many-to-one"/>

</div>

<div class="form-group ${hasErrors(bean: comodityInputInstance, field: 'delta', 'error')} required">
    <label for="delta">
        <g:message code="comodityInput.delta.label" default="Delta"/>
        <span class="required-indicator">*</span>
    </label>
    <g:field class="form-control" name="delta" value="${fieldValue(bean: comodityInputInstance, field: 'delta')}"
             required=""/>

</div>

<div class="form-group ${hasErrors(bean: comodityInputInstance, field: 'geoTag', 'error')} required">
    <label for="geoTag">
        <g:message code="comodityInput.geoTag.label" default="Geo Tag"/>
        <span class="required-indicator">*</span>
    </label>
    <g:textField class="form-control" name="geoTag" required="" value="${comodityInputInstance?.geoTag}"/>

</div>


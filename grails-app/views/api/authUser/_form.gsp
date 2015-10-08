<%@ page import="com.pantau.user.AuthUser" %>



<div class="form-group ${hasErrors(bean: authUserInstance, field: 'username', 'error')} required">
	<label for="username">
		<g:message code="authUser.username.label" default="Username" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField class="form-control" name="username" required="" value="${authUserInstance?.username}"/>

</div>

<div class="form-group ${hasErrors(bean: authUserInstance, field: 'password', 'error')} required">
	<label for="password">
		<g:message code="authUser.password.label" default="Password" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField class="form-control" name="password" required="" value="${authUserInstance?.password}"/>

</div>

<div class="form-group ${hasErrors(bean: authUserInstance, field: 'email', 'error')} required">
	<label for="email">
		<g:message code="authUser.email.label" default="Email" />
		<span class="required-indicator">*</span>
	</label>
	<g:field class="form-control" type="email" name="email" required="" value="${authUserInstance?.email}"/>

</div>

<div class="form-group ${hasErrors(bean: authUserInstance, field: 'accountExpired', 'error')} ">
	<label for="accountExpired">
		<g:message code="authUser.accountExpired.label" default="Account Expired" />
		
	</label>
	<g:checkBox class="checkbox-inline" name="accountExpired" value="${authUserInstance?.accountExpired}" />

</div>

<div class="form-group ${hasErrors(bean: authUserInstance, field: 'accountLocked', 'error')} ">
	<label for="accountLocked">
		<g:message code="authUser.accountLocked.label" default="Account Locked" />
		
	</label>
	<g:checkBox class="checkbox-inline" name="accountLocked" value="${authUserInstance?.accountLocked}" />

</div>

<div class="form-group ${hasErrors(bean: authUserInstance, field: 'enabled', 'error')} ">
	<label for="enabled">
		<g:message code="authUser.enabled.label" default="Enabled" />
		
	</label>
	<g:checkBox class="checkbox-inline" name="enabled" value="${authUserInstance?.enabled}" />

</div>

<div class="form-group ${hasErrors(bean: authUserInstance, field: 'ktp', 'error')} required">
	<label for="ktp">
		<g:message code="authUser.ktp.label" default="Ktp" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField class="form-control" name="ktp" required="" value="${authUserInstance?.ktp}"/>

</div>

<div class="form-group ${hasErrors(bean: authUserInstance, field: 'nohp', 'error')} required">
	<label for="nohp">
		<g:message code="authUser.nohp.label" default="Nohp" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField class="form-control" name="nohp" required="" value="${authUserInstance?.nohp}"/>

</div>

<div class="form-group ${hasErrors(bean: authUserInstance, field: 'passwordExpired', 'error')} ">
	<label for="passwordExpired">
		<g:message code="authUser.passwordExpired.label" default="Password Expired" />
		
	</label>
	<g:checkBox class="checkbox-inline" name="passwordExpired" value="${authUserInstance?.passwordExpired}" />

</div>


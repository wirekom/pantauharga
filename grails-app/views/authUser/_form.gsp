<%@ page import="com.pantau.user.AuthUser" %>



<div class="fieldcontain ${hasErrors(bean: authUserInstance, field: 'username', 'error')} required">
	<label for="username">
		<g:message code="authUser.username.label" default="Username" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="username" required="" value="${authUserInstance?.username}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: authUserInstance, field: 'password', 'error')} required">
	<label for="password">
		<g:message code="authUser.password.label" default="Password" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="password" required="" value="${authUserInstance?.password}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: authUserInstance, field: 'email', 'error')} required">
	<label for="email">
		<g:message code="authUser.email.label" default="Email" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="email" name="email" required="" value="${authUserInstance?.email}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: authUserInstance, field: 'accountExpired', 'error')} ">
	<label for="accountExpired">
		<g:message code="authUser.accountExpired.label" default="Account Expired" />
		
	</label>
	<g:checkBox name="accountExpired" value="${authUserInstance?.accountExpired}" />

</div>

<div class="fieldcontain ${hasErrors(bean: authUserInstance, field: 'accountLocked', 'error')} ">
	<label for="accountLocked">
		<g:message code="authUser.accountLocked.label" default="Account Locked" />
		
	</label>
	<g:checkBox name="accountLocked" value="${authUserInstance?.accountLocked}" />

</div>

<div class="fieldcontain ${hasErrors(bean: authUserInstance, field: 'enabled', 'error')} ">
	<label for="enabled">
		<g:message code="authUser.enabled.label" default="Enabled" />
		
	</label>
	<g:checkBox name="enabled" value="${authUserInstance?.enabled}" />

</div>

<div class="fieldcontain ${hasErrors(bean: authUserInstance, field: 'passwordExpired', 'error')} ">
	<label for="passwordExpired">
		<g:message code="authUser.passwordExpired.label" default="Password Expired" />
		
	</label>
	<g:checkBox name="passwordExpired" value="${authUserInstance?.passwordExpired}" />

</div>


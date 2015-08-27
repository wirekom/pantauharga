<%@ page import="com.pantau.user.AuthRole" %>



<div class="form-group ${hasErrors(bean: authRoleInstance, field: 'authority', 'error')} ">
	<label for="authority">
		<g:message code="authRole.authority.label" default="Authority" />
		
	</label>
	<g:textField class="form-control" name="authority" value="${authRoleInstance?.authority}" />

</div>


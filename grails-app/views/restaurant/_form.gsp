<%@ page import="com.pantau.test.Restaurant" %>



<div class="form-group ${hasErrors(bean: restaurantInstance, field: 'location', 'error')} ">
	<label for="location">
		<g:message code="restaurant.location.label" default="Location" />
		
	</label>
	

</div>

<div class="form-group ${hasErrors(bean: restaurantInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="restaurant.name.label" default="Name" />
		
	</label>
	<g:textField class="form-control" name="name" value="${restaurantInstance?.name}" />

</div>


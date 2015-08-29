<%@ page import="com.pantau.test.Hotel" %>



<div class="form-group ${hasErrors(bean: hotelInstance, field: 'location', 'error')} ">
	<label for="location">
		<g:message code="hotel.location.label" default="Location" />
		
	</label>
	

</div>

<div class="form-group ${hasErrors(bean: hotelInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="hotel.name.label" default="Name" />
		
	</label>
	<g:textField class="form-control" name="name" value="${hotelInstance?.name}" />

</div>


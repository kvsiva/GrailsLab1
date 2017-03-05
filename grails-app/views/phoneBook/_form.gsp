<%@ page import="grailslab1.PhoneBook" %>



<div class="fieldcontain ${hasErrors(bean: phoneBookInstance, field: 'firstName', 'error')} required">
	<label for="firstName">
		<g:message code="phoneBook.firstName.label" default="First Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="firstName" required="" value="${phoneBookInstance?.firstName}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: phoneBookInstance, field: 'lastName', 'error')} required">
	<label for="lastName">
		<g:message code="phoneBook.lastName.label" default="Last Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="lastName" required="" value="${phoneBookInstance?.lastName}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: phoneBookInstance, field: 'phoneNumber', 'error')} required">
	<label for="phoneNumber">
		<g:message code="phoneBook.phoneNumber.label" default="Phone Number" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="phoneNumber" required="" value="${phoneBookInstance?.phoneNumber}"/>

</div>


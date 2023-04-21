<%--
- form.jsp
-
- Copyright (C) 2012-2023 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://www.the-acme-framework.org/"%>

<acme:form>
	<acme:input-textbox code="authenticated.notes.form.label.title" path="title"/>
	<acme:input-textarea code="authenticated.notes.form.label.moment" path="moment" readonly="true"/>
	<acme:input-textarea code="authenticated.notes.form.label.author" path="author"/>
	<acme:input-textarea code="authenticated.notes.form.label.message" path="message"/>
	<acme:input-textarea code="authenticated.notes.form.label.email" path="email"/>
	<acme:input-url code="authenticated.notes.form.label.link" path="link"/>
	<jstl:if test="${!readonly}">
		<acme:input-checkbox code="authenticated.notes.form.label.confirmation" path="confirmation"/>
 		<acme:submit test="${_command == 'create'}" code="authenticated.notes.form.button.create" action="/authenticated/note/create"/>
 	</jstl:if>
</acme:form>
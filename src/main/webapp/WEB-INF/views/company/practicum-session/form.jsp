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
	<acme:input-textbox code="company.practicumSession.form.label.title" path="title"/>
	<acme:input-textarea code="company.practicumSession.form.label.abstract$" path="abstract$"/>
	<acme:input-moment code="company.practicumSession.form.label.sessionStartDate" path="sessionStartDate"/>
	<acme:input-moment code="company.practicumSession.form.label.sessionEndDate" path="sessionEndDate"/>
	<acme:input-url code="company.practicumSession.form.label.link" path="link"/>
	<acme:input-textbox code="company.practicumSession.form.label.is-published" path="isPublished" readonly="true"/>
	<acme:input-textbox code="company.practicumSession.form.label.is-addendum" path="isAddendum" readonly="true"/>
	<acme:input-select code="company.practicumSession.form.label.practicum" path="practicum" choices="${practicums}"/>
	
	<jstl:choose>	 
		<jstl:when test="${acme:anyOf(_command, 'show|update|delete|publish') && isPublished == false}">
			<acme:submit code="company.practicumSession.form.button.update" action="/company/practicum-session/update"/>
			<acme:submit code="company.practicumSession.form.button.delete" action="/company/practicum-session/delete"/>
			<acme:submit code="company.practicumSession.form.button.publish" action="/company/practicum-session/publish"/>
		</jstl:when>
		<jstl:when test="${_command == 'create'}">
			<acme:submit code="company.practicumSession.form.button.create" action="/company/practicum-session/create"/>
		</jstl:when>		
	</jstl:choose> 
</acme:form>

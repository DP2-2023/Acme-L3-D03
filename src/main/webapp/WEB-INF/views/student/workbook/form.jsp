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
	<acme:input-textbox code="student.course.form.label.code" path="code"/>
	<acme:input-textbox code="student.course.form.label.title" path="title"/>
	<acme:input-textarea code="student.course.form.label.abstract$" path="abstract$"/>
	<acme:input-textbox code="student.course.form.label.course-type" path="courseType"/>
	<acme:input-money code="student.course.form.label.price" path="price"/>
	<acme:input-url code="student.course.form.label.further-information" path="furtherInformation"/>
	<acme:input-textbox code="student.workbook.form.label.published" path="published" readonly="true"/>
	
	<jstl:choose>	 
		<jstl:when test="${acme:anyOf(_command, 'show|update|delete|publish') && published == false}">
			<acme:submit code="student.workbook.form.button.update" action="/student/workbook/update"/>
			<acme:submit code="student.workbook.form.button.delete" action="/student/workbook/delete"/>
			<acme:submit code="student.workbook.form.button.publish" action="/student/workbook/publish"/>
		</jstl:when>
		<jstl:when test="${_command == 'create'}">
			<acme:submit code="student.workbook.form.button.create" action="/student/workbook/create"/>
		</jstl:when>		
	</jstl:choose>
</acme:form>
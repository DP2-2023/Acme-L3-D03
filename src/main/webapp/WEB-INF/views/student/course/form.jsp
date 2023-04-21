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
	<acme:input-textbox code="student.course.form.label.code" path="code"/>
	<acme:input-textbox code="student.course.form.label.title" path="title"/>
	<acme:input-textarea code="student.course.form.label.abstract$" path="abstract$"/>
	<acme:input-textbox code="student.course.form.label.course-type" path="courseType"/>
	<acme:input-money code="student.course.form.label.price" path="price"/>
	<acme:input-url code="student.course.form.label.further-information" path="furtherInformation"/>
	<acme:input-textbox code="student.course.form.label.lecture-name" path="lecture.name"/>
<acme:input-textbox code="student.course.form.label.lecturer-name" path="lecturer.name"/>
</acme:form>
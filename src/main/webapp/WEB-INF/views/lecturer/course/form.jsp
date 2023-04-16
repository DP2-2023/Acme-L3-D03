<%--
- form.jsp
-
- Copyright (C) 2012-2023 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for lecturer particular
- purposes.  The copyright owner does not offer lecturer warranties or representations, nor do
- they accept lecturer liabilities with respect to them.
--%>

<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://www.the-acme-framework.org/"%>

<acme:form>
	<jstl:choose>	 
		<jstl:when test="${acme:anyOf(_command, 'show|create|update|delete|publish')}">
			<jstl:if test="${_command == 'create'}">
				<acme:input-textbox code="lecturer.course.form.label.code" path="code"/>
			</jstl:if>
			<jstl:if test="${acme:anyOf(_command, 'show|update|delete|publish')}">
				<acme:input-textbox code="lecturer.course.form.label.code" path="code" readonly="true"/>
			</jstl:if>
			<acme:input-textbox code="lecturer.course.form.label.title" path="title"/>
			<acme:input-textarea code="lecturer.course.form.label.abstract$" path="abstract$"/>
			<acme:input-textbox code="lecturer.course.form.label.type" path="type" readonly="true"/>
			<acme:input-money code="lecturer.course.form.label.price" path="price"/>
			<acme:input-url code="lecturer.course.form.label.further-information" path="furtherInformation"/>
			<jstl:if test="${acme:anyOf(_command, 'show|update|delete|publish')}">
				<acme:input-textbox code="lecturer.course.form.label.is-published" path="isPublished" readonly="true"/>
			</jstl:if>
			<jstl:if test="${_command == 'create'}">
				<acme:input-select code="lecturer.course.form.label.lecture" path="lecture" choices="${lectures}"/>
			</jstl:if>	
		
			<jstl:choose>	 
				<jstl:when test="${acme:anyOf(_command, 'show|update|delete|publish') && isPublished == false}">
					<acme:submit code="lecturer.course.form.button.update" action="/lecturer/course/update"/>
					<acme:submit code="lecturer.course.form.button.delete" action="/lecturer/course/delete"/>
					<acme:submit code="lecturer.course.form.button.publish" action="/lecturer/course/publish"/>
					<acme:button code="lecturer.course.form.button.add-lecture" action="/lecturer/course/add-lecture?id=${id}"/>
					<acme:button code="lecturer.course.form.button.remove-lecture" action="/lecturer/course/remove-lecture?id=${id}"/>
					<acme:button code="lecturer.course.form.button.list-lectures" action="/lecturer/lecture/list-mine?masterId=${id}"/>
				</jstl:when>
				<jstl:when test="${acme:anyOf(_command, 'show|update|delete|publish')}">
					<acme:button code="lecturer.course.form.button.list-lectures" action="/lecturer/lecture/list-mine?masterId=${id}"/>
				</jstl:when>
				<jstl:when test="${_command == 'create'}">
					<acme:submit code="lecturer.course.form.button.create" action="/lecturer/course/create"/>
				</jstl:when>
			</jstl:choose>
		</jstl:when>
		
		
		<jstl:when test="${acme:anyOf(_command, 'add-lecture|remove-lecture')}">
			<acme:input-textbox code="lecturer.course.form.label.code" path="code" readonly="true"/>
			<acme:input-textbox code="lecturer.course.form.label.title" path="title" readonly="true"/>
			<acme:input-select code="lecturer.course.form.label.lecture" path="lecture" choices="${lectures}"/>
			<jstl:choose>	 
				<jstl:when test="${_command == 'add-lecture'}">
					<acme:submit code="lecturer.course.form.button.add-lecture" action="/lecturer/course/add-lecture"/>
				</jstl:when>
				<jstl:when test="${_command == 'remove-lecture'}">
					<acme:submit code="lecturer.course.form.button.remove-lecture" action="/lecturer/course/remove-lecture"/>
				</jstl:when>		
			</jstl:choose>
		</jstl:when>
	</jstl:choose>
		
</acme:form>

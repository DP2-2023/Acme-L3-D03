<%--
- list.jsp
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

<acme:list>
	<acme:list-column code="lecturer.lecture.list.label.title" path="title" width="80%"/>
	<acme:list-column code="lecturer.lecture.list.label.type" path="type" width="10%"/>
	<acme:list-column code="lecturer.lecture.list.label.is-published" path="isPublished" width="10%"/>
	<acme:list-payload path="payload"/>
</acme:list>

<jstl:if test="${_command == 'list-mine'}">
	<acme:button code="lecturer.lecture.list.button.create" action="/lecturer/lecture/create"/>
</jstl:if>	

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
	<acme:input-textbox code="assistant.tutorial.list.label.code" path="code"/>
	<acme:input-textbox code="assistant.tutorial.list.label.title" path="title"/>	
	<acme:input-textbox code="assistant.tutorial.list.label.goals" path="goals"/>
	<acme:input-textbox code="assistant.tutorial.list.label.resume" path="resume"/>
	<acme:input-double code="assistant.job.form.label.estimatedTotalTime" path="estimatedTotalTime"/>
	<acme:input-select code="assistant.tutorial.list.label.course-title" path="course" choices="${courses}"/>	
	<acme:input-integer code="assistant.job.form.label.numSessions" path="numSessions"/>
		
</acme:form>
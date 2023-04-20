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

<h2>
	<acme:message code="assistant.dashboard.form.title.general-indicators"/>
</h2>

<table class="table table-sm">
	<tr>
		<th scope="row">
			<acme:message code="assistant.dashboard.form.label.totalNumberOfTutorialsRegardingTheoryOrHandsonCourses"/>
		</th>
		<td>
			<acme:print value="${totalNumberOfTutorialsRegardingTheoryOrHandsonCourses}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="assistant.dashboard.form.label.averageTimeOfHisSessions"/>
		</th>
		<td>
			<acme:print value="${averageTimeOfHisSessions}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="assistant.dashboard.form.label.deviationTimeOfHisSessions"/>
		</th>
		<td>
			<acme:print value="${deviationTimeOfHisSessions}"/>
		</td>
	</tr>	
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.minimumTimeOfHisSessions"/>
		</th>
		<td>
			<acme:print value="${minimumTimeOfHisSessions}"/>
		</td>
	</tr>	
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.maximumTimeOfHisSessions"/>
		</th>
		<td>
			<acme:print value="${maximumTimeOfHisSessions}"/>
		</td>
	</tr>	
	<tr>
		<th scope="row">
			<acme:message code="assistant.dashboard.form.label.averageTimeOfHisTutorials"/>
		</th>
		<td>
			<acme:print value="${averageTimeOfHisTutorials}"/>
		</td>
	</tr>	
	<tr>
		<th scope="row">
			<acme:message code="assistant.dashboard.form.label.deviationTimeOfHisTutorials"/>
		</th>
		<td>
			<acme:print value="${deviationTimeOfHisTutorials}"/>
		</td>
	</tr>	
	<tr>
		<th scope="row">
			<acme:message code="assistant.dashboard.form.label.minimumTimeOfHisTutorials"/>
		</th>
		<td>
			<acme:print value="${minimumTimeOfHisTutorials}"/>
		</td>
	</tr>	
	<tr>
		<th scope="row">
			<acme:message code="assistant.dashboard.form.label.maximumTimeOfHisTutorials"/>
		</th>
		<td>
			<acme:print value="${maximumTimeOfHisTutorials}"/>
		</td>
	</tr>	
</table>
<acme:return/>


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
	<acme:message code="lecturer.dashboard.form.title.lecturer-indicators"/>
</h2>

<table class="table table-sm">
	<tr>
		<th scope="row">
			<acme:message code="lecturer.dashboard.form.label.total-number-of-theory-lectures"/>
		</th>
		<td>
			<acme:print value="${totalNumberOfTheoryLectures}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="lecturer.dashboard.form.label.total-number-of-hands-on-lectures"/>
		</th>
		<td>
			<acme:print value="${totalNumberOfHandsOnLectures}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="lecturer.dashboard.form.label.average-learning-time-per-lecture"/>
		</th>
		<td>
			<acme:print value="${averageLearningTimePerLecture}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="lecturer.dashboard.form.label.deviation-learning-time-per-lecture"/>
		</th>
		<td>
			<acme:print value="${deviationLearningTimePerLecture}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="lecturer.dashboard.form.label.minimum-learning-time-per-lecture"/>
		</th>
		<td>
			<acme:print value="${minimumLearningTimePerLecture}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="lecturer.dashboard.form.label.maximum-learning-time-per-lecture"/>
		</th>
		<td>
			<acme:print value="${maximumLearningTimePerLecture}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="lecturer.dashboard.form.label.average-learning-time-per-course"/>
		</th>
		<td>
			<acme:print value="${averageLearningTimePerCourse}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="lecturer.dashboard.form.label.deviation-learning-time-per-course"/>
		</th>
		<td>
			<acme:print value="${deviationLearningTimePerCourse}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="lecturer.dashboard.form.label.minimum-learning-time-per-course"/>
		</th>
		<td>
			<acme:print value="${minimumLearningTimePerCourse}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="lecturer.dashboard.form.label.maximum-learning-time-per-course"/>
		</th>
		<td>
			<acme:print value="${maximumLearningTimePerCourse}"/>
		</td>
	</tr>

</table>

<acme:return/>


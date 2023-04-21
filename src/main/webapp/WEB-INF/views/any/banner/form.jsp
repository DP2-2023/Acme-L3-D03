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
	<acme:input-moment code="any.banner.list.label.moment" path="moment"/>
	<acme:input-moment code="any.banner.list.label.displayPeriodStart" path="displayPeriodStart"/>
	<acme:input-moment code="any.banner.list.label.displayPeriodEnd" path="displayPeriodEnd"/>
	<acme:input-url code="any.banner.list.label.linkPicture" path="linkPicture"/>
	<div class="panel-body" style="margin: 1em 0em 1em 0em; text-align: left;">	
		<img src="${linkPicture}" alt="${slogan}" class="img-fluid rounded" style="border-style: solid; max-width: 150px;"/>
	</div>
	<acme:input-textbox code="any.banner.list.label.slogan" path="slogan"/>
	<acme:input-url code="any.banner.list.label.linkTarget" path="linkTarget"/>
	
</acme:form>

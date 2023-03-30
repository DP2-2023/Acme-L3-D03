
<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://www.the-acme-framework.org/"%>

<acme:form>
	<acme:input-textbox code="administrator.config.form.label.configKey" path="configKey"/>
	<acme:input-textbox code="administrator.config.form.label.configKey" path="value"/>
</acme:form>

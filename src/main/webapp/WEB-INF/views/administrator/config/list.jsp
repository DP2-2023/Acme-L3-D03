
<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://www.the-acme-framework.org/"%>

<acme:list>
	<acme:list-column code="administrator.config.list.label.reference" path="configKey" width="10%"/>
	<acme:list-column code="administrator.config.list.label.reference" path="value" width="90%"/>
	<acme:list-payload path="payload"/>
</acme:list>

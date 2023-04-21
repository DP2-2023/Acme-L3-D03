<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://www.the-acme-framework.org/"%>

<acme:list>>
	<acme:list-column code="authenticated.notes.list.label.title" path="title" width="25%"/>
	<acme:list-column code="authenticated.notes.list.label.moment" path="moment" width="25%"/>
	<acme:list-column code="authenticated.notes.list.label.author" path="author" width="25%"/>
	<acme:list-column code="authenticated.notes.list.label.message" path="message" width="25%"/>

</acme:list>


<acme:button code="authenticated.notes.list.button.create" action="/authenticated/note/create"/>

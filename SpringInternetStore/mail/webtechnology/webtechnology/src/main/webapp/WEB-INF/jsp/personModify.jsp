<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<t:template title="${title }">
	<jsp:attribute name="content">
		<h1>${title }</h1>

		<form action="/person/modify" method="post">
			<input type="hidden" name="id" value="${person.id }">
			<div>
				<label>First name: </label><input type="text" name="firstName" value="${person.firstName }">&nbsp;
				<span class="error">${errors.firstName }</span>
			</div>
			<div>
				<label>Last name: </label><input type="text" name="lastName" value="${person.lastName }">
			</div>
			<br>
			<button class="stdBtn" type="submit">Save</button>
			
		</form>		
		
	</jsp:attribute>
	
</t:template>
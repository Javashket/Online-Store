<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<t:template title="${title }">
	<jsp:attribute name="content">
		<h1>${title }</h1>
		
		<input id="filter">&nbsp;<button class="stdBtn" type="button" onclick="clickFilter('filter');">Filter</button>
		<br>
		<br>
		<c:set var="i" value="0" />
		<table class="datatable" id="personList">
			<tr class="column-header">
				<th>First Name</th>
				<th>Last Name</th>
				<th>Action</th>				
			</tr>			
			<c:forEach items="${persons }" var="person">
			<tr class='<c:out value="${i % 2 == 0 ? 'even' : 'odd' }"/>'> 
				<td>${person.firstName }</td>
				<td>${person.lastName }</td>
				<td>
					<a href="/person/modify?id=${person.id }">Edit</a>
				</td>
			</tr>
			<c:set var="i" value="${i + 1}" scope="page" />
			</c:forEach>
			
		</table>
		<br>
		<a href="/person/modify">Add</a><br><br>
		
		<div style="display: none;">
			<table>
				<tr id="templateRow">
					<td></td>
					<td></td>
					<td><a href="">Edit</a><br>
					</td>
				</tr>
			</table>
		</div>
		
	</jsp:attribute>
	
	<jsp:attribute name="appscript">
		<script src="/js/main.js"></script>
	</jsp:attribute>
	
</t:template>
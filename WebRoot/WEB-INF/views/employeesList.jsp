<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<h1>List Employees</h1>
<h3><a href="/ecommerce/add.do">Add More Employee</a></h3>

<c:if test="${!empty employees}">
	<table id="empTable" class="table table-striped table-bordered" cellspacing="0" width="100%">
		<tr>
			<th data-sortable="true">Employee ID</th>
			<th>Employee Name</th>
			<th>Employee Address</th>
			<th>Edit</th>
		</tr>

		<c:forEach items="${employees}" var="employee">
			<tr>
				<td><c:out value="${employee.id}"/></td> 
				<td><c:out value="${employee.name}"/></td>
				<td><c:out value="${employee.address}"/></td>
				<td align="center"><a href="/ecommerce/edit.do?id=${employee.id}">Edit</a> | <a href="/ecommerce/delete.do?id=${employee.id}">Delete</a></td>
			</tr>
		</c:forEach>
	</table>
</c:if>

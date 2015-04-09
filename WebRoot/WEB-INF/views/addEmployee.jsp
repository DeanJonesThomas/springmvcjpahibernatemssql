<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Spring MVC Form Handling</title>
	</head>
	<body>
		<h2>Add Employee Data</h2>
		<form:form method="POST" action="/ecommerce/save.do" commandName="addEmp">
	   		<table id="empTable" class="table table-striped table-bordered" cellspacing="0" width="100%" >
			    <tr>
			        <td><form:label path="id">ID:</form:label></td>
			        <td><form:input path="id" class="form-control" value="${employee.id}" readonly="true"/></td>
			    </tr>
			    <tr> 
			        <td><form:label path="name">Name:</form:label></td>
			        <td><form:input path="name" class="form-control" value="${employee.name}" /></td>
			    </tr>
			    <tr> 
			        <td><form:label path="address">Address:</form:label></td>
			        <td><form:input path="address" class="form-control" value="${employee.address}" /></td>
			    </tr>
			    <tr>
			      <td colspan="2"><input type="submit" value="Submit"/></td>
		      </tr>
			</table> 
		</form:form>
		
 
	</body>
</html>
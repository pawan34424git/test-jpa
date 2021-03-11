<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Form Example - Register an Employee</title>
</head>
<body>
	<h3>Welcome, Enter The Employee Details ${name}</h3>

	<form:form method="POST"  modelAttribute="employee"  >
		<table>
			<tr>
				<td><form:label path="empName">Name</form:label></td>
				<td><form:input path="empName" /></td>
			</tr>
			<tr>
				<td><form:label path="id">Id</form:label></td>
				<td><form:input path="id" /></td>
			</tr>
			 
			<tr>
				<td><input type="submit" value="Submit" /></td>
			</tr>
		</table>
	</form:form>

</body>

</html>
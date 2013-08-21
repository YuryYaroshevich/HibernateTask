<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="jstl"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/employeeList.css"
	media="screen" />
<title>Employee list</title>
</head>
<body>
	<h2>Employee list</h2>
	<table>
		<tr>
			<th>Name</th>
			<th>Address</th>
			<th class="company">Company name</th>
			<th class="city">City</th>
			<th class="country">Country</th>
			<th class="address">Address</th>
			<th class="employee-number">Number of employees for given
				company office</th>
			<th class="position">Position</th>

		</tr>
		<jstl:forEach var="employee" items="${employees}">
			<tr>
				<td>${employee.firstName} ${employee.lastName}</td>
				<td rowspan="${fn:length(employee.jobs)}">${employee.address}</td>
			</tr>

			<jstl:forEach var="job" items="${employee.jobs}">
					<td class="company">${job.key.company}</td>
					<td class="city">${job.key.address.city}</td>
					<td class="country">${job.key.address.city.country}</td>
					<td class="address">${job.key.address}</td>
					<td class="employee-number">${job.key.numberOfEmployees}</td>
					<td class="position">${job.value}</td>
			</jstl:forEach>


		</jstl:forEach>
	</table>

	<table border="1" style="width: 80%;">
		<tr>
			<th rowspan="2">Table header</th>
			<td>Table cell 1</td>
		</tr>
		<tr>
			<td>Table cell 2</td>
		</tr>
	</table>
</body>
</html>
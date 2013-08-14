<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="jstl"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Employee list</title>
</head>
<body>
	<h2>Employee list</h2>
	<table border="1">
		<tr>
			<th>Name</th>
			<th>Address</th>
			<th>
				<table border="1">
					<tr>
						<th>Offices</th>
					</tr>
					<tr>
						<th>Company name</th>
						<th>City</th>
						<th>Country</th>
						<th>Address</th>
						<th>Number of employees for given company office</th>
						<th>Position</th>
					</tr>
				</table>
			</th>
		</tr>
		<jstl:forEach var="employee" items="${employees}">
			<tr>
				<td>${employee.firstName} ${employee.lastName}</td>
				<td>${employee.address}</td>

				<jstl:forEach var="office" items="${employee.offices}">
					<td>
						<table border="1">
							<tr>
								<td>office.company</td>
								<td>office.address.city</td>
								<td>office.address.city.country</td>
								<td>office.address</td>
								<td></td>
								<td></td>
							</tr>
						</table>
					</td>
				</jstl:forEach>
			</tr>
		</jstl:forEach>
	</table>
</body>
</html>
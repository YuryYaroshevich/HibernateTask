package com.epam.ht.db.dao;

import java.util.List;

import com.epam.ht.entity.employee.Employee;

public interface EmployeePaginalDao extends EmployeeDao {
	List<Employee> getEmployees(int numEmployeesPerPage, int pageNumber)
			throws Exception;

	int countEmployees() throws Exception;
}

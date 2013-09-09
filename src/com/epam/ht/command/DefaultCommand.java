package com.epam.ht.command;

import static com.epam.ht.constant.HTConstant.*;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.epam.ht.db.dao.EmployeePaginalDao;
import com.epam.ht.entity.employee.Employee;

final class DefaultCommand implements Command {
	// attribute name for setting value in session
	private static final String EMPLOYEES = "employees";

	@Override
	public void execute(HttpServletRequest req, EmployeePaginalDao emplDao)
			throws Exception {
		List<Employee> employees = emplDao
				.getEmployees(DEFAULT_EMPLOYEES_NUMBER);
		req.setAttribute(EMPLOYEES, employees);
		int numberOfEmployees = emplDao.countEmployees();
		HttpSession session = req.getSession(true);
		int numberOfPages = (numberOfEmployees / employees.size()) + 1;
		session.setAttribute(NUMBER_OF_PAGES, numberOfPages);
		session.setAttribute(CURRENT_PAGE_INDEX, 1);
	}
}

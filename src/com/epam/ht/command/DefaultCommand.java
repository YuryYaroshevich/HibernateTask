package com.epam.ht.command;

import static com.epam.ht.constant.HTConstant.*;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.epam.ht.db.dao.EmployeePaginalDao;
import com.epam.ht.entity.employee.Employee;

final class DefaultCommand implements Command {
	@Override
	public void execute(HttpServletRequest req, EmployeePaginalDao emplDao)
			throws Exception {
		// gets employees for output on first page
		List<Employee> employees = emplDao
				.getEmployees(DEFAULT_EMPLOYEES_NUMBER);
		req.setAttribute(EMPLOYEES, employees);
		HttpSession session = req.getSession(true);
		// uses this value in ViewPageCommand
		session.setAttribute(N_EMPLS_PER_PAGE, DEFAULT_EMPLOYEES_NUMBER);
		// counts number of pages for paging tag
		int numberOfEmployees = emplDao.countEmployees();
		int numberOfPages = (numberOfEmployees / employees.size()) + 1;
		session.setAttribute(NUMBER_OF_PAGES, numberOfPages);
		session.setAttribute(CURRENT_PAGE_INDEX, DEFAULT_PAGE_INDEX);
	}
}

package com.epam.ht.command;

import static com.epam.ht.constant.HTConstant.*;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.epam.ht.db.dao.EmployeePaginalDao;
import com.epam.ht.entity.employee.Employee;
import com.epam.ht.util.PagingHelper;

final class SetNEmplPerPageCommand implements Command {
	@Override
	public void execute(HttpServletRequest req, EmployeePaginalDao emplDao)
			throws Exception {
		int nEmplPerPage = Integer.valueOf(req.getParameter(N_EMPLS_PER_PAGE));
		// gets employees for output on first page
		List<Employee> employees = emplDao.getEmployees(nEmplPerPage);
		req.setAttribute(EMPLOYEES, employees);
		HttpSession session = req.getSession(true);
		// uses this value in ViewPageCommand
		session.setAttribute(N_EMPLS_PER_PAGE, nEmplPerPage);
		// counts number of pages for paging tag
		int numberOfEmployees = emplDao.countEmployees();
		int numberOfPages = PagingHelper.countNumberOfPages(numberOfEmployees,
				nEmplPerPage);
		session.setAttribute(NUMBER_OF_PAGES, numberOfPages);
		session.setAttribute(CURRENT_PAGE_INDEX, DEFAULT_PAGE_INDEX);
	}
}

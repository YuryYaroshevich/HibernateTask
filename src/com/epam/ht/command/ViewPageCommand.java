package com.epam.ht.command;

import static com.epam.ht.constant.HTConstant.*;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.epam.ht.db.dao.EmployeePaginalDao;
import com.epam.ht.entity.employee.Employee;

final class ViewPageCommand implements Command {
	@Override
	public void execute(HttpServletRequest req, EmployeePaginalDao emplDao)
			throws Exception {
		HttpSession session = req.getSession(true);
		int nEmplPerPage = (Integer) session.getAttribute(N_EMPLS_PER_PAGE);
		int currentPage = Integer.valueOf(req.getParameter(CURRENT_PAGE_INDEX));
		// gets employees for output on chosen page
		List<Employee> employees = emplDao.getEmployees(nEmplPerPage,
				currentPage);
		req.setAttribute(EMPLOYEES, employees);
		session.setAttribute(CURRENT_PAGE_INDEX, currentPage);
	}
}

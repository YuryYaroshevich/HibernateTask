package com.epam.ht.command;

import javax.servlet.http.HttpServletRequest;

import com.epam.ht.db.dao.EmployeePaginalDao;

final class SetNEmplPerPageCommand implements Command {
	// attribute name for setting value in session
	private static final String EMPLOYEES = "employees";

	@Override
	public void execute(HttpServletRequest req, EmployeePaginalDao dao) {
		// TODO Auto-generated method stub

	}

}

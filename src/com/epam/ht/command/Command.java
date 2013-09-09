package com.epam.ht.command;

import javax.servlet.http.HttpServletRequest;

import com.epam.ht.db.dao.EmployeePaginalDao;

public interface Command {
	void execute(HttpServletRequest req, EmployeePaginalDao dao)
			throws Exception;
}

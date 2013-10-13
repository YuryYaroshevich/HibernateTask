package com.epam.ht.controller;

import static com.epam.ht.db.dao.EmployeePaginalDaoFactory.DAOType.*;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.ht.command.Command;
import com.epam.ht.command.CommandCreator;
import com.epam.ht.db.dao.EmployeePaginalDao;
import com.epam.ht.db.dao.EmployeePaginalDaoFactory;

public final class EmployeeListServlet extends HttpServlet {
	private static final long serialVersionUID = 1529606092637198325L;

	// key for getting parameter from context
	private static final String DISPATCH_PATH = "dispatchPath";

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		processRequest(req, resp);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		processRequest(req, resp);
	}

	private void processRequest(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			EmployeePaginalDao emplDAO = EmployeePaginalDaoFactory
					.getEmployeeDAO(JDBC);
			Command command = CommandCreator.createCommand(req);
			command.execute(req, emplDAO);
			String dispatchPath = getServletContext().getInitParameter(
					DISPATCH_PATH);
			RequestDispatcher dispatcher = req
					.getRequestDispatcher(dispatchPath);
			dispatcher.forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
	}
}

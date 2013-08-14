package com.epam.ht.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.ht.dao.EmployeeDAOFactory;
import com.epam.ht.entity.employee.Employee;

public class EmployeeListServlet extends HttpServlet {
	private static final long serialVersionUID = 1529606092637198325L;
	
	// key for getting parameter from context
	private static final String DISPATCH_PATH = "dispatchPath";
	
	// attribute name for setting value in session
	private static final String EMPLOYEES = "employees";

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
		/*List<Employee> employees = EmployeeDAOFactory.getEmployeeDAO()
				.getEmployees();
		req.getSession(true).setAttribute(EMPLOYEES, employees);*/
		String dispatchPath = getServletContext().getInitParameter(
				DISPATCH_PATH);
		RequestDispatcher dispatcher = req.getRequestDispatcher(dispatchPath);
		dispatcher.forward(req, resp);
	}
}

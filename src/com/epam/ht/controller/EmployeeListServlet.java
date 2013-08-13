package com.epam.ht.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EmployeeListServlet extends HttpServlet {
	private static final long serialVersionUID = 1529606092637198325L;

	private static final String DISPATCH_PATH = "dispatchPath";

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		processRequest(req, resp);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		processRequest(req, resp);
	}

	private void processRequest(HttpServletRequest req,
			HttpServletResponse resp) throws ServletException, IOException {
		String dispatchPath = getServletContext().getInitParameter(
				DISPATCH_PATH);
		RequestDispatcher dispatcher = req.getRequestDispatcher(dispatchPath);
		dispatcher.forward(req, resp);
	}
}

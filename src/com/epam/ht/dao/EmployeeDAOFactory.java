package com.epam.ht.dao;

public final class EmployeeDAOFactory {
	private EmployeeDAOFactory() {
	}
	
	public static EmployeeDAO getEmployeeDAO() {
		return EmployeeDAOHibernate.getInstance();
	}
}

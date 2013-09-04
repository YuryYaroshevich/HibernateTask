package com.epam.ht.db.dao;

public final class EmployeeDAOFactory {
	public static enum DAOType {
		HIBERNATE, JDBC
	}
	
	private EmployeeDAOFactory() {
	}
	
	public static EmployeeDAO getEmployeeDAO(DAOType daoType) {
		switch(daoType) {
		case JDBC:
			return EmployeeDAOJDBC.getInstance();
		case HIBERNATE:
			return EmployeeDAOHibernate.getInstance();
		default:
			return EmployeeDAOHibernate.getInstance();
		}
	}
}

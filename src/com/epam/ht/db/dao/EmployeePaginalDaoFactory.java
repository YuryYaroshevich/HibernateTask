package com.epam.ht.db.dao;

public final class EmployeePaginalDaoFactory {
	public static enum DAOType {
		HIBERNATE, JDBC, JPA
	}
	
	private EmployeePaginalDaoFactory() {
	}
	
	public static EmployeePaginalDao getEmployeeDAO(DAOType daoType) {
		switch(daoType) {
		case JDBC:
			return EmployeePaginalDaoJDBC.getInstance();
		case HIBERNATE:
			return EmployeePaginalDaoHibernate.getInstance();
		case JPA:
			return EmployeePaginalDaoJPA.getInstance();
		default:
			return EmployeePaginalDaoHibernate.getInstance();
		}
	}
}

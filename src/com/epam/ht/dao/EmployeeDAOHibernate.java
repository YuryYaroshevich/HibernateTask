package com.epam.ht.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.epam.ht.entity.employee.Employee;
import com.epam.ht.util.SessionFactoryGetter;

final class EmployeeDAOHibernate implements EmployeeDAO {
	private static EmployeeDAOHibernate dao = new EmployeeDAOHibernate();

	private static SessionFactory sessionFactory;
	static {
		sessionFactory = SessionFactoryGetter.getSessionFactory();
	}

	// query name
	private static final String EMPLOYEE_LIST = "query.EmployeeList";
	private static final String CORRESPOND_OFFICES = "query.CorrespondOffices";
	private static final String CORRESPOND_OFFICES_IDS = "query.CorrespondOfficeIds";

	// parameter names for queries
	private static final String EMPLOYEES_NUMB_PARAM = "employees_number";
	private static final String OFFICE_IDS_PARAM = "office_ids";

	// number of rows I take from table
	private static final int EMPLOYEES_NUMBER = 100;

	private EmployeeDAOHibernate() {
	}

	public static EmployeeDAO getInstance() {
		return dao;
	}

	@SuppressWarnings("unchecked")
	public List<Employee> getEmployees() {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		// get id of offices where first 100 employees work
		List<Long> officeIds = session
				.getNamedQuery(CORRESPOND_OFFICES_IDS)
				.setParameter(EMPLOYEES_NUMB_PARAM, EMPLOYEES_NUMBER).list();
		// load in session correspond offices
		session.getNamedQuery(CORRESPOND_OFFICES)
				.setParameterList(OFFICE_IDS_PARAM, officeIds).list();
		// get employees
		List<Employee> employees = session.getNamedQuery(EMPLOYEE_LIST)
				.setMaxResults(EMPLOYEES_NUMBER).list();
		tx.commit();
		return employees;
	}
}

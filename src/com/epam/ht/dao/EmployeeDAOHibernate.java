package com.epam.ht.dao;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.epam.ht.entity.employee.Employee;
import com.epam.ht.entity.office.Office;
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
	private static final String CORRESPOND_OFFICE_IDS = "query.CorrespondOfficeIds";

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
		/*List<Long> officeIds =*/ System.out.println(session.createSQLQuery("select offi.office_id from office offi").list());/*session
				.getNamedQuery(CORRESPOND_OFFICE_IDS)
				.setParameter(EMPLOYEES_NUMB_PARAM, EMPLOYEES_NUMBER).list();*/
		//System.out.println(officeIds);
		// load in session correspond offices
		/*List<Office> offices = session.getNamedQuery(CORRESPOND_OFFICES)
				.setParameterList(OFFICE_IDS_PARAM, officeIds).list();
		System.out.println(offices);
		// get employees
		List<Employee> employees = session.getNamedQuery(EMPLOYEE_LIST)
				.setMaxResults(EMPLOYEES_NUMBER).list();
		Iterator<Office> i = employees.get(0).getJobs().keySet().iterator();
		while (i.hasNext()) {
			System.out.println(i.next().getNumberOfEmployees()+" !");
		}*/
		tx.commit();
		return null;
	}
}

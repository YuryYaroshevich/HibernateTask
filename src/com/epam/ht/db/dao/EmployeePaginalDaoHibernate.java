package com.epam.ht.db.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.epam.ht.entity.employee.Employee;
import com.epam.ht.util.SessionFactoryGetter;

final class EmployeePaginalDaoHibernate implements EmployeePaginalDao {
	private static EmployeePaginalDao dao = new EmployeePaginalDaoHibernate();

	private static SessionFactory sessionFactory;
	static {
		sessionFactory = SessionFactoryGetter.getSessionFactory();
	}

	// query name
	private static final String CORRESPOND_EMPLOYEE_IDS = "query.CorrespondEmployeeIds";
	private static final String EMPLOYEE_LIST = "query.EmployeeList";
	private static final String CORRESPOND_OFFICES = "query.CorrespondOffices";
	private static final String CORRESPOND_OFFICE_IDS = "query.CorrespondOfficeIds";
	private static final String EMPLOYEES_NUMBER = "query.EmployeesNumber";

	// parameter names for queries
	private static final String FIRST_ROW_NUMB_PARAM = "first_row_numb";
	private static final String LAST_ROW_NUMB_PARAM = "last_row_numb";
	private static final String EMPLOYEE_IDS_PARAM = "employee_ids";
	private static final String OFFICE_IDS_PARAM = "office_ids";

	private EmployeePaginalDaoHibernate() {
	}

	public static EmployeePaginalDao getInstance() {
		return dao;
	}

	@Override
	public List<Employee> getEmployees(int rowsNumber) {
		return getEmployees(rowsNumber, 1);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Employee> getEmployees(int numEmployeesPerPage, int pageNumber) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();

		// get ids of employees on correspond page
		int firstRowNumb = numEmployeesPerPage * (pageNumber - 1) + 1;
		int lastRowNumb = firstRowNumb + numEmployeesPerPage - 1;
		List<Long> employeeIds = session.getNamedQuery(CORRESPOND_EMPLOYEE_IDS)
				.setParameter(FIRST_ROW_NUMB_PARAM, firstRowNumb)
				.setParameter(LAST_ROW_NUMB_PARAM, lastRowNumb).list();
		// get id of offices where employees work
		List<Long> officeIds = session.getNamedQuery(CORRESPOND_OFFICE_IDS)
				.setParameterList(EMPLOYEE_IDS_PARAM, employeeIds).list();
		// load in session correspond offices
		session.getNamedQuery(CORRESPOND_OFFICES)
				.setParameterList(OFFICE_IDS_PARAM, officeIds).list();
		// get employees
		List<Employee> employees = session.getNamedQuery(EMPLOYEE_LIST)
				.setParameterList(EMPLOYEE_IDS_PARAM, employeeIds).list();

		tx.commit();
		return employees;
	}

	@Override
	public int countEmployees() {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();

		int employeesNumber = (Integer) session.getNamedQuery(EMPLOYEES_NUMBER)
				.uniqueResult();

		tx.commit();
		return employeesNumber;
	}
}

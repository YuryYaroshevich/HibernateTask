package com.epam.ht.db.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.epam.ht.entity.employee.Employee;
import com.epam.ht.util.SessionFactoryGetter;
import static com.epam.ht.constant.HTConstant.*;

final class EmployeePaginalDaoHibernate implements EmployeePaginalDao {
	private static EmployeePaginalDao dao = new EmployeePaginalDaoHibernate();

	private static SessionFactory sessionFactory;
	static {
		sessionFactory = SessionFactoryGetter.getSessionFactory();
	}

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
		List<Long> employeeIds = session.getNamedQuery(CORRESPOND_EMPLOYEE_IDS)
				.setFirstResult(firstRowNumb)
				.setMaxResults(numEmployeesPerPage).list();
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

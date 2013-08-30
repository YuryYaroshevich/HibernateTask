package com.epam.ht.dao;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
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

	// number of rows I take from table
	private static final int NUMBER_OF_EMPLOYEES = 100;

	private EmployeeDAOHibernate() {
	}

	public static EmployeeDAO getInstance() {
		return dao;
	}

	@SuppressWarnings("unchecked")
	public List<Employee> getEmployees() {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();

<<<<<<< HEAD
		Query query = session.getNamedQuery(EMPLOYEE_LIST).setMaxResults(
				NUMBER_OF_EMPLOYEES);
		List<Employee> employees = query.list();

=======
		/*
		 * List<BigDecimal> employeeIds = session
		 * .createSQLQuery("select employee_id from employee")
		 * .setMaxResults(NUMBER_OF_EMPLOYEES).list();
		 * 
		 * List<Office> offices = session
		 * .getNamedQuery("query.CorrespondOffices")
		 * .setParameterList("employeeIds", employeeIds) .list();
		 */

		Query employeesQ = session.getNamedQuery(EMPLOYEE_LIST).setMaxResults(
				NUMBER_OF_EMPLOYEES);
		/*
		 * List<Employee> employees = session.createCriteria(Employee.class)
		 * .setFetchMode("address", FetchMode.JOIN) .setFetchMode("jobs",
		 * FetchMode.JOIN) .list();
		 */
		List<Employee> employees = employeesQ.list();
		/*Set<Object> officeIds = new HashSet<Object>();

		for (Employee empl : employees) {
			officeIds.addAll(empl.getJobs().keySet());
		}
		Query correspondOfficesQ = session.getNamedQuery(
				"query.CorrespondOffices").setParameterList("office_ids",
				officeIds.toArray());
		List<Office> offices = correspondOfficesQ.list();*/
		
		
		
		
>>>>>>> f9690536339571cfa3bd137e85987ed00b38633c
		tx.commit();
		return employees;
	}
}

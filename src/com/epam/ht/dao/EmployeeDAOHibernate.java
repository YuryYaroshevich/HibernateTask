package com.epam.ht.dao;

import java.math.BigDecimal;
import java.util.List;

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

		List<BigDecimal> employeeIds = session
				.createSQLQuery("select employee_id from employee")
				.setMaxResults(NUMBER_OF_EMPLOYEES).list();

		List<Office> offices = session
				.getNamedQuery("query.CorrespondOffices")
				.setParameterList("employeeIds", employeeIds)
				.list();
		/*offices = session.createQuery(
				"from Office o left join fetch o.company"
						+ " left join fetch o.address addr"
						+ " left join fetch addr.city c"
						+ " left join fetch c.country").list();*/

		Query query = session.getNamedQuery(EMPLOYEE_LIST).setMaxResults(
				NUMBER_OF_EMPLOYEES);

		List<Employee> employees = query.list();
		tx.commit();
		return employees;
	}
}

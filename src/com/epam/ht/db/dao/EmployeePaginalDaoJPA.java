package com.epam.ht.db.dao;

import static com.epam.ht.resource.PropertyGetter.getProperty;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;

import com.epam.ht.entity.employee.Employee;
import static com.epam.ht.constant.HTConstant.*;

final class EmployeePaginalDaoJPA implements EmployeePaginalDao {
	private static final EmployeePaginalDao dao = new EmployeePaginalDaoJPA();

	private static final String PERSISTENCE_UNIT_NAME = "persistence.unit.name";

	@PersistenceUnit
	private static final EntityManagerFactory entManagerFactory = Persistence
			.createEntityManagerFactory(getProperty(PERSISTENCE_UNIT_NAME));

	private EmployeePaginalDaoJPA() {
	}

	public static EmployeePaginalDao getInstance() {
		return dao;
	}

	@Override
	public List<Employee> getEmployees(int rowsNumber) throws Exception {
		return getEmployees(rowsNumber, 1);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Employee> getEmployees(int numEmployeesPerPage, int pageNumber) {
		EntityManager entManager = entManagerFactory.createEntityManager();
		EntityTransaction tx = entManager.getTransaction();
		tx.begin();

		// get ids of employees on correspond page
		int firstRowNumb = numEmployeesPerPage * (pageNumber - 1) + 1;
		List<Long> employeeIds = entManager
				.createNamedQuery(CORRESPOND_EMPLOYEE_IDS)
				.setFirstResult(firstRowNumb)
				.setMaxResults(numEmployeesPerPage).getResultList();
		// get id of offices where first 100 employees work
		List<Long> officeIds = entManager
				.createNamedQuery(CORRESPOND_OFFICE_IDS)
				.setParameter(EMPLOYEE_IDS_PARAM, employeeIds).getResultList();
		// load in session correspond offices
		entManager.createNamedQuery(CORRESPOND_OFFICES)
				.setParameter(OFFICE_IDS_PARAM, officeIds).getResultList();
		// get employees
		List<Employee> employees = entManager.createNamedQuery(EMPLOYEE_LIST)
				.setParameter(EMPLOYEE_IDS_PARAM, employeeIds).getResultList();

		tx.commit();
		entManager.close();
		return employees;
	}

	@Override
	public int countEmployees() {
		EntityManager entManager = entManagerFactory.createEntityManager();
		EntityTransaction tx = entManager.getTransaction();
		tx.begin();

		int employeesNumber = (Integer) entManager.createNamedQuery(EMPLOYEES_NUMBER)
				.getSingleResult();

		tx.commit();
		entManager.close();
		return employeesNumber;
	}
}

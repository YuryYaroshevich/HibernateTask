package com.epam.ht.db.dao;

import static com.epam.ht.resource.PropertyGetter.getProperty;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;

import com.epam.ht.entity.employee.Employee;

final class EmployeePaginalDaoJPA implements EmployeePaginalDao {
	private static final EmployeePaginalDao dao = new EmployeePaginalDaoJPA();

	private static final String PERSISTENCE_UNIT_NAME = "persistence.unit.name";

	@PersistenceUnit
	private static final EntityManagerFactory entManagerFactory = Persistence
			.createEntityManagerFactory(getProperty(PERSISTENCE_UNIT_NAME));

	// query name
	private static final String CORRESPOND_EMPLOYEE_IDS = "query.CorrespondEmployeeIds";
	private static final String EMPLOYEE_LIST = "query.EmployeeList";
	private static final String CORRESPOND_OFFICES = "query.CorrespondOffices";
	private static final String CORRESPOND_OFFICE_IDS = "query.CorrespondOfficeIds";

	// parameter names for queries
	private static final String EMPLOYEE_IDS_PARAM = "employee_ids";
	private static final String OFFICE_IDS_PARAM = "office_ids";

	private EmployeePaginalDaoJPA() {
	}

	public static EmployeePaginalDao getInstance() {
		return dao;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Employee> getEmployees(int rowsNumber) throws Exception {
		EntityManager entManager = entManagerFactory.createEntityManager();
		EntityTransaction tx = entManager.getTransaction();
		tx.begin();

		// get ids of first 100 employees
		List<Long> employeeIds = entManager
				.createNamedQuery(CORRESPOND_EMPLOYEE_IDS)
				.setMaxResults(rowsNumber).getResultList();
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
	public List<Employee> getEmployees(int numEmployeesPerPage, int pageNumber) {
		// TODO Auto-generated method stub
		return null;
	}
}

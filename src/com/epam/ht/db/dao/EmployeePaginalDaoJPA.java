package com.epam.ht.db.dao;

import static com.epam.ht.constant.HTConstant.*;
import static com.epam.ht.resource.PropertyGetter.getProperty;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.epam.ht.entity.employee.Employee;
import com.epam.ht.entity.employee.Employee_;
import com.epam.ht.entity.office.Office;
import com.epam.ht.entity.office.Office_;

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
	public List<Employee> getEmployees(int nEmplsPerPage, int pageNumber) {
		EntityManager entManager = entManagerFactory.createEntityManager();
		EntityTransaction tx = entManager.getTransaction();
		tx.begin();

		// get ids of employees on correspond page
		int firstRowNumb = nEmplsPerPage * (pageNumber - 1) + 1;
		List<Long> employeeIds = entManager
				.createNamedQuery(CORRESPOND_EMPLOYEE_IDS)
				.setFirstResult(firstRowNumb).setMaxResults(nEmplsPerPage)
				.getResultList();
		// get id of offices where first 100 employees work
		List<Long> officeIds = entManager
				.createNamedQuery(CORRESPOND_OFFICE_IDS)
				.setParameter(EMPLOYEE_IDS_PARAM, employeeIds).getResultList();
		CriteriaBuilder critBuilder = entManager.getCriteriaBuilder();
		// load in session correspond offices
		fetchEntityList(entManager, critBuilder, officeIds, Office.class);
		// get employees
		List<Employee> employees = fetchEntityList(entManager, critBuilder,
				employeeIds, Employee.class);

		tx.commit();
		entManager.close();
		return employees;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static List fetchEntityList(EntityManager entManager,
			CriteriaBuilder critBuilder, List<Long> ids, Class entityClass) {
		CriteriaQuery entityCrit = critBuilder.createQuery(entityClass);
		Root queryRoot = entityCrit.distinct(true).from(entityClass);
		if (Employee.class.equals(entityClass)) {
			entityCrit.where(critBuilder.isTrue(queryRoot.get(Employee_.id).in(
					ids)));
		} else if (Office.class.equals(entityClass)) {
			entityCrit.where(critBuilder.isTrue(queryRoot.get(Office_.id).in(
					ids)));
		}
		TypedQuery entityQuery = entManager.createQuery(entityCrit);
		return entityQuery.getResultList();
	}

	@Override
	public int countEmployees() {
		EntityManager entManager = entManagerFactory.createEntityManager();
		EntityTransaction tx = entManager.getTransaction();
		tx.begin();

		BigDecimal employeesNumber = (BigDecimal) entManager.createNamedQuery(
				EMPLOYEES_NUMBER).getSingleResult();

		tx.commit();
		entManager.close();
		return employeesNumber.intValue();
	}
}

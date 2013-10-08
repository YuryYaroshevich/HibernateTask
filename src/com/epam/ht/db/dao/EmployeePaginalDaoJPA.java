package com.epam.ht.db.dao;

import static com.epam.ht.constant.HTConstant.CORRESPOND_EMPLOYEE_IDS;
import static com.epam.ht.constant.HTConstant.CORRESPOND_OFFICE_IDS;
import static com.epam.ht.constant.HTConstant.EMPLOYEES_NUMBER;
import static com.epam.ht.constant.HTConstant.EMPLOYEE_IDS_PARAM;
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
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import com.epam.ht.entity.employee.Employee;
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
		//entManager.find(Employee.class, new Long(89988));
		//entManager.find(Office.class, new Long(37362));
		// load in session correspond offices
		loadOfficeList(entManager, critBuilder, officeIds);
		// get employees
		// List<Employee> employees = fetchEntityList(entManager, critBuilder,
		// employeeIds, Employee.class);

		tx.commit();
		entManager.close();
		return null;
	}

	private static List<Office> loadOfficeList(EntityManager entManager,
			CriteriaBuilder critBuilder, List<Long> ids) {
		CriteriaQuery<Office> officeCrit = critBuilder.createQuery(Office.class);
		Root<Office> queryRoot = officeCrit.distinct(true).from(Office.class);

		queryRoot.fetch("address");
		queryRoot.fetch("company");
		//queryRoot.fetch("address.city", JoinType.INNER);
		
		officeCrit.where(queryRoot.get(Office_.id).in(ids));
		TypedQuery<Office> query = entManager.createQuery(officeCrit);
		return query.getResultList();
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

package com.epam.ht.db.dao;

import static com.epam.ht.constant.HTConstant.CORRESPOND_EMPLOYEE_IDS;
import static com.epam.ht.constant.HTConstant.CORRESPOND_OFFICES;
import static com.epam.ht.constant.HTConstant.CORRESPOND_OFFICE_IDS;
import static com.epam.ht.constant.HTConstant.EMPLOYEES_NUMBER;
import static com.epam.ht.constant.HTConstant.EMPLOYEE_IDS_PARAM;
import static com.epam.ht.constant.HTConstant.EMPLOYEE_LIST;
import static com.epam.ht.constant.HTConstant.OFFICE_IDS_PARAM;
import static com.epam.ht.resource.PropertyGetter.getProperty;

import java.math.BigDecimal;
import java.util.ArrayList;
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

	/*
	 * private static final String JOIN_HINT_EMPLOYEE =
	 * "employee.address.city.country"; private static final String
	 * JOIN_HINT_OFFICE = "office.address.city.country";
	 */

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
		List<BigDecimal> employeeIds = entManager
				.createNamedQuery(CORRESPOND_EMPLOYEE_IDS)
				.setFirstResult(firstRowNumb).setMaxResults(nEmplsPerPage)
				.getResultList();
		// get id of offices where first 100 employees work
		List<BigDecimal> officeIds = entManager
				.createNamedQuery(CORRESPOND_OFFICE_IDS)
				.setParameter(EMPLOYEE_IDS_PARAM, employeeIds).getResultList();
		// load in session correspond offices
		entManager
				.createNamedQuery(CORRESPOND_OFFICES)
				.setParameter(OFFICE_IDS_PARAM,
						castBigDecimalListToLongList(officeIds))
				.getResultList();
		// get employees
		List<Employee> employees = entManager
				.createNamedQuery(EMPLOYEE_LIST)
				.setParameter(EMPLOYEE_IDS_PARAM,
						castBigDecimalListToLongList(employeeIds))
				.getResultList();
		System.out.println(employees.get(0).getJobs().size());

		tx.commit();
		entManager.close();
		return employees;
	}

	private static List<Long> castBigDecimalListToLongList(
			List<BigDecimal> bgdList) {
		List<Long> lList = new ArrayList<Long>();
		for (BigDecimal bgd : bgdList) {
			lList.add(bgd.longValue());
		}
		return lList;
	}

	/*
	 * private static List<Office> loadOfficeList(EntityManager entManager,
	 * CriteriaBuilder critBuilder, List<Long> ids) { CriteriaQuery<Office>
	 * officeCrit = critBuilder .createQuery(Office.class); Root<Office>
	 * queryRoot = officeCrit.distinct(true).from(Office.class);
	 * 
	 * officeCrit.where(queryRoot.get(Office_.id).in(ids)); TypedQuery<Office>
	 * query = entManager.createQuery(officeCrit);
	 * query.setHint(QueryHints.FETCH, JOIN_HINT_OFFICE); return
	 * query.getResultList(); }
	 * 
	 * private static List<Employee> loadEmployeeList(EntityManager entManager,
	 * CriteriaBuilder critBuilder, List<Long> ids) { CriteriaQuery<Employee>
	 * emplCrit = critBuilder .createQuery(Employee.class); Root<Employee>
	 * queryRoot = emplCrit.distinct(true).from(Employee.class);
	 * 
	 * emplCrit.where(queryRoot.get(Employee_.id).in(ids)); TypedQuery<Employee>
	 * query = entManager.createQuery(emplCrit); query.setHint(QueryHints.FETCH,
	 * JOIN_HINT_EMPLOYEE); return query.getResultList(); }
	 */

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

/*
 * // get ids of employees on correspond page int firstRowNumb = nEmplsPerPage *
 * (pageNumber - 1) + 1; List<Long> emplIds = entManager
 * .createNamedQuery(CORRESPOND_EMPLOYEE_IDS)
 * .setFirstResult(firstRowNumb).setMaxResults(nEmplsPerPage) .getResultList();
 * // get id of offices where first 100 employees work List<Long> officeIds =
 * entManager .createNamedQuery(CORRESPOND_OFFICE_IDS) .setParameter(1,
 * emplIds.get(0)) .setParameter(2, emplIds.get(emplIds.size() - 1))
 * .getResultList(); CriteriaBuilder critBuilder =
 * entManager.getCriteriaBuilder(); // load in session correspond offices
 * List<Office> offs = loadOfficeList(entManager, critBuilder, officeIds);
 * System
 * .out.println(offs.get(0).getNumberOfEmployees()+"!!!!!!!!!!!!!!!!!!!!!!!!!!11"
 * ); // get employees //List<Employee> employees = loadEmployeeList(entManager,
 * critBuilder, // emplIds);
 */

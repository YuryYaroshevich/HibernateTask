package com.epam.ht.db.dao;

import static com.epam.ht.resource.PropertyGetter.getProperty;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.epam.ht.db.pool.ConnectionPool;
import com.epam.ht.entity.address.Address;
import com.epam.ht.entity.city.City;
import com.epam.ht.entity.company.Company;
import com.epam.ht.entity.country.Country;
import com.epam.ht.entity.employee.Employee;
import com.epam.ht.entity.employee.Position;
import com.epam.ht.entity.office.Office;

final class EmployeeDAOJDBC implements EmployeeDAO {
	private static final EmployeeDAO dao = new EmployeeDAOJDBC();

	private static final int EMPLOYEES_NUMBER = 100;

	// SQL query keys
	private static final String EMPLOYEE_IDS = "query.employee.ids";
	private static final String EMPLOYEE_LIST = "query.employee.list";
	private static final String OFFICE_LIST = "query.office.list";
	private static final String EMPLOYEES = "query.employees";

	public static EmployeeDAO getInstance() {
		return dao;
	}

	@Override
	public List<Employee> getEmployees() throws Exception {
		Connection con = null;
		ConnectionPool pool = ConnectionPool.getInstance();
		try {
			con = pool.getConnection();
			// List<Long> employeeIds = getEmployeeIds(con);
			List<Employee> employees = fetchCorrespondEmployees(con);
			/*
			 * List<Office> offices = fetchCorrespondOffices(con, employeeIds);
			 * wireOfficesWithEmployees(employees, offices); return new
			 * ArrayList<Employee>(employees.values());
			 */
			return null;
		} finally {
			pool.makeConnectionFree(con);
		}
	}

	private static List<Long> getEmployeeIds(Connection con)
			throws SQLException {
		PreparedStatement statem = null;
		ResultSet rsltSet = null;
		try {
			statem = con.prepareStatement(getProperty(EMPLOYEE_IDS));
			statem.setLong(1, EMPLOYEES_NUMBER);
			rsltSet = statem.executeQuery();
			List<Long> employeeIds = new ArrayList<Long>();
			while (rsltSet.next()) {
				employeeIds.add(rsltSet.getLong(1));
			}
			return employeeIds;
		} finally {
			closeResultSet(rsltSet);
			closeStatement(statem);
		}
	}

	private static List<Employee> fetchCorrespondEmployees(Connection con)
			throws SQLException {
		Statement statem = null;
		ResultSet rsltSet = null;
		try {
			statem = con.createStatement();
			rsltSet = statem.executeQuery(getProperty(EMPLOYEES));
			List<Employee> employees = new ArrayList<Employee>();
			long currentEmployeeId;
			long previousEmployeeId = -1;
			Employee empl = null;
			Position position = null;
			Office office = null;
			Map<Office, Position> jobs = null;
			while (rsltSet.next()) {
				currentEmployeeId = rsltSet.getLong("employeeId");
				
				if (currentEmployeeId != previousEmployeeId) {
					// create employee and fetch his first job
					if (empl != null) {
						empl.setJobs(jobs);
						employees.add(empl);
					}
					empl = buildEmployee(rsltSet, currentEmployeeId);
					position = new Position(rsltSet.getString("position"));
					office = buildOffice(rsltSet);
					jobs = new HashMap<Office, Position>();
					jobs.put(office, position);

				} else {
					// add offices to existing employee
					position = new Position(rsltSet.getString("position"));
					office = buildOffice(rsltSet);
				}
				
				
			}
			return employees;
		} finally {
			closeResultSet(rsltSet);
			closeStatement(statem);
		}
	}

	/*
	 * private static Map<Long, Employee> fetchCorrespondEmployees(Connection
	 * con, List<Long> employeeIds) throws SQLException { Statement statem =
	 * null; ResultSet rsltSet = null; try { Map<Long, Employee> employees = new
	 * HashMap<Long, Employee>(); statem = con.createStatement(); rsltSet =
	 * statem.executeQuery(buildQueryWithIdList(employeeIds,
	 * getProperty(EMPLOYEE_LIST))); Employee empl = null; while
	 * (rsltSet.next()) { empl = buildEmployee(rsltSet);
	 * employees.put(empl.getId(), empl); } return employees; } finally {
	 * closeResultSet(rsltSet); closeStatement(statem); } }
	 */

	private static String buildQueryWithIdList(List<Long> ids, String queryStart) {
		StringBuilder query = new StringBuilder(queryStart);
		for (int i = 0; i < ids.size(); i++) {
			query.append(ids.get(i));
			if (i + 1 == ids.size()) {
				query.append(")");
			} else {
				query.append(",");
			}
		}
		return query.toString();
	}

	private static Employee buildEmployee(ResultSet rs, long employeeId)
			throws SQLException {
		// build employee
		Employee empl = new Employee(employeeId);
		empl.setFirstName(rs.getString("first_name"));
		empl.setLastName(rs.getString("last_name"));
		// build his address
		Address addr = new Address();
		addr.setId(empl.getId());
		addr.setAddress(rs.getString("employee_address"));
		empl.setAddress(addr);
		return empl;
	}

	/*
	 * private static Employee buildEmployee(ResultSet rs) throws SQLException {
	 * // build employee Employee empl = new Employee();
	 * empl.setId(rs.getLong("employee_id"));
	 * empl.setFirstName(rs.getString("first_name"));
	 * empl.setLastName(rs.getString("last_name")); // build his address Address
	 * addr = new Address(); addr.setId(empl.getId());
	 * addr.setAddress(rs.getString("address")); empl.setAddress(addr); return
	 * empl; }
	 */

	private static List<Office> fetchCorrespondOffices(Connection con,
			List<Long> employeeIds) throws SQLException {
		Statement statem = null;
		ResultSet rsltSet = null;
		try {
			List<Office> offices = new ArrayList<Office>();
			statem = con.createStatement();
			rsltSet = statem.executeQuery(buildQueryWithIdList(employeeIds,
					getProperty(OFFICE_LIST)));
			while (rsltSet.next()) {
				offices.add(buildOffice(rsltSet));
			}
			return offices;
		} finally {
			closeResultSet(rsltSet);
			closeStatement(statem);
		}
	}

	private static Office buildOffice(ResultSet rs) throws SQLException {
		// build office
		Office office = new Office();
		office.setId(rs.getLong("office_id"));
		office.setNumberOfEmployees(rs.getInt("number_of_employees"));
		// build company
		Company company = new Company();
		company.setId(rs.getLong("company_id"));
		company.setName(rs.getString("company_name"));
		office.setCompany(company);
		// build company address
		Address addr = new Address();
		addr.setId(rs.getLong("address_id"));
		addr.setAddress(rs.getString("office_address"));
		office.setAddress(addr);
		// build office city
		City city = new City();
		city.setId(rs.getLong("city_id"));
		city.setName(rs.getString("city_name"));
		addr.setCity(city);
		// build office country
		Country country = new Country();
		country.setId(rs.getLong("country_id"));
		country.setName(rs.getString("country_name"));
		city.setCountry(country);

		return office;
	}

	private static void wireOfficesWithEmployees(Map<Long, Employee> employees,
			List<Office> offices) {

	}

	private static void closeResultSet(ResultSet rs) throws SQLException {
		if (rs != null) {
			rs.close();
		}
	}

	private static void closeStatement(Statement st) throws SQLException {
		if (st != null) {
			st.close();
		}
	}
}

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

final class EmployeePaginalDaoJDBC implements EmployeePaginalDao {
	private static final EmployeePaginalDao dao = new EmployeePaginalDaoJDBC();

	// SQL query keys
	private static final String EMPLOYEE_LIST = "query.employee.list";

	// Column names
	private static final String EMPLOYEE_ID_COL = "employee_id";
	private static final String POSITION_COL = "position";
	private static final String FIRST_NAME_COL = "first_name";
	private static final String LAST_NAME_COL = "last_name";
	private static final String EMPLOYEE_ADDRESS_COL = "employee_address";
	private static final String OFFICE_ID_COL = "office_id";
	private static final String NUMBER_OF_EMPLOYEES_COL = "number_of_employees";
	private static final String COMPANY_ID_COL = "company_id";
	private static final String COMPANY_NAME_COL = "company_name";
	private static final String ADDRESS_ID_COL = "address_id";
	private static final String OFFICE_ADDRESS_COL = "office_address";
	private static final String CITY_ID_COL = "city_id";
	private static final String CITY_NAME_COL = "city_name";
	private static final String COUNTRY_ID_COL = "country_id";
	private static final String COUNTRY_NAME_COL = "country_name";

	private EmployeePaginalDaoJDBC() {
	}

	public static EmployeePaginalDao getInstance() {
		return dao;
	}

	@Override
	public List<Employee> getEmployees(int rowsNumber) throws Exception {
		Connection con = null;
		ConnectionPool pool = ConnectionPool.getInstance();
		try {
			con = pool.getConnection();
			List<Employee> employees = fetchCorrespondEmployees(con, rowsNumber);
			return employees;
		} finally {
			pool.makeConnectionFree(con);
		}
	}

	private static List<Employee> fetchCorrespondEmployees(Connection con,
			int rowsNumber) throws SQLException {
		PreparedStatement statem = null;
		ResultSet rsltSet = null;
		try {
			statem = con.prepareStatement(getProperty(EMPLOYEE_LIST));
			statem.setInt(1, rowsNumber);
			rsltSet = statem.executeQuery();
			// processing of resultSet
			List<Employee> employees = new ArrayList<Employee>();
			long currentEmployeeId;
			long previousEmployeeId = -1;
			Employee empl = null;
			Position position = null;
			Office office = null;
			Map<Office, Position> jobs = null;
			// resultSet ordered by employee id
			while (rsltSet.next()) {
				currentEmployeeId = rsltSet.getLong(EMPLOYEE_ID_COL);
				// if not equal then group of rows of another employee started
				if (currentEmployeeId != previousEmployeeId) {
					// adding of previous employee to list
					if (empl != null) {
						empl.setJobs(jobs);
						employees.add(empl);
					}
					// fetch new employee and fetch his first job
					empl = buildEmployee(rsltSet, currentEmployeeId);
					position = new Position(rsltSet.getString(POSITION_COL));
					office = buildOffice(rsltSet);
					jobs = new HashMap<Office, Position>();
					jobs.put(office, position);
				} else {
					// add office to existing employee
					position = new Position(rsltSet.getString(POSITION_COL));
					office = buildOffice(rsltSet);
					jobs.put(office, position);
				}
			}
			return employees;
		} finally {
			closeResultSet(rsltSet);
			closeStatement(statem);
		}
	}

	private static Employee buildEmployee(ResultSet rs, long employeeId)
			throws SQLException {
		// build employee
		Employee empl = new Employee(employeeId);
		empl.setFirstName(rs.getString(FIRST_NAME_COL));
		empl.setLastName(rs.getString(LAST_NAME_COL));
		// build his address
		Address addr = new Address();
		addr.setId(empl.getId());
		addr.setAddress(rs.getString(EMPLOYEE_ADDRESS_COL));
		empl.setAddress(addr);
		return empl;
	}

	private static Office buildOffice(ResultSet rs) throws SQLException {
		// build office
		Office office = new Office();
		office.setId(rs.getLong(OFFICE_ID_COL));
		office.setNumberOfEmployees(rs.getInt(NUMBER_OF_EMPLOYEES_COL));
		// build company
		Company company = new Company();
		company.setId(rs.getLong(COMPANY_ID_COL));
		company.setName(rs.getString(COMPANY_NAME_COL));
		office.setCompany(company);
		// build company address
		Address addr = new Address();
		addr.setId(rs.getLong(ADDRESS_ID_COL));
		addr.setAddress(rs.getString(OFFICE_ADDRESS_COL));
		office.setAddress(addr);
		// build office city
		City city = new City();
		city.setId(rs.getLong(CITY_ID_COL));
		city.setName(rs.getString(CITY_NAME_COL));
		addr.setCity(city);
		// build office country
		Country country = new Country();
		country.setId(rs.getLong(COUNTRY_ID_COL));
		country.setName(rs.getString(COUNTRY_NAME_COL));
		city.setCountry(country);

		return office;
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

	@Override
	public List<Employee> getEmployees(int numEmployeesPerPage, int pageNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int countEmployees() {
		// TODO Auto-generated method stub
		return 0;
	}
}

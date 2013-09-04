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
import com.epam.ht.entity.employee.Employee;
import com.epam.ht.entity.office.Office;

final class EmployeeDAOJDBC implements EmployeeDAO {
	private static final EmployeeDAO dao = new EmployeeDAOJDBC();

	private static final int EMPLOYEES_NUMBER = 100;

	// SQL query keys
	private static final String EMPLOYEE_IDS = "query.employee.ids";
	private static final String EMPLOYEE_LIST = "query.employee.list";
	private static final String OFFICE_LIST = "query.office.list";

	public static EmployeeDAO getInstance() {
		return dao;
	}

	@Override
	public List<Employee> getEmployees() throws Exception {
		Connection con = null;
		ConnectionPool pool = ConnectionPool.getInstance();
		try {
			con = pool.getConnection();
			List<Long> employeeIds = getEmployeeIds(con);
			Map<Long, Employee> employees = fetchCorrespondEmployees(con,
					employeeIds);
			List<Office> offices = fetchCorrespondOffices(con, employeeIds);
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

	private static Map<Long, Employee> fetchCorrespondEmployees(Connection con,
			List<Long> employeeIds) throws SQLException {
		Statement statem = null;
		ResultSet rsltSet = null;
		try {
			Map<Long, Employee> employees = new HashMap<Long, Employee>();
			statem = con.createStatement();
			rsltSet = statem.executeQuery(buildQueryWithIdList(employeeIds,
					getProperty(EMPLOYEE_LIST)));
			Employee empl = null;
			while (rsltSet.next()) {
				empl = buildEmployee(rsltSet);
				employees.put(empl.getId(), empl);
			}
			return employees;
		} finally {
			closeResultSet(rsltSet);
			closeStatement(statem);
		}
	}

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

	private static Employee buildEmployee(ResultSet rs) throws SQLException {
		// make employee
		Employee empl = new Employee();
		empl.setId(rs.getLong(1));
		empl.setFirstName(rs.getString(2));
		empl.setLastName(rs.getString(3));
		// make his address
		Address addr = new Address();
		addr.setId(empl.getId());
		addr.setAddress(rs.getString(4));
		empl.setAddress(addr);
		return empl;
	}

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

	private static Office buildOffice(ResultSet rsltSet) {
		return null;
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

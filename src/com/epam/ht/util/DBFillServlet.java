package com.epam.ht.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.ht.db.pool.ConnectionPool;

public class DBFillServlet extends HttpServlet {
	private static final long serialVersionUID = -5340139606686218447L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		processRequest(req, resp);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		processRequest(req, resp);
	}

	private void processRequest(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Connection con = null;
		try {
			con = ConnectionPool.getInstance().getConnection();
			PreparedStatement country = con.prepareStatement(
					"insert into yra.country(country_id, country_name) " +
					"values(yra.country_id_seq.nextval, 'Belarus')");
			PreparedStatement city = con.prepareStatement(
					"insert into yra.city(city_id, city_name, country_id)" +
					" values(yra.city_id_seq.nextval, 'Minks', yra.country_id_seq.currval)");
			PreparedStatement address = con.prepareStatement(
					"insert into yra.address(address_id, address, city_id)" +
					" values(yra.address_id_seq.nextval, 'Kuprevicha 1', yra.city_id_seq.currval)");
			PreparedStatement company = con.prepareStatement(
					"insert into yra.company(company_id, company_name)" +
					" values(yra.company_id_seq.nextval, 'epam')");
			PreparedStatement employee = con.prepareStatement(
					"insert into yra.employee(employee_id, first_name, last_name)" +
					" values(yra.address_id_seq.currval, 'Yury', 'Yaroshevich')");
			PreparedStatement position = con.prepareStatement(
					"insert into yra.position(position_id, position)" +
					" values(yra.position_id_seq.nextval, 'programmer')");
			PreparedStatement office = con.prepareStatement(
					"insert into yra.office(office_id, company_id, address_id)" +
					" values(yra.office_id_seq.nextval, yra.company_id_seq.currval," +
					" yra.address_id_seq.currval)");
			PreparedStatement offiEmpl = con.prepareStatement(
					"insert into yra.office_employee(employee_id, office_id, position_id)" +
					" values(yra.address_id_seq.currval, yra.office_id_seq.currval," +
					" yra.position_id_seq.currval)");
			
			for (int i = 1; i <= 20000; i++) {
				country.execute();
				city.execute();
				address.execute();
				if (i % 2 == 0) {
					employee.execute();
					position.execute();
					offiEmpl.execute();
				} else {
					company.execute();
					office.execute();
				}
			}
			
			country.close();
			city.close();
			address.close();
			company.close();
			employee.close();
			office.close();
			offiEmpl.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		} finally {
			try {
				ConnectionPool.getInstance().makeConnectionFree(con);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}

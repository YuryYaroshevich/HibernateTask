package com.epam.ht.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DBFillServlet extends HttpServlet {
	private static final long serialVersionUID = 959809129122389741L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException {
		processRequest(req, resp);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException {
		processRequest(req, resp);
	}

	private static void processRequest(HttpServletRequest req,
			HttpServletResponse resp) throws ServletException {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe", "yra", "1234");
			// insert into yra.country(country_id, country_name)
			// values(yra.country_id_seq.nextval,?)
			PreparedStatement putCountry = con
					.prepareStatement("insert into yra.country(country_id, country_name)"
							+ " values(yra.country_id_seq.nextval,?)");
			// insert into yra.city(city_id, city_name, country_id)
			// values(yra.city_id_seq.nextval,?, yra.country_id_seq.currval)
			PreparedStatement putCity = con
					.prepareStatement("insert into "
							+ "yra.city(city_id, city_name, country_id)"
							+ " values(yra.city_id_seq.nextval,?, yra.country_id_seq.currval)");
			// insert into yra.address(address_id, city_id, address)
			// values(address_id_seq.nextval, city_id_seq.currval, ?)
			PreparedStatement putAddress = con.prepareStatement("insert into "
					+ "yra.address(address_id, city_id, address) "
					+ "values(address_id_seq.nextval, city_id_seq.currval, ?)");
			// insert into yra.employee(employee_id, first_name, last_name,
			// address_id)
			// values(yra.employee_id_seq.nextval, ?, ?,
			// yra.address_id_seq.currval)
			PreparedStatement putEmployee = con
					.prepareStatement("insert into"
							+ " yra.employee(employee_id, first_name, last_name, address_id) "
							+ "values(yra.employee_id_seq.nextval, ?, ?, yra.address_id_seq.currval)");

			// insert into yra.company(company_id, company_name)
			// values(company_id_seq.nextval,?)
			PreparedStatement putCompany = con
					.prepareStatement("insert into yra.company(company_id, company_name) "
							+ "values(company_id_seq.nextval,?)");
			// insert into yra.company_address(company_id, address_id)
			// values(yra.company_id_seq.currval, yra.address_id_seq.currval)
			PreparedStatement putCompanyAddress = con
					.prepareStatement("insert into "
							+ "yra.company_address(company_id, address_id)"
							+ " values(yra.company_id_seq.currval, yra.address_id_seq.currval)");
			// insert into yra.company_employee(company_id, employee_id,
			// position) values(yra.company_id_seq.currval,
			// yra.employee_id_seq.currval, ?)
			PreparedStatement putCompanyEmployee = con
					.prepareStatement("insert "
							+ "into yra.company_employee(company_id, employee_id,position) "
							+ "values(yra.company_id_seq.currval,yra.employee_id_seq.currval, ?)");
			for (int i = 0; i < 5000; i++) {
				putCountry.setString(1, "USA");
				putCountry.executeUpdate();

				putCity.setString(1, "Gotham");
				putCity.executeUpdate();

				putAddress.setString(1, "Cave");
				putAddress.executeUpdate();

				if ((i % 2) == 0) {
					putEmployee.setString(1, "Dark");
					putEmployee.setString(2, "Knight");
					putEmployee.executeUpdate();
				} else {
					putCompany.setString(1, "CleamCity");
					putCompany.executeUpdate();
					putCompanyAddress.executeUpdate();
				}

				if (i > 2) {
					putCompanyEmployee.setString(1, "superhero");
					putCompanyEmployee.executeUpdate();
				}
			}

			putCountry.close();
			putCity.close();
			putAddress.close();
			putEmployee.close();
			putCompanyAddress.close();
			con.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new ServletException(e);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
	}
}

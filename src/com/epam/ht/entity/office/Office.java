package com.epam.ht.entity.office;

import java.util.List;

import com.epam.ht.entity.address.Address;
import com.epam.ht.entity.company.Company;
import com.epam.ht.entity.employee.Employee;

public class Office {
	private long officeId;
	
	private Company company;
	
	private Address adderss;
	
	private List<Employee> employees;
	
	private int numberOfEmployees;
	
	public int getNumberOfEmployees() {
		return numberOfEmployees;
	}

	public void setNumberOfEmployees(int numberOfEmployees) {
		this.numberOfEmployees = numberOfEmployees;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	public long getOfficeId() {
		return officeId;
	}

	public void setOfficeId(long officeId) {
		this.officeId = officeId;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public Address getAdderss() {
		return adderss;
	}

	public void setAdderss(Address adderss) {
		this.adderss = adderss;
	}
}

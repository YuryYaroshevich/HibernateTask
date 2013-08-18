package com.epam.ht.entity.office;

import java.io.Serializable;
import java.util.Set;

import com.epam.ht.entity.address.Address;
import com.epam.ht.entity.company.Company;
import com.epam.ht.entity.employee.Employee;

public class Office implements Serializable {
	private static final long serialVersionUID = 1130756185750654144L;

	private long id;
	private Company company;
	private Address address;

	private Set<Employee> employees;
	
	private int numberOfEmployees;	

	public Office() {
	}
	
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	
	public int getNumberOfEmployees() {
		return numberOfEmployees;
	}

	public void setNumberOfEmployees(int numberOfEmployees) {
		this.numberOfEmployees = numberOfEmployees;
	}

	public Set<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
	}

	public long getId() {
		return id;
	}

	private void setId(long officeId) {
		this.id = officeId;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

}

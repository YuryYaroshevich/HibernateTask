package com.epam.ht.entity.employee;

import java.util.List;

import com.epam.ht.entity.address.Address;
import com.epam.ht.entity.office.Office;

public class Employee {
	private long employeeId;
	private String firstName;
	private String lastName;
	
	private Address address;
	
	private List<Office> offices;
	
	public List<Office> getOffices() {
		return offices;
	}

	public void setOffices(List<Office> offices) {
		this.offices = offices;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(long employeeId) {
		this.employeeId = employeeId;
	}

	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}	
}

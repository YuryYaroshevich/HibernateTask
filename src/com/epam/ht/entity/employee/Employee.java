package com.epam.ht.entity.employee;

import java.io.Serializable;
import java.util.Set;

import com.epam.ht.entity.address.Address;
import com.epam.ht.entity.office.Office;

public class Employee implements Serializable {
	private static final long serialVersionUID = -8246951586123338991L;
	
	private long employeeId;
	private String firstName;
	private String lastName;
	
	private Address address;
	
	private Set<Office> offices;
	
	public Employee() {
	}
	
	public Set<Office> getOffices() {
		return offices;
	}

	public void setOffices(Set<Office> offices) {
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

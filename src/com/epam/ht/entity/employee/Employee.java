package com.epam.ht.entity.employee;

import java.io.Serializable;
import java.util.Map;

import com.epam.ht.entity.address.Address;
import com.epam.ht.entity.office.Office;

public class Employee implements Serializable {
	private static final long serialVersionUID = -8246951586123338991L;
	
	private long id;
	private String firstName;
	private String lastName;
	
	private Address address;
	
	private Map<Office, String> offices;
	
	public Employee() {
	}
	
	public Map<Office, String> getOffices() {
		return offices;
	}

	public void setOffices(Map<Office, String> offices) {
		this.offices = offices;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public long getId() {
		return id;
	}

	private void setId(long employeeId) {
		this.id = employeeId;
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

	@Override
	public int hashCode() {
		final int prime = 53;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result
				+ ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result
				+ ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((offices == null) ? 0 : offices.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}	
		if ((obj == null) || (getClass() != obj.getClass())) {
			return false;
		}	
		Employee other = (Employee) obj;
		if (address == null) {
			if (other.address != null) {
				return false;
			}	
		} else if (!address.equals(other.address)) {
			return false;
		}	
		if (firstName == null) {
			if (other.firstName != null) {
				return false;
			}	
		} else if (!firstName.equals(other.firstName)) {
			return false;
		}	
		if (id != other.id) {
			return false;
		}	
		if (lastName == null) {
			if (other.lastName != null) {
				return false;
			}	
		} else if (!lastName.equals(other.lastName)) {
			return false;
		}	
		if (offices == null) {
			if (other.offices != null) {
				return false;
			}	
		} else if (!offices.equals(other.offices)) {
			return false;
		}	
		return true;
	}	
}

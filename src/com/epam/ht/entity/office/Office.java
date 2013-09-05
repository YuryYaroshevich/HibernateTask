package com.epam.ht.entity.office;

import java.io.Serializable;

import com.epam.ht.entity.address.Address;
import com.epam.ht.entity.company.Company;

public class Office implements Serializable {
	private static final long serialVersionUID = 1130756185750654144L;
	
	private long id;
	private Company company;
	private Address address;
	
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

	public long getId() {
		return id;
	}

	public void setId(long officeId) {
		this.id = officeId;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	@Override
	public int hashCode() {
		final int prime = 43;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((company == null) ? 0 : company.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + numberOfEmployees;
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
		Office other = (Office) obj;
		if (address == null) {
			if (other.address != null) {
				return false;
			}	
		} else if (!address.equals(other.address)) {
			return false;
		}	
		if (company == null) {
			if (other.company != null) {
				return false;
			}	
		} else if (!company.equals(other.company)) {
			return false;
		}		
		if (id != other.id) {
			return false;
		}	
		if (numberOfEmployees != other.numberOfEmployees) {
			return false;
		}	
		return true;
	}
}

package com.epam.ht.entity.office;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Formula;

import com.epam.ht.entity.address.Address;
import com.epam.ht.entity.company.Company;

@Entity
public class Office implements Serializable {
	private static final long serialVersionUID = 1130756185750654144L;

	@Id
	@SequenceGenerator(name = "office_id_generator", sequenceName = "OFFICE_ID_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "office_id_generator")
	@Column(name = "OFFICE_ID")
	private long id;

	@Fetch(FetchMode.JOIN)
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "COMPANY_ID")
	private Company company;

	@Fetch(FetchMode.JOIN)
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ADDRESS_ID")
	private Address address;

	@Formula("(select count(*) from yra.OFFICE_EMPLOYEE oe"
			+ " where oe.office_id = office_id)")
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

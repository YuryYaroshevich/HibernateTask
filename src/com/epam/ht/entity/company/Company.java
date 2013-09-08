package com.epam.ht.entity.company;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Company implements Serializable {
	private static final long serialVersionUID = -4334175722566107012L;
	
	private long id;
	private String name;
	
	public Company() {
	}
	
	public long getId() {
		return id;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "COMPANY_ID_SEQ")
	@Column(name = "COMPANY_ID")
	public void setId(long companyId) {
		this.id = companyId;
	}
	
	public String getName() {
		return name;
	}
	
	@Column(name = "COMPANY_NAME")
	public void setName(String name) {
		this.name = name;
	}
	
	public String toString() {
		return name;
	}

	@Override
	public int hashCode() {
		final int prime = 7;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Company other = (Company) obj;
		if (id != other.id) {
			return false;
		}	
		if (name == null) {
			if (other.name != null) {
				return false;
			}	
		} else if (!name.equals(other.name)) {
			return false;
		}	
		return true;
	}
}

package com.epam.ht.entity.country;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Country implements Serializable {
	private static final long serialVersionUID = 7799307133606363130L;

	private String name;
	private long id;

	public Country() {
	}

	public String getName() {
		return name;
	}

	@Column(name = "COUNTRY_NAME")
	public void setName(String name) {
		this.name = name;
	}

	public long getId() {
		return id;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "COUNTRY_ID_SEQ")
	@Column(name = "COUNTRY_ID")
	public void setId(long countryId) {
		this.id = countryId;
	}

	public String toString() {
		return name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
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
		Country other = (Country) obj;
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

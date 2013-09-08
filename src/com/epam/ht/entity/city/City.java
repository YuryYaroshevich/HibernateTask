package com.epam.ht.entity.city;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.epam.ht.entity.country.Country;

@Entity
public class City implements Serializable {
	private static final long serialVersionUID = 5958802680632997798L;

	private long id;
	private String name;

	private Country country;

	public City() {
	}

	public Country getCountry() {
		return country;
	}

	@Id 
	@ManyToOne
	@JoinColumn(name = "COUNTRY_ID")
	public void setCountry(Country country) {
		this.country = country;
	}

	public long getId() {
		return id;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CITY_ID_SEQ")
	@Column(name = "CITY_ID")
	public void setId(long cityId) {
		this.id = cityId;
	}

	public String getName() {
		return name;
	}

	@Column(name = "CITY_NAME")
	public void setName(String name) {
		this.name = name;
	}

	public String toString() {
		return name;
	}

	@Override
	public int hashCode() {
		final int prime = 17;
		int result = 1;
		result = prime * result + ((country == null) ? 0 : country.hashCode());
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
		City other = (City) obj;
		if (country == null) {
			if (other.country != null) {
				return false;
			}	
		} else if (!country.equals(other.country)) {
			return false;
		}
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

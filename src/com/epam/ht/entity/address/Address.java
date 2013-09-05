package com.epam.ht.entity.address;

import java.io.Serializable;

import com.epam.ht.entity.city.City;

public class Address implements Serializable {
	private static final long serialVersionUID = 4743510992982011631L;
	
	private long id;
	private String address;
	
	private City city;
	
	public Address() {
	}

	public long getId() {
		return id;
	}

	public void setId(long addressId) {
		this.id = addressId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}
	
	public String toString() {
		return address;
	}

	@Override
	public int hashCode() {
		final int prime = 5;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
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
		Address other = (Address) obj;
		if (address == null) {
			if (other.address != null) {
				return false;
			}	
		} else if (!address.equals(other.address)) {
			return false;
		}	
		if (city == null) {
			if (other.city != null) {
				return false;
			}	
		} else if (!city.equals(other.city)) {
			return false;
		}	
		if (id != other.id) {
			return false;
		}	
		return true;
	}
}

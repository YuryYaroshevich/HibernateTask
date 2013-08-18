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

	private void setId(long addressId) {
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
}

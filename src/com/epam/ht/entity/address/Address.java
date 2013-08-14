package com.epam.ht.entity.address;

import com.epam.ht.entity.city.City;

public class Address {
	private long addressId;
	private String address;
	
	private City city;

	public long getAddressId() {
		return addressId;
	}

	public void setAddressId(long addressId) {
		this.addressId = addressId;
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

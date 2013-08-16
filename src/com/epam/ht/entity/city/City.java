package com.epam.ht.entity.city;

import java.io.Serializable;

import com.epam.ht.entity.country.Country;

public class City implements Serializable {
	private static final long serialVersionUID = 5958802680632997798L;
	
	private long cityId;
	private String name;
	
	private Country country;
	
	public City() {
	}
	
	public Country getCountry() {
		return country;
	}
	
	public void setCountry(Country country) {
		this.country = country;
	}
	
	public long getCityId() {
		return cityId;
	}
	
	public void setCityId(long cityId) {
		this.cityId = cityId;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String toString() {
		return name;
	}
}

package com.epam.ht.entity.country;

public class Country {
	private String name;
	private long countryId;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getCountryId() {
		return countryId;
	}

	public void setCountryId(long countryId) {
		this.countryId = countryId;
	}
	
	public String toString() {
		return name;
	}
}

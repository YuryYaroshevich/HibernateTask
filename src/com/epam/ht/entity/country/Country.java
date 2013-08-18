package com.epam.ht.entity.country;

import java.io.Serializable;

public class Country implements Serializable {
	private static final long serialVersionUID = 7799307133606363130L;
	
	private String name;
	private long id;
	
	public Country() {
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getId() {
		return id;
	}

	private void setId(long countryId) {
		this.id = countryId;
	}
	
	public String toString() {
		return name;
	}
}

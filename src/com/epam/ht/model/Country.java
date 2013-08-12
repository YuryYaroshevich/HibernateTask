package com.epam.ht.model;

public class Country {
	private String name;
	private long id;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Country(String name) {
		this.name = name;
	}
}

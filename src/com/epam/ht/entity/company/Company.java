package com.epam.ht.entity.company;

import java.io.Serializable;

public class Company implements Serializable {
	private static final long serialVersionUID = -4334175722566107012L;
	
	private long id;
	private String name;
	
	public Company() {
	}
	
	public long getId() {
		return id;
	}
	
	private void setId(long companyId) {
		this.id = companyId;
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

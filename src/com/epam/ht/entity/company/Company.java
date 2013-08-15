package com.epam.ht.entity.company;

import java.io.Serializable;

public class Company implements Serializable {
	private static final long serialVersionUID = -4334175722566107012L;
	
	private long companyId;
	private String name;
	
	public Company() {
	}
	
	public long getCompanyId() {
		return companyId;
	}
	
	public void setCompanyId(long companyId) {
		this.companyId = companyId;
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

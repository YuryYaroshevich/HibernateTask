package com.epam.ht.entity;

public class Office {
	private long officeId;
	
	private Company company;
	
	private Address adderss;

	public long getOfficeId() {
		return officeId;
	}

	public void setOfficeId(long officeId) {
		this.officeId = officeId;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public Address getAdderss() {
		return adderss;
	}

	public void setAdderss(Address adderss) {
		this.adderss = adderss;
	}
}

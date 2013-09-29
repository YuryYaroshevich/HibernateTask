package com.epam.ht.entity.office;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import com.epam.ht.entity.address.Address;
import com.epam.ht.entity.company.Company;

@StaticMetamodel(Office.class)
public class Office_ {
	public static volatile SingularAttribute<Office, Long> id;
	
	public static volatile SingularAttribute<Office, Company> company;
	
	public static volatile SingularAttribute<Office, Address> address;
	
	public static volatile SingularAttribute<Office, Integer> numberOfEmployees;
}

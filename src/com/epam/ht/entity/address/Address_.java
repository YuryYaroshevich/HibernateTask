package com.epam.ht.entity.address;

import javax.persistence.metamodel.SingularAttribute;

import com.epam.ht.entity.city.City;

public class Address_ {
	public static volatile SingularAttribute<Address, Long> id;
	
	public static volatile SingularAttribute<Address, String> address;
	
	public static volatile SingularAttribute<Address, City> city;
}

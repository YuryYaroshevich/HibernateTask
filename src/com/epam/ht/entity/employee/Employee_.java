package com.epam.ht.entity.employee;

import java.util.Map;

import javax.persistence.metamodel.MapAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import com.epam.ht.entity.address.Address;
import com.epam.ht.entity.office.Office;

@StaticMetamodel(Employee.class)
public class Employee_ {
	public static volatile SingularAttribute<Employee, Long> id;

	public static volatile SingularAttribute<Employee, String> firstName;

	public static volatile SingularAttribute<Employee, String> lastName;

	public static volatile SingularAttribute<Employee, Address> address;

	public static volatile MapAttribute<Employee, Office, Map<Office, String>> jobs;
}

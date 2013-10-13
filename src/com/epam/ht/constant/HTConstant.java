package com.epam.ht.constant;

public final class HTConstant {
	private HTConstant() {
	}

	// parameter names I need for paging tag
	public static final String CURRENT_PAGE_INDEX = "currentIndex";
	public static final String NUMBER_OF_EMPLOYEES = "numOfEntities";

	// attribute name for setting value in session
	public static final String EMPLOYEES = "employees";
	public static final String N_EMPLS_PER_PAGE = "numOfEntsPerPage";

	public static final int DEFAULT_PAGE_INDEX = 1;

	// query name
	public static final String CORRESPOND_EMPLOYEE_IDS = "query.CorrespondEmployeeIds";
	public static final String EMPLOYEE_LIST = "query.EmployeeList";
	public static final String CORRESPOND_OFFICES = "query.CorrespondOffices";
	public static final String CORRESPOND_OFFICE_IDS = "query.CorrespondOfficeIds";
	public static final String EMPLOYEES_NUMBER = "query.EmployeesNumber";
	
	// query parameters
	public static final String EMPLOYEE_IDS_PARAM = "employee_ids";
	public static final String OFFICE_IDS_PARAM = "office_ids";
}

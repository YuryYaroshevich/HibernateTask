package com.epam.ht.constant;

public final class HTConstant {
	private HTConstant() {
	}

	public static final String HIBERNATE_CONFIG_PATH = "hibernate.config";

	// default number of rows I take from table
	public static final int DEFAULT_EMPLOYEES_NUMBER = 100;

	// parameter names I need for paging tag
	public static final String NUMBER_OF_PAGES = "numberOfPages";
	public static final String CURRENT_PAGE_INDEX = "currentPageIndex";

	// attribute name for setting value in session
	public static final String EMPLOYEES = "employees";
	public static final String N_EMPLS_PER_PAGE = "nEmplsPerPage";

	public static final int DEFAULT_PAGE_INDEX = 1;

	// query name
	public static final String CORRESPOND_EMPLOYEE_IDS = "query.CorrespondEmployeeIds";
	public static final String EMPLOYEE_LIST = "query.EmployeeList";
	public static final String CORRESPOND_OFFICES = "query.CorrespondOffices";
	public static final String CORRESPOND_OFFICE_IDS = "query.CorrespondOfficeIds";
	public static final String EMPLOYEES_NUMBER = "query.EmployeesNumber";
	
	public static final String EMPLOYEE_IDS_PARAM = "#employee_ids";
}

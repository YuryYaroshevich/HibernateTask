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
}

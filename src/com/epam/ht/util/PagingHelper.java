package com.epam.ht.util;

public final class PagingHelper {
	private PagingHelper() {
	}
	
	public static int countNumberOfPages(int numberOfEmployees,
			int nEmplPerPage) {
		if (numberOfEmployees < nEmplPerPage) {
			return 1;
		} else if (numberOfEmployees % nEmplPerPage != 0) {
			return (numberOfEmployees / nEmplPerPage) + 1;
		} else {
			return (numberOfEmployees / nEmplPerPage);
		}
	}
	
	// counts first page index in list of page indexes of paging tag
	public static int countFirstPageIndex(int currentPageIndex, int pageIndexesPerPage) {
		if ((currentPageIndex > pageIndexesPerPage)
				&& (currentPageIndex % pageIndexesPerPage != 0)) {
			return (currentPageIndex / pageIndexesPerPage)
					* pageIndexesPerPage + 1;
		} else if ((currentPageIndex > pageIndexesPerPage)
				&& (currentPageIndex % pageIndexesPerPage == 0)) {
			return currentPageIndex - pageIndexesPerPage + 1;
		} else {
			return 1;
		}
	}
}

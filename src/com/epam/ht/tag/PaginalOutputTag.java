package com.epam.ht.tag;

import java.io.IOException;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class PaginalOutputTag extends TagSupport {
	private static final long serialVersionUID = -7680707601997596255L;

	private int indexesPerPage;
	private int currentIndex;
	private int numOfEntities;
	private int numOfEntsPerPage;

	// HTML parts of tag
	private static final String SET_N_ENTITIES_PER_PAGE_FORM = "<form id='entites-number-form' action='controller'"
			+ " method='POST'><input type='hidden' name='command' value='SET_NUMB_EMPLOYEES_PER_PAGE' />"
			+ "Number of entities per page:<input id='entities-number' type='text' name='numOfEntsPerPage' />"
			+ "<input id='enter-button' type='submit' value='ENTER' /></form>";
	private static final String GO_TO_PAGE_FORM = "<form id='go-to-page-form' action='controller' method='POST'>"
			+ "<input type='hidden' name='command' value='VIEW_PAGE' />"
			+ "Enter page number:<input type='text' name='currentIndex' />"
			+ "<input id='go-button' type='submit' value='GO' /></form>";
	private static final String ANCHOR_TAG_BEGIN = "<a class='page-number' "
			+ "href='/HibernateTask/controller?command=VIEW_PAGE&currentIndex=";
	private static final String ANCHOR_TAG_END_LEFT_EDGE = "'>&lt; </a>";
	private static final String ANCHOR_TAG_END_RIGHT_EDGE = "'> &gt;</a>";
	private static final String DIV_TAG_BEGIN = "<div>";
	private static final String DIV_TAG_END = "</div>";

	public int getIndexesPerPage() {
		return indexesPerPage;
	}

	public void setIndexesPerPage(int indexesPerPage) {
		this.indexesPerPage = indexesPerPage;
	}

	public int getCurrentIndex() {
		return currentIndex;
	}

	public void setCurrentIndex(int currentIndex) {
		this.currentIndex = currentIndex;
	}

	public int getNumOfEntities() {
		return numOfEntities;
	}

	public void setNumOfEntities(int numOfEntities) {
		this.numOfEntities = numOfEntities;
	}

	public int getNumOfEntsPerPage() {
		return numOfEntsPerPage;
	}

	public void setNumOfEntsPerPage(int numOfEntsPerPage) {
		this.numOfEntsPerPage = numOfEntsPerPage;
	}

	public int doStartTag() {
		int numberOfPages = countNumberOfPages(numOfEntities,
				numOfEntsPerPage);
		// count first and last indexes in the list of page numbers
		int firstPageIndex = countFirstPageIndex(currentIndex,
				indexesPerPage);
		int lastPageIndex = firstPageIndex + indexesPerPage - 1;
		if (lastPageIndex > numberOfPages) {
			lastPageIndex = numberOfPages;
		}
		writeTagOnJSP(firstPageIndex, lastPageIndex, numberOfPages,
				pageContext.getOut());
		return SKIP_BODY;
	}

	private static int countNumberOfPages(int numOfEntities, int nEntPerPage) {
		if (numOfEntities < nEntPerPage) {
			return 1;
		} else if (numOfEntities % nEntPerPage != 0) {
			return (numOfEntities / nEntPerPage) + 1;
		} else {
			return (numOfEntities / nEntPerPage);
		}
	}
	
	private static int countFirstPageIndex(int currentPageIndex, int pageIndexesPerPage) {
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

	private static void writeTagOnJSP(int firstPageIndex, int lastPageIndex,
			int numberOfPages, JspWriter writer) {
		try {
			writer.write(SET_N_ENTITIES_PER_PAGE_FORM);
			writer.write(GO_TO_PAGE_FORM);
			writer.write(DIV_TAG_BEGIN);
			if (firstPageIndex > 1) {
				writer.write(buildAnchorTag(firstPageIndex - 1, true, false));
			}
			for (int i = firstPageIndex; i <= lastPageIndex; i++) {
				writer.write(buildAnchorTag(i, false, false));
			}
			if (lastPageIndex < numberOfPages) {
				writer.write(buildAnchorTag(lastPageIndex + 1, false, true));
			}
			writer.write(DIV_TAG_END);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// if leftEdge true then &lt; should be placed in anchor body
	// if rightEdge true then &gt; should be placed in anchor body
	private static String buildAnchorTag(int pageIndex, boolean leftEdge,
			boolean rightEdge) {
		if (leftEdge) {
			return ANCHOR_TAG_BEGIN + pageIndex + ANCHOR_TAG_END_LEFT_EDGE;
		} else if (rightEdge) {
			return ANCHOR_TAG_BEGIN + pageIndex + ANCHOR_TAG_END_RIGHT_EDGE;
		}
		return ANCHOR_TAG_BEGIN + pageIndex + "'> " + pageIndex + "</a>";
	}
}

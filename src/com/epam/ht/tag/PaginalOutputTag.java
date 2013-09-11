package com.epam.ht.tag;

import java.io.IOException;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.epam.ht.util.PagingHelper;

import static com.epam.ht.resource.PropertyGetter.getProperty;
import static com.epam.ht.constant.HTConstant.*;

public class PaginalOutputTag extends TagSupport {
	private static final long serialVersionUID = -7680707601997596255L;

	private static final int PAGE_INDEXES_PER_PAGE = 20;

	// HTML parts of tag
	private static final String SET_NUMB_EMPLOYEES_PER_PAGE = "tag.paging.set.employees.number";
	private static final String ANCHOR_TAG_BEGIN = "tag.paging.anchor.tag.start";
	private static final String ANCHOR_TAG_END_LEFT_EDGE = "tag.paging.anchor.tag.end.left.edge";
	private static final String ANCHOR_TAG_END_RIGHT_EDGE = "tag.paging.anchor.tag.end.right.edge";
	private static final String DIV_TAG_BEGIN = "<div>";
	private static final String DIV_TAG_END = "</div>";

	public int doStartTag() {
		HttpSession session = pageContext.getSession();
		int numberOfPages = (Integer) session.getAttribute(NUMBER_OF_PAGES);
		int currentPageIndex = (Integer) session
				.getAttribute(CURRENT_PAGE_INDEX);
		// count first and last indexes in the list of page numbers
		int firstPageIndex = PagingHelper.countFirstPageIndex(currentPageIndex,
				PAGE_INDEXES_PER_PAGE);
		int lastPageIndex = firstPageIndex + PAGE_INDEXES_PER_PAGE - 1;
		if (lastPageIndex > numberOfPages) {
			lastPageIndex = numberOfPages;
		}
		writeTagOnJSP(firstPageIndex, lastPageIndex, numberOfPages,
				pageContext.getOut());
		return SKIP_BODY;
	}

	private static void writeTagOnJSP(int firstPageIndex, int lastPageIndex,
			int numberOfPages, JspWriter writer) {
		try {
			writer.write(getProperty(SET_NUMB_EMPLOYEES_PER_PAGE));
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
			return getProperty(ANCHOR_TAG_BEGIN) + pageIndex
					+ getProperty(ANCHOR_TAG_END_LEFT_EDGE);
		} else if (rightEdge) {
			return getProperty(ANCHOR_TAG_BEGIN) + pageIndex
					+ getProperty(ANCHOR_TAG_END_RIGHT_EDGE);
		}
		return getProperty(ANCHOR_TAG_BEGIN) + pageIndex + "'> " + pageIndex
				+ "</a>";
	}
}

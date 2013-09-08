package com.epam.ht.tag;

import java.io.IOException;

import javax.servlet.jsp.tagext.TagSupport;

public class PaginalOutputTag extends TagSupport {
	private static final long serialVersionUID = -7680707601997596255L;
	
	public int doStartTag() {
		try {
			pageContext.getOut().write("DIRTY YRA 007 IS IN THE HOUSE");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return SKIP_BODY;
	}
}

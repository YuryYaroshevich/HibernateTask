package com.epam.ht.resource;

import java.util.ResourceBundle;

public final class PropertyGetter {	
	private static final ResourceBundle resourceBundle = ResourceBundle
			.getBundle("com.epam.st.resource.htresource");

	private PropertyGetter() {
	}

	public static String getProperty(String key) {
		return resourceBundle.getString(key);
	}
}
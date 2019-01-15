package com.gl.utils;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;

public class DocumentNamesProperties {

	private static Properties DOCUMENT_NAMES_PROPERTIES;


	private DocumentNamesProperties() {
	}

	static {
		DOCUMENT_NAMES_PROPERTIES = new Properties();
		URL testUrl = ClassLoader.getSystemResource("documentNames.properties");
		try {
			DOCUMENT_NAMES_PROPERTIES.load(testUrl.openStream());
			System.out.println(DOCUMENT_NAMES_PROPERTIES);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String getTestProperty(String key) {
		return DOCUMENT_NAMES_PROPERTIES.getProperty(key);
	}

}

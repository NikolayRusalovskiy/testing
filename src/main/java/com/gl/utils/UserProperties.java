package com.gl.utils;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;

public class UserProperties {

	private static Properties USER_PROPERTIES;

	private UserProperties() {
	}

	static {
		USER_PROPERTIES = new Properties();
		URL testUrl = ClassLoader.getSystemResource("user.properties");
		try {
			USER_PROPERTIES.load(testUrl.openStream());
				} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String getUserProperty(String key) {
		return USER_PROPERTIES.getProperty(key);
	}

}

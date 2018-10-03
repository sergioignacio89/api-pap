package com.experta.pap.utils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class Resources {

	private static Properties props;

	public static Properties getProperties() throws Exception {

		if (props == null) {
			props = loadPropertyFile();
		}
		return props;
	}

	public static Properties loadPropertyFile() throws Exception {

		Properties prop = new Properties();
		InputStream input = null;
		try {

			input = new FileInputStream(System.getProperty("app-resources"));
			prop.load(input);

		} catch (Exception e) {
			throw e;
		} finally {

			if (input != null) {
				try {
					input.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return prop;
	}
}

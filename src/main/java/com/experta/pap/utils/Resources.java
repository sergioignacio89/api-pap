package com.experta.pap.utils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;


/**
 * <p>
 * Clase utilitaria para tratamiento de archivo de configuracion
 * app-resources.properties
 * </p>
 * 
 * <p>
 * El archivo <b>app-resources.properties</b> se recupera mediante
 * System.getProperty()
 * </p>
 * 
 * @author Sergio Massa
 *
 */
public class Resources {

	private static Properties props;

	/**
	 * Recupera el archivo de configuracion .properties
	 *
	 * @author Sergio Massa
	 * 
	 * @return {@link Properties}
	 * 
	 * @throws Exception
	 * 
	 */
	public static Properties getProperties() throws Exception {

		if (props == null) {
			props = loadPropertyFile();
		}
		return props;
	}

	/**
	 * Abre el archivo de configuracion .properties
	 *
	 * @author Sergio Massa
	 * 
	 * @return {@link Properties}
	 * 
	 * @throws Exception
	 * 
	 */
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

package com.experta.pap.utils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Logger;

import com.experta.pap.exceptions.ResourcesException;
import com.experta.pap.service.impl.FileServiceImpl;


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
	
	private static final Logger LOGGER = Logger.getLogger(FileServiceImpl.class.getName());

	private static Properties props;

	/**
	 * Recupera el archivo de configuracion .properties
	 *
	 * @author Sergio Massa
	 * 
	 * @return {@link Properties}
	 * 
	 * @throws ResourcesException
	 * 
	 */
	public static Properties getProperties() throws ResourcesException {

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
	 * @throws ResourcesException
	 * 
	 */
	public static Properties loadPropertyFile() throws ResourcesException {

		Properties prop = new Properties();
		InputStream input = null;
		try {

			input = new FileInputStream(System.getProperty("app-resources"));
			prop.load(input);

		} catch (Exception e) {
			String errorMessage = "Fallo al cargar archivo properties de configuracion. Variable de application server System property es: "
					+ System.getProperty("app-resources");
			
			LOGGER.severe(errorMessage);
			e.printStackTrace();
			throw new ResourcesException(errorMessage);
			
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

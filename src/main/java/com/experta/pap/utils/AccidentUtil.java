package com.experta.pap.utils;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;

import com.experta.pap.controller.AccidentController;
import com.experta.pap.exceptions.ResourcesException;
import com.experta.pap.model.Accident;
import com.experta.pap.model.RangeConfiguration;
import com.experta.pap.model.WrapperRangeConfiguration;

/**
 * Clase utilitaria para {@link Accident}
 * 
 * @author Sergio Massa
 *
 */
public class AccidentUtil {

	private static DecimalFormat decimalFormat;
	private static WrapperRangeConfiguration rangesConfiguration;
	private static final Logger LOGGER = Logger.getLogger(AccidentController.class.getName());
	
	static {
		
		DecimalFormatSymbols formatSymbols = new DecimalFormatSymbols();
		formatSymbols.setDecimalSeparator('.');

		decimalFormat = new DecimalFormat("#.00", formatSymbols); 
		decimalFormat.setRoundingMode(RoundingMode.HALF_UP);
		decimalFormat.setRoundingMode(RoundingMode.HALF_UP);
	}
	
	/**
	 * Mapea los atributos de {@link Accident} para que sea aceptado por IBM Watson
	 * 
	 * @author Sergio Massa
	 * 
	 * @param accident

	 * @return lista de los atributos del {@link Accident}
	 * 
	 * @throws ParseException
	 */
	
	public static List<String> retrieveData(Accident accident) throws ParseException {

		List<String> data = new ArrayList<>();

		data.add("\"" + accident.getSiniestroSeveridad() + "\"");
		data.add("\"" + accident.getSiniestroCausa() + "\"");
		data.add("\"" + accident.getSiniestroParteCuerpo() + "\"");
		data.add("\"" + accident.getSiniestradoDescUltimoDX() + "\"");
		
//		data.add(accident.getSiniestroFKT());
//		data.add(accident.getSiniestroAltaMedica());
		data.add("\"" + accident.getSiniestroFKT() + "\"");
		data.add("\"" + accident.getSiniestroAltaMedica() + "\"");
		data.add(accident.getSiniestroDiagnostico());
		data.add("\"" + accident.getSiniestroCirugia() + "\"");
		data.add("\"" + accident.getSiniestroEstudios() + "\"");
		
		data.add(accident.getSiniestroPeriodo());
		data.add("\"" + accident.getSiniestroPrestadorProvincia() + "\"");
		data.add("\"" + accident.getSiniestroCanalIngreso() + "\"");
		data.add("\"" + accident.getSiniestroCaseSML() + "\"");
		data.add("\"" + accident.getSiniestroCaseSupervisor() + "\"");
		data.add("\"" + accident.getSiniestroPrestador() + "\"");
		
		data.add(accident.getEmpresaCP());
		data.add(accident.getEmpresaProvincia());
		data.add(accident.getSiniestradoNacionalidad());
		data.add(accident.getSiniestradoCP());
		data.add(accident.getSiniestradoSexo());

		if((accident.getSiniestradoFhNacimiento() == null) || ((accident.getSiniestradoFhNacimiento().equals("null"))) ) {
			data.add(accident.getSiniestradoFhNacimiento());
		}  else {
			data.add("\"" + accident.getSiniestradoFhNacimiento() + "\"");
		}
		data.add("\"" + accident.getTipoPoliza() + "\"");
		data.add("\"" + accident.getLocalidadPoliza() + "\"");

		return data;
	}
	
	/**
	 * Formatea el valor de prediccion
	 * 
	 * @author Sergio Massa
	 * 
	 * @param value
	 * 
	 * @return value formateado
	 */
	public static double parsePredictionValue(double value) {
		
		String d = decimalFormat.format(value);
		double doub = Double.parseDouble(d);
		return doub;
	}
	
	/**
	 * Singleton para cargar la configuracion de rangos de criticidad
	 * 
	 * @author Sergio Massa
	 * 
	 * @return list de rangos
	 * @throws ResourcesException 
	 * 
	 * @see RangeConfiguration
	 * @see WrapperRangeConfiguration
	 */
	public static WrapperRangeConfiguration getRangeConfiguration() throws ResourcesException {
	
		if(rangesConfiguration == null) {
			loadRangeConfigurationWrapper();
		}
		
		return rangesConfiguration;
	}
	
	/**
	 * Carga la configuracion de rangos de criticidad desde el archivo de configuracion app-resources.properties
	 * 
	 * @author Sergio Massa
	 * 
	 * @throws ResourcesException
	 * @see RangeConfiguration 
	 * @see WrapperRangeConfiguration
	 */
	private static void loadRangeConfigurationWrapper() throws ResourcesException {

		try {
			Properties prop = Resources.getProperties();

			rangesConfiguration = new WrapperRangeConfiguration();
			
			//..........LOW..........//
			RangeConfiguration low = new RangeConfiguration();

			String caption = String.valueOf(prop.get("pap.ranges.type.low.caption"));
			String min = String.valueOf(prop.get("pap.ranges.type.low.min"));
			String max = String.valueOf(prop.get("pap.ranges.type.low.max"));

			validateNullableValues(caption, min, max);
			
			low.setCaption(String.valueOf(caption));
			low.setMin(String.valueOf(min));
			low.setMax(String.valueOf(max));
			rangesConfiguration.setLow(low);

			//..........MIDDLE..........//			
			RangeConfiguration middle = new RangeConfiguration();
			
			caption = String.valueOf(prop.get("pap.ranges.type.middle.caption"));
			min = String.valueOf(prop.get("pap.ranges.type.middle.min"));
			max = String.valueOf(prop.get("pap.ranges.type.middle.max"));
			
			validateNullableValues(caption, min, max);
			
			middle.setCaption(caption);
			middle.setMin(min);
			middle.setMax(max);
			rangesConfiguration.setMiddle(middle);

			//..........CRITICAL..........//
			RangeConfiguration critical = new RangeConfiguration();
			
			caption = String.valueOf(prop.get("pap.ranges.type.critical.caption"));
			min = String.valueOf(prop.get("pap.ranges.type.critical.min"));
			max = String.valueOf(prop.get("pap.ranges.type.critical.max"));
			
			validateNullableValues(caption, min, max);
			
			critical.setCaption(caption);
			critical.setMin(min);
			critical.setMax(max);
			rangesConfiguration.setCritical(critical);

		} catch (Exception e) {
			LOGGER.severe(e.getMessage());
			e.printStackTrace();
			rangesConfiguration = null;
			throw e;

		} finally {
			
		}
	}
	
	/**
	 * Valida que los valores de rangos de criticidad no sean nulos o vacios
	 * 
	 * @author Sergio Massa
	 * 
	 * @throws ResourcesException
	 */
	private static void validateNullableValues(String caption, String min, String max) throws ResourcesException {
		
		if ((caption == "null") || (caption.equals("")) || (min == "null") || (min.equals("")) || (max == "null")
				|| (max.equals(""))) {
			
			throw new ResourcesException("Los valores de rangos de criticidad son null o empty");
		}
	}
}

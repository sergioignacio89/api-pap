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
import com.experta.pap.exceptions.GenericException;
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
	 * 
	 * @see RangeConfiguration
	 * @see WrapperRangeConfiguration
	 */
	public static WrapperRangeConfiguration getRangeConfiguration() throws GenericException {
	
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
	 * @see RangeConfiguration 
	 * @see WrapperRangeConfiguration
	 */
	private static void loadRangeConfigurationWrapper() throws GenericException {

		try {
			Properties prop = Resources.getProperties();

			rangesConfiguration = new WrapperRangeConfiguration();
			
			RangeConfiguration low = new RangeConfiguration();
			low.setCaption(String.valueOf(prop.get("pap.ranges.type.low.caption")));
			low.setMin(String.valueOf(prop.get("pap.ranges.type.low.min")));
			low.setMax(String.valueOf(prop.get("pap.ranges.type.low.max")));
			rangesConfiguration.setLow(low);

			RangeConfiguration middle = new RangeConfiguration();
			middle.setCaption(String.valueOf(prop.get("pap.ranges.type.middle.caption")));
			middle.setMin(String.valueOf(prop.get("pap.ranges.type.middle.min")));
			middle.setMax(String.valueOf(prop.get("pap.ranges.type.middle.max")));
			rangesConfiguration.setMiddle(middle);

			RangeConfiguration critical = new RangeConfiguration();
			critical.setCaption(String.valueOf(prop.get("pap.ranges.type.critical.caption")));
			critical.setMin(String.valueOf(prop.get("pap.ranges.type.critical.min")));
			critical.setMax(String.valueOf(prop.get("pap.ranges.type.critical.max")));
			rangesConfiguration.setCritical(critical);

		} catch (Exception e) {
			LOGGER.severe(e.getMessage());
			e.printStackTrace();
			rangesConfiguration = null;
			throw new GenericException("error creating configuration ranges");

		} finally {
			
		}

	}
}

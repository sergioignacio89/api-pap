package com.experta.pap.utils;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.experta.pap.model.Accident;

/**
 * Clase utilitaria para {@link Accident}
 * 
 * @author Sergio Massa
 *
 */
public class AccidentUtil {

	private static DecimalFormat decimalFormat;
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
}

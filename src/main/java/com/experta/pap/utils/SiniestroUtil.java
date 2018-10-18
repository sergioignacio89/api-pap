package com.experta.pap.utils;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.Normalizer;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.experta.pap.model.Siniestro;

public class SiniestroUtil {

	private static DecimalFormat decimalFormat;
	static {
		
		DecimalFormatSymbols formatSymbols = new DecimalFormatSymbols();
		formatSymbols.setDecimalSeparator('.');

		decimalFormat = new DecimalFormat("#.00", formatSymbols); 
		decimalFormat.setRoundingMode(RoundingMode.HALF_UP);
		decimalFormat.setRoundingMode(RoundingMode.HALF_UP);
	}
	
	public static List<String> retrieveData(Siniestro siniestro) throws ParseException {

		List<String> data = new ArrayList<>();

		data.add("\"" + siniestro.getSiniestroSeveridad() + "\"");
		data.add("\"" + siniestro.getSiniestroCausa() + "\"");
		data.add("\"" + siniestro.getSiniestroParteCuerpo() + "\"");
		data.add("\"" + siniestro.getSiniestradoDescUltimoDX() + "\"");
		data.add("\"" + siniestro.getSiniestroCircunstancia() + "\"");
		
		data.add("\"" + siniestro.getSiniestroFKT() + "\"");
		data.add("\"" + siniestro.getSiniestroAltaMedica() + "\"");
		data.add(String.valueOf(siniestro.getSiniestroDiagnostico()));
		data.add("\"" + siniestro.getSiniestroCirugia() + "\"");
		data.add("\"" + siniestro.getSiniestroEstudios() + "\"");
		
		data.add(String.valueOf(siniestro.getSiniestroPeriodo()));
		data.add("\"" + siniestro.getSiniestroPrestadorProvincia() + "\"");
		data.add("\"" + siniestro.getSiniestroCanalIngreso() + "\"");
		data.add("\"" + siniestro.getSiniestroCaseSML() + "\"");
		data.add("\"" + siniestro.getSiniestroCaseSupervisor() + "\"");
		data.add("\"" + siniestro.getSiniestroPrestador() + "\"");
		
		data.add(String.valueOf(siniestro.getEmpresaCP()));
		data.add(String.valueOf(siniestro.getEmpresaProvincia()));
		data.add(String.valueOf(siniestro.getSiniestradoNacionalidad()));
		data.add(String.valueOf(siniestro.getSiniestradoCP()));
		data.add(String.valueOf(siniestro.getSiniestradoSexo()));
		
		data.add("\"" + siniestro.getSiniestradoFhNacimiento() + "\"");
		data.add("\"" + siniestro.getTipoPoliza() + "\"");
		data.add("\"" + siniestro.getLocalidadPoliza() + "\"");

		return data;
	}
	
	public static String purifySiniestro(String data) {
		
		data = stripAccents(data);
		data = change—toN(data);
		return data;
	}
	
	private static String stripAccents(String data) 
	{
	    data = Normalizer.normalize(data, Normalizer.Form.NFD);
	    data = data.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
	    return data;
	}
	
	public static String change—toN(String data) {
		data = data.replaceAll("Ò", "n");
		return data;
	}
	
	public static double parsePredictionValue(double value) {
		
		String d = decimalFormat.format(value);
		double doub = Double.parseDouble(d);
		return doub;
	}
}

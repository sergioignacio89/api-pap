package com.experta.pap.utils;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;

import com.experta.pap.model.Siniestro;

public class SiniestroUtil {

	public static List<String> retrieveData(Siniestro siniestro) {

		List<String> data = new ArrayList<>();

		data.add("\"" + siniestro.getSiniestroSeveridad() + "\"");
		data.add("\"" + siniestro.getSiniestroCausa() + "\"");
		data.add("\"" + siniestro.getSiniestroParteCuerpo() + "\"");
		data.add("\"" + siniestro.getSiniestradoDescUltimoDX() + "\"");
		data.add("\"" + siniestro.getSiniestroCircunstancia() + "\"");
		data.add("\"" + siniestro.getSiniestroFKT() + "\"");
		data.add("\"" + siniestro.getSiniestroAltaMedica() + "\"");
		data.add("\"" + siniestro.getSiniestroDiagnostico() + "\"");
		data.add("\"" + siniestro.getSiniestroCirugia() + "\"");
		data.add("\"" + siniestro.getSiniestroEstudios() + "\"");
		data.add("\"" + siniestro.getSiniestroPeriodo() + "\"");
		data.add("\"" + siniestro.getSiniestroPrestadorProvincia() + "\"");
		data.add("\"" + siniestro.getSiniestroCanalIngreso() + "\"");
		data.add("\"" + siniestro.getSiniestroCaseSML() + "\"");
		data.add("\"" + siniestro.getSiniestroCaseSupervisor() + "\"");
		data.add("\"" + siniestro.getSiniestroPrestador() + "\"");
		data.add(siniestro.getEmpresaCP());
		data.add(siniestro.getEmpresaProvincia());
		data.add(siniestro.getJuicioAbogado());
		data.add(siniestro.getJuicioEstudio());
		data.add(siniestro.getJuicioTiene());
		data.add(siniestro.getJuicioLeyInvocada());
		data.add(siniestro.getAbogadoCP());
		data.add(siniestro.getEstudioCP());
		data.add("\"" + siniestro.getSiniestradoNacionalidad() + "\"");
		data.add(siniestro.getSiniestradoCP());
		data.add("\"" + siniestro.getSiniestradoSexo() + "\"");
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
	
	private static String change—toN(String data) {
		data = data.replaceAll("Ò", "n");
		return data;
	}
}

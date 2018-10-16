package com.experta.pap.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.experta.pap.exceptions.GenericException;
import com.experta.pap.model.Siniestro;
import com.experta.pap.model.SiniestroInferido;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonMapper {

	public static List<Siniestro> convertJsonToSiniestro(List<String> listDocuments) {

		return null;
	}

	public static String convertSiniestroToJson(List<Siniestro> siniestros) throws GenericException {

		String json = "";
		ObjectMapper mapper = new ObjectMapper();
		try {
			json = mapper.writeValueAsString(siniestros);
		} catch (Exception e) {
			e.printStackTrace();
			throw new GenericException("error when converting siniestros to json");
		}
		return json;
	}

	public static List<String> getInferredPercentage(String jsonStringScoring) throws Exception {

		List<String> inferredPercentage = new ArrayList<>();

		ObjectMapper mapper = new ObjectMapper();

		JsonNode jsonNode = mapper.readTree(jsonStringScoring);
		JsonNode values = jsonNode.get("values");
		Iterator<JsonNode> iterator = values.iterator();

		while (iterator.hasNext()) {

			JsonNode node = iterator.next();

			JsonNode index0 = node.get(30);
			System.out.println(index0);
			inferredPercentage.add(Float.parseFloat(index0.toString()) * 100 + "%");
		}
		return inferredPercentage;
	}

	public static List<SiniestroInferido> convertJsonToSiniestroInferred(List<Siniestro> siniestros,
			List<String> inferredPercentage) throws GenericException, Exception {

		List<SiniestroInferido> siniestrosInferidos = new ArrayList<>();

		try {
			Iterator<Siniestro> itSiniestros = siniestros.iterator();
			Iterator<String> itInferredPerc = inferredPercentage.iterator();

			while (itSiniestros.hasNext() && itInferredPerc.hasNext()) {

				Siniestro siniestro = itSiniestros.next();
				String percentage = itInferredPerc.next();

				SiniestroInferido siniestroInferido = new SiniestroInferido(siniestro, percentage);
				siniestrosInferidos.add(siniestroInferido);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

		return siniestrosInferidos;
	}

}

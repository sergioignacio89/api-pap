package com.experta.pap.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.experta.pap.exceptions.GenericException;
import com.experta.pap.model.Predictions;
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

	public static List<Predictions> getInferredPercentage(String jsonStringScoring) throws Exception {

		List<Predictions> inferredPercentage = new ArrayList<>();

		ObjectMapper mapper = new ObjectMapper();

		JsonNode jsonNode = mapper.readTree(jsonStringScoring);
		JsonNode values = jsonNode.get("values");
		Iterator<JsonNode> iterator = values.iterator();

		while (iterator.hasNext()) {

			JsonNode node = iterator.next();

			JsonNode index0 = node.get(26);
			double percentageForNonJudgment = index0.get(0).asDouble() * 100;
			double percentageForJudgment = index0.get(1).asDouble() * 100;
			
			percentageForNonJudgment = SiniestroUtil.parsePredictionValue(percentageForNonJudgment);
			percentageForJudgment = SiniestroUtil.parsePredictionValue(percentageForJudgment);
			
			Predictions predictions = new Predictions(String.valueOf(percentageForNonJudgment), String.valueOf(percentageForJudgment));
			inferredPercentage.add(predictions);
		}
		
		return inferredPercentage;
	}

	public static List<SiniestroInferido> convertJsonToSiniestroInferred(List<Siniestro> siniestros,
			List<Predictions> inferredPercentage) throws GenericException, Exception {

		List<SiniestroInferido> siniestrosInferidos = new ArrayList<>();

		try {
			Iterator<Siniestro> itSiniestros = siniestros.iterator();
			Iterator<Predictions> itInferredPerc = inferredPercentage.iterator();

			while (itSiniestros.hasNext() && itInferredPerc.hasNext()) {

				Siniestro siniestro = itSiniestros.next();
				Predictions predictions = itInferredPerc.next();

				SiniestroInferido siniestroInferido = new SiniestroInferido(siniestro, predictions);
				siniestrosInferidos.add(siniestroInferido);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

		return siniestrosInferidos;
	}

}

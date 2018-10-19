package com.experta.pap.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.experta.pap.exceptions.GenericException;
import com.experta.pap.model.Accident;
import com.experta.pap.model.AccidentInferred;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonMapper {

	public static List<Accident> convertJsonToAccident(List<String> listDocuments) {

		return null;
	}

	public static String convertAccidentToJson(List<Accident> accidents) throws GenericException {

		String json = "";
		ObjectMapper mapper = new ObjectMapper();
		try {
			json = mapper.writeValueAsString(accidents);
		} catch (Exception e) {
			e.printStackTrace();
			throw new GenericException("error when converting accidents to json");
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

			JsonNode index0 = node.get(26);
//			double percentageForNonJudgment = index0.get(0).asDouble() * 100;
			double percentageForJudgment = index0.get(1).asDouble() * 100;
			
//			percentageForNonJudgment = SiniestroUtil.parsePredictionValue(percentageForNonJudgment);
			percentageForJudgment = AccidentUtil.parsePredictionValue(percentageForJudgment);
			
//			Predictions predictions = new Predictions(String.valueOf(percentageForNonJudgment), String.valueOf(percentageForJudgment));
//			inferredPercentage.add(predictions);
			String strPercentageForJudgment = String.valueOf(percentageForJudgment);
			inferredPercentage.add(strPercentageForJudgment);
		}
		
		return inferredPercentage;
	}

	public static List<AccidentInferred> convertJsonToAccidentInferred(List<Accident> accidents,
			List<String> inferredPercentage) throws GenericException, Exception {

		List<AccidentInferred> accidentsInferidos = new ArrayList<>();

		try {
			Iterator<Accident> itAccidents = accidents.iterator();
			Iterator<String> itInferredPerc = inferredPercentage.iterator();

			while (itAccidents.hasNext() && itInferredPerc.hasNext()) {

				Accident accident = itAccidents.next();
				String predictions = itInferredPerc.next();

				AccidentInferred accidentInferido = new AccidentInferred(accident, predictions);
				accidentsInferidos.add(accidentInferido);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

		return accidentsInferidos;
	}

}

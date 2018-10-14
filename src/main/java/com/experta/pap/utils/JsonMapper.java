package com.experta.pap.utils;

import java.util.List;

import com.experta.pap.exceptions.GenericException;
import com.experta.pap.model.Siniestro;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonMapper {

	public List<Siniestro> convertJsonToSiniestro(List<String> listDocuments) {
		
		return null;
	}

	public String convertSiniestroToJson(List<Siniestro> siniestros) throws GenericException {

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
	
}

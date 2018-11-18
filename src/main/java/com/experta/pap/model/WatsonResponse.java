package com.experta.pap.model;

/**
 * Modelo que mapea la respuesta de IBM Watson
 * 
 * @author Sergio Massa
 *
 */
public class WatsonResponse {

	private int responseCode;
	private String response;

	public WatsonResponse() {
	}
	
	public WatsonResponse(int responseCode, String response) {
		this.responseCode = responseCode;
		this.response = response;
	}

	public int getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	@Override
	public String toString() {
		return "WatsonResponse [responseCode=" + responseCode + ", response=" + response + "]";
	}

}

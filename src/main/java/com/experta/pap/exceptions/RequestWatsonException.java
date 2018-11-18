package com.experta.pap.exceptions;

/**
 * Excepciones sobre los valores de siniestros enviados a IBM Watson
 * 
 * @author Sergio Massa
 */
public class RequestWatsonException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RequestWatsonException(String message) {
		super(message);
	}
}

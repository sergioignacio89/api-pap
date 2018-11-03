package com.experta.pap.exceptions;

/**
 * Excepciones emitidas cuando hubo un error de parseo
 * 
 * @author Sergio Massa
 */
public class ParseException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ParseException(String message) {
		super(message);
	}
}

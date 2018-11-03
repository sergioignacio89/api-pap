package com.experta.pap.exceptions;

/**
 * Excepciones para capa de persistencia
 * 
 * @author Sergio Massa
 */
public class ConnectionException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ConnectionException(String message) {
		super(message);
	}
}

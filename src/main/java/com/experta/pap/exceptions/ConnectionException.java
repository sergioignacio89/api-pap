package com.experta.pap.exceptions;

/**
 * Exception de tipo conexion a IBM Watson
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

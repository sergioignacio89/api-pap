package com.experta.pap.exceptions;

/**
 * Excepcion que abarca tipos de datos ingresados erroneos
 * 
 * @author Sergio Massa
 */
public class InputTypeException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InputTypeException(String message) {
		super(message);
	}
}

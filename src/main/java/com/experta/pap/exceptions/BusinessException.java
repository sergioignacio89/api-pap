package com.experta.pap.exceptions;

/**
 * Excepciones para capa de negocios
 * 
 * @author Sergio Massa
 */
public class BusinessException extends Exception {

	private static final long serialVersionUID = 1L;

	public BusinessException(String message) {
		super(message);
	}
}

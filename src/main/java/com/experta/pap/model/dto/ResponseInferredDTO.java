package com.experta.pap.model.dto;

import java.util.List;

import com.experta.pap.model.AccidentInferred;

/**
 * DTO de respuesta con listado de siniestros y sus predicciones
 * 
 * @author Sergio Massa
 * @see AccidentInferred
 * @see
 */
public class ResponseInferredDTO {

	List<AccidentInferred> _accidents;
	String _errorMessage;

	public List<AccidentInferred> get_accidents() {
		return _accidents;
	}

	public void set_accidents(List<AccidentInferred> _accidents) {
		this._accidents = _accidents;
	}

	public String get_errorMessage() {
		return _errorMessage;
	}

	public void set_errorMessage(String _errorMessage) {
		this._errorMessage = _errorMessage;
	}

}

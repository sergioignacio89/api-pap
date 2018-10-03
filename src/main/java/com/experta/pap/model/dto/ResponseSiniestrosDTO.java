package com.experta.pap.model.dto;

import java.util.List;

import com.experta.pap.model.Siniestro;

public class ResponseSiniestrosDTO {

	List<Siniestro> _siniestros;
	String _errorMessage;
	
	public List<Siniestro> get_siniestros() {	return _siniestros;	}
	public String get_errorMessage() {	return _errorMessage;	}

	public void set_siniestros(List<Siniestro> _siniestros) {	this._siniestros = _siniestros;	}
	public void set_errorMessage(String _errorMessage) {	this._errorMessage = _errorMessage;	}
	
}

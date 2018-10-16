package com.experta.pap.model.dto;

import java.util.List;

import com.experta.pap.model.SiniestroInferido;

public class ResponseSiniestrosDTO {

	List<SiniestroInferido> _siniestros;
	String _errorMessage;
	
	public List<SiniestroInferido> get_siniestros() {	return _siniestros;	}
	public String get_errorMessage() {	return _errorMessage;	}

	public void set_siniestros(List<SiniestroInferido> _siniestros) {	this._siniestros = _siniestros;	}
	public void set_errorMessage(String _errorMessage) {	this._errorMessage = _errorMessage;	}
	
}

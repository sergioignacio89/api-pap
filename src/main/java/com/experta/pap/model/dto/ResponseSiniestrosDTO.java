package com.experta.pap.model.dto;

import java.util.List;

import com.experta.pap.model.Siniestro;

public class ResponseSiniestrosDTO {

	List<Siniestro> siniestros;
	String errorMessage;

	public List<Siniestro> getSiniestros() {	return siniestros;	}
	public String getErrorMessage() {	return errorMessage;	}

	public void setSiniestros(List<Siniestro> siniestros) {	this.siniestros = siniestros;	}
	public void setErrorMessage(String errorMessage) {	this.errorMessage = errorMessage;	}
	
}

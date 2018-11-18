package com.experta.pap.model.dto;

import java.util.List;

import com.experta.pap.model.AccidentInferred;
import com.experta.pap.model.RangeConfiguration;
import com.experta.pap.model.WrapperRangeConfiguration;

/**
 * DTO de respuesta con listado de siniestros y sus predicciones
 * 
 * @author Sergio Massa
 * @see AccidentInferred
 * @see WrapperRangeConfiguration
 * @see RangeConfiguration
 */
public class ResponseInferredDTO {

	List<AccidentInferred> accidents;
	WrapperRangeConfiguration ranges;
	String errorMessage;
	String friendlyError;
	
	public List<AccidentInferred> getAccidents() {
		return accidents;
	}

	public void setAccidents(List<AccidentInferred> accidents) {
		this.accidents = accidents;
	}

	public WrapperRangeConfiguration getRanges() {
		return ranges;
	}

	public void setRanges(WrapperRangeConfiguration ranges) {
		this.ranges = ranges;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getFriendlyError() {
		return friendlyError;
	}

	public void setFriendlyError(String friendlyError) {
		this.friendlyError = friendlyError;
	}

}

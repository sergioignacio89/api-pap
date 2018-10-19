package com.experta.pap.model;

public class AccidentInferred {

	private Accident accident;
	private String inferredValue;
	
	public AccidentInferred(Accident accident, String inferredValue) {
		this.accident = accident;
		this.inferredValue = inferredValue;
	}

	public Accident getAccident() {
		return accident;
	}

	public void setAccident(Accident accident) {
		this.accident = accident;
	}

	public String getInferredValue() {
		return inferredValue;
	}

	public void setInferredValue(String inferredValue) {
		this.inferredValue = inferredValue;
	}

	@Override
	public String toString() {
		return "AccidentInferred [accident=" + accident + ", inferredValue=" + inferredValue + "]";
	}

}

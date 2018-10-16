package com.experta.pap.model;

public class SiniestroInferido {

	private Siniestro siniestro;
	private String inferredValue;
	
	public SiniestroInferido(Siniestro siniestro, String inferredValue) {
		this.siniestro = siniestro;
		this.inferredValue = inferredValue;
	}

	public Siniestro getSiniestro() {
		return siniestro;
	}

	public void setSiniestro(Siniestro siniestro) {
		this.siniestro = siniestro;
	}

	public String getInferredValue() {
		return inferredValue;
	}

	public void setInferredValue(String inferredValue) {
		this.inferredValue = inferredValue;
	}

	@Override
	public String toString() {
		return "SiniestroInferido [siniestro=" + siniestro + ", inferredValue=" + inferredValue + "]";
	}

}

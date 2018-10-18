package com.experta.pap.model;

public class SiniestroInferido {

	private Siniestro siniestro;
	private Predictions predictions;
	
	public SiniestroInferido(Siniestro siniestro, Predictions predictions) {
		this.siniestro = siniestro;
		this.predictions = predictions;
	}

	public Siniestro getSiniestro() {
		return siniestro;
	}

	public void setSiniestro(Siniestro siniestro) {
		this.siniestro = siniestro;
	}

	public Predictions getPredictions() {
		return predictions;
	}

	public void setPredictions(Predictions predictions) {
		this.predictions = predictions;
	}

	@Override
	public String toString() {
		return "SiniestroInferido [siniestro=" + siniestro + ", predictions=" + predictions + "]";
	}

}

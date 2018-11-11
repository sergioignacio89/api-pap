package com.experta.pap.model;

/**
 * Modelo que mapea la configuracion de las criticidades de los siniestros
 * 
 * @author Sergio Massa
 *
 */
public class RangeConfiguration {

	private String caption;
	private String min;
	private String max;

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public String getMin() {
		return min;
	}

	public void setMin(String min) {
		this.min = min;
	}

	public String getMax() {
		return max;
	}

	public void setMax(String max) {
		this.max = max;
	}

	@Override
	public String toString() {
		return "Range [caption=" + caption + ", min=" + min + ", max=" + max + "]";
	}

}

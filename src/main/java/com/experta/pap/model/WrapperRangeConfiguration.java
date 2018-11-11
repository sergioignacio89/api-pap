package com.experta.pap.model;

public class WrapperRangeConfiguration {

	private RangeConfiguration low;
	private RangeConfiguration middle;
	private RangeConfiguration critical;

	public RangeConfiguration getLow() {
		return low;
	}

	public void setLow(RangeConfiguration low) {
		this.low = low;
	}

	public RangeConfiguration getMiddle() {
		return middle;
	}

	public void setMiddle(RangeConfiguration middle) {
		this.middle = middle;
	}

	public RangeConfiguration getCritical() {
		return critical;
	}

	public void setCritical(RangeConfiguration critical) {
		this.critical = critical;
	}

}

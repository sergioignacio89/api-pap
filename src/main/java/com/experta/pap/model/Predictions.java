package com.experta.pap.model;

public class Predictions {

	// 0
	private String percentageForNonJudgment;
	// 1
	private String percentageForJudgment;

	public Predictions(String percentageForNonJudgment, String percentageForJudgment) {
		this.percentageForNonJudgment = percentageForNonJudgment;
		this.percentageForJudgment = percentageForJudgment;
	}

	public String getPercentageForNonJudgment() {
		return percentageForNonJudgment;
	}

	public void setPercentageForNonJudgment(String percentageForNonJudgment) {
		this.percentageForNonJudgment = percentageForNonJudgment;
	}

	public String getPercentageForJudgment() {
		return percentageForJudgment;
	}

	public void setPercentageForJudgment(String percentageForJudgment) {
		this.percentageForJudgment = percentageForJudgment;
	}

	@Override
	public String toString() {
		return "Predictions [percentageForNonJudgment=" + percentageForNonJudgment + ", percentageForJudgment="
				+ percentageForJudgment + "]";
	}

}

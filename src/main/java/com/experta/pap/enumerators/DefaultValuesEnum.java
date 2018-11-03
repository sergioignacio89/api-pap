package com.experta.pap.enumerators;

public enum DefaultValuesEnum {

	number("0"), string(""), date("null");

	private String value;

	DefaultValuesEnum(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}

package com.experta.pap.enumerators;

public enum DefaultValuesEnum {

	number("0"), string(""), date("1901-01-01");

	private String value;

	DefaultValuesEnum(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}

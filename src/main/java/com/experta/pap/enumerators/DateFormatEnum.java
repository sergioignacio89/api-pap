package com.experta.pap.enumerators;

public enum DateFormatEnum {

	dd_MMM_yyyy("dd-MMM-yyyy"), dd_MM_yyyy_a("dd/MM/yyyy"),  dd_MM_yyyy_b("dd-MM-yyyy"), yyyy_MM_dd("yyyy-MM-dd");
	
	private String format;

	DateFormatEnum(String format) {
		this.format = format;
	}

	public String getFormat() {
		return format;
	}

}

package com.experta.pap.test;

import com.experta.pap.utils.StringUtil;

public class StringUtilTest {

	public static void main(String[] args) {

		String date = "1/1/1968  00:00:00";

		date = StringUtil.parseToDate(date);
		System.out.println(date);
	}

}

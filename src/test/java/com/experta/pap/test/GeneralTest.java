package com.experta.pap.test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GeneralTest {

	public static void main(String[] args) throws Exception {

		String date = "11/10/1989";
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date d = sdf.parse(date);

		long time = d.getTime();
		java.sql.Timestamp timestamp = new java.sql.Timestamp(time);

		System.out.println(timestamp);
		System.out.println(timestamp.toString());


	}

}

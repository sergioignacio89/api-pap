package com.experta.pap.test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GeneralTest {

	public static void main(String[] args) throws Exception {

		Date d = new Date();
		System.out.println(d);
				
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyy-MM-dd");
		System.out.println(sdf1.format(d));

	}

}

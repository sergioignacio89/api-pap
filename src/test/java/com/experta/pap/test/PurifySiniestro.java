package com.experta.pap.test;

import com.experta.pap.utils.AccidentUtil;

public class PurifySiniestro {

	public static void main(String[] args) {

		String data = "Esto es una � o � y t�ldes en se�or�a";
		System.out.println(data);
		
		data = AccidentUtil.purifyAccident(data);
		System.out.println(data);
		
		
	}

}

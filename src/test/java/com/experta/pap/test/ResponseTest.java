package com.experta.pap.test;

import java.io.File;
import java.io.FileInputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.experta.pap.model.Accident;
import com.experta.pap.model.AccidentInferred;
import com.experta.pap.utils.ExcelUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ResponseTest {

	public static void main(String[] args) throws Exception {

		File file = new File("C:\\Users\\Sergio\\Desktop\\prueba.xlsx");
		FileInputStream excelFile = new FileInputStream(file);

		ExcelUtil excel = new ExcelUtil(true);
		List<Accident> accidents = excel.fromExcelToAccidents(excelFile);

		List<AccidentInferred> inferred = new ArrayList<>();
		
		DecimalFormat decimalFormat = new DecimalFormat("##.##");
		Random r = new Random(10);
		for(Accident accident : accidents) {
			

			String inferredValue = decimalFormat.format(r.nextFloat() * 100);
			System.out.println(inferredValue);
			AccidentInferred ai = new AccidentInferred(accident, inferredValue);
			
			inferred.add(ai);
		}
		
		ObjectMapper mapper = new ObjectMapper();
		String result = mapper.writeValueAsString(inferred);
		
		System.out.println(result);
		
	}

}
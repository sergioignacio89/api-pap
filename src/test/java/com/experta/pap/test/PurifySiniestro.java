package com.experta.pap.test;

import java.util.ArrayList;
import java.util.List;

import com.experta.pap.model.Accident;
import com.experta.pap.service.impl.FileServiceImpl;
import com.experta.pap.utils.AccidentUtil;
import com.experta.pap.utils.StringUtil;

public class PurifySiniestro {

	public static void main(String[] args) throws Exception {

		System.setProperty("pap.location.files.temp", "C:\\Users\\Sergio\\Documents\\ORT-Proyecto-PAP\\otros");
		System.setProperty("app-resources",
				"C:\\Users\\Sergio\\Documents\\ORT-Proyecto-PAP\\api-pap\\src\\main\\resources\\app-resources.properties");

		FileServiceImpl service = new FileServiceImpl();

		List<Accident> accidents = service.readFile("DEMO.xlsx");

		System.out.println("INPUT - MAPEO");
		for(Accident accident : accidents) {
			System.out.println(accident);
		}
		
		List<List<String>> data = new ArrayList<>();

		for (Accident s : accidents) {
			data.add(AccidentUtil.retrieveData(s));
		}

		String jsonStringScoring = StringUtil.purifyAccident(data.toString());
		
		System.out.println();
		System.out.println("OUTPUT - MAPEO PARA WATSON");
		System.out.println(jsonStringScoring);

	}

}

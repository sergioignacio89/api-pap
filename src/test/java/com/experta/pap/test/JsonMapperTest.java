package com.experta.pap.test;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonMapperTest {

	public static void main(String[] args) throws Exception {

//		String response = "{   \"fields\": [     \"Siniestro:Severidad\",     \"Siniestro:Causa\",     \"Siniestro:ParteCuerpo\",     \"DESC_ULTIMO_DX\",     \"Siniestro:Circunstancia\",     \"Siniestro:FKT?\",     \"Siniestro:AltaMedica?\",     \"Siniestro:Diagnostivo?\",     \"Siniestro:Cirugia?\",     \"Siniestro:Estudios?\",     \"Siniestro:Periodo\",     \"Siniestrop:PrestadorProvincia\",     \"Siniesto:CanalIngreso\",     \"Siniestro:CaseSML\",     \"Siniestro:CAseSupervisor\",     \"Siniestro:Prestador\",     \"Empresa:CP\",     \"Empresa:Provincia\",     \"Juicio:Abogado\",     \"Juicio:Estudio\",     \"Juicio:LeyInvocada\",     \"ABOGADO_CP\",     \"ESTUDIO_CP\",     \"SINIESTADO_NACIONALIDAD\",     \"SINIESTRADO_CP\",     \"SINIESTRADO_SEXO\",     \"SINIESTRADO_Fh_NACIMIENTO\",     \"POLIZA_Tipo_Poliza\",     \"LOCALIDAD_POLIZA\",     \"features\",     \"prediction\"   ],   \"values\": [     [       \"Leve\",       \"Injuria Punzo-Cortante O Contusa Involuntaria\",       \"Dedos De Las Manos\",       \"Herida Punzante Con Riesgo Biologico En Estudio\",       \"Realizando Tarea Habitual\",       \"NO\",       \"SI\",       \"1\",       \"NO\",       \"NO\",       \"201601\",       \"Capital Federal\",       \"DA\",       \"Dllano\",       \"Alvarez_sup\",       \"Cemic\",       1425,       0,       0,       0,       0,       0,       0,       0,       \"0\",       1095,       \"27\",       \"1/1/01\",       \"RG\",       [         102,         [           0,           6         ],         [           2.002479849204711,           3.261260675158529         ]       ],       0.15658230108621785     ]   ] }";
		String response = "{   \"fields\": [     \"Siniestro:Severidad\",     \"Siniestro:Causa\",     \"Siniestro:ParteCuerpo\",     \"DESC_ULTIMO_DX\",     \"Siniestro:Circunstancia\",     \"Siniestro:FKT?\",     \"Siniestro:AltaMedica?\",     \"Siniestro:Diagnostivo?\",     \"Siniestro:Cirugia?\",     \"Siniestro:Estudios?\",     \"Siniestro:Periodo\",     \"Siniestrop:PrestadorProvincia\",     \"Siniesto:CanalIngreso\",     \"Siniestro:CaseSML\",     \"Siniestro:CAseSupervisor\",     \"Siniestro:Prestador\",     \"Empresa:CP\",     \"Empresa:Provincia\",     \"Juicio:Abogado\",     \"Juicio:Estudio\",     \"Juicio:LeyInvocada\",     \"ABOGADO_CP\",     \"ESTUDIO_CP\",     \"SINIESTADO_NACIONALIDAD\",     \"SINIESTRADO_CP\",     \"SINIESTRADO_SEXO\",     \"SINIESTRADO_Fh_NACIMIENTO\",     \"POLIZA_Tipo_Poliza\",     \"LOCALIDAD_POLIZA\",     \"features\",     \"prediction\"   ],   \"values\": [     [       \"Leve\",       \"Injuria Punzo-Cortante O Contusa Involuntaria\",       \"Dedos De Las Manos\",       \"Herida Punzante Con Riesgo Biologico En Estudio\",       \"Realizando Tarea Habitual\",       \"NO\",       \"SI\",       \"1\",       \"NO\",       \"NO\",       \"201601\",       \"Capital Federal\",       \"DA\",       \"Dllano\",       \"Alvarez_sup\",       \"Cemic\",       1425,       0,       0,       0,       0,       0,       0,       0,       \"0\",       1095,       \"27\",       \"1/1/01\",       \"RG\",       [         102,         [           0,           6         ],         [           2.002479849204711,           3.261260675158529         ]       ],       0.15658230108621785     ],     [       \"Moderado\",       \"Mordeduras Por Animales\",       \"Miembro Inferior, Ubicaciones Multiples (Elaborar Informe Especial)\",       \"Mordedura\",       \"Realizando Tarea Habitual\",       \"NO\",       \"SI\",       \"1\",       \"NO\",       \"NO\",       \"201601\",       \"Santa Fe\",       \"DE\",       \"Aruiz\",       \"Flamma\",       \"Mapaci Laboral S.A.\",       2000,       12,       0,       0,       0,       0,       0,       0,       \"12\",       2000,       \"27\",       \"1/1/01\",       \"RG\",       [         102,         [           1,           36         ],         [           2.3034835275950365,           13.19731843217679         ]       ],       0.4795222280634125     ]   ] }";
		ObjectMapper mapper = new ObjectMapper();
		

		JsonNode jsonNode = mapper.readTree(response);
		JsonNode values = jsonNode.get("values");
		
		JsonNode index0 = values.get(0).get(30);
		System.out.println(index0);
		System.out.println(Float.parseFloat(index0.toString())*100 + "%");
		
		JsonNode index1 = values.get(1).get(30);
		System.out.println(index1);
		System.out.println(Float.parseFloat(index1.toString())*100 + "%");

		
		
	}

}

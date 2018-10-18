package com.experta.pap.test;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonMapperTest {

	public static void main(String[] args) throws Exception {

		String response = "{  \"fields\": [\"Siniestro:Severidad\", \"Siniestro:Causa\", \"Siniestro:ParteCuerpo\", \"DESC_ULTIMO_DX\", \"Siniestro:Circunstancia\", \"Siniestro:FKT?\", \"Siniestro:AltaMedica?\", \"Siniestro:Diagnostivo?\", \"Siniestro:Cirugia?\", \"Siniestro:Estudios?\", \"Siniestro:Periodo\", \"Siniestrop:PrestadorProvincia\", \"Siniesto:CanalIngreso\", \"Siniestro:CaseSML\", \"Siniestro:CAseSupervisor\", \"Siniestro:Prestador\", \"Empresa:CP\", \"Empresa:Provincia\", \"SINIESTADO_NACIONALIDAD\", \"SINIESTRADO_CP\", \"SINIESTRADO_SEXO\", \"SINIESTRADO_Fh_NACIMIENTO\", \"POLIZA_Tipo_Poliza\", \"LOCALIDAD_POLIZA\", \"features\", \"rawPrediction\", \"probability\", \"prediction\", \"nodeADP_class\", \"nodeADP_classes\"],  \"values\": [[\"Leve\", \"Contacto Con Fuego\", \"Pierna\", \"Quemadura Cadera Y Minf 1er Grado Exc Tob/Pie Sup Hasta 10%\", \"Realizando Tarea Habitual\", \"NO\", \"SI\", 1, \"NO\", \"NO\", 201601, \"Capital Federal\", \"DA\", \"Lsalvatierra\", \"Dgonza_sup\", \"Cemic\", 1425, 0, 12, 0, 27, \"1901-01-01\", \"RG\", \"Capital Federal\", [310, [0, 43, 61, 142, 144, 146, 147, 149, 152, 177, 203, 287, 305, 306, 307], [2.0007551548550677, 34.01751461516236, 4.133083674733494, 2.0095625574059133, 10.601186951630563, 58.90307017252564, 3.703861563757829, 2.0228050421695656, 2.4512210945597315, 2.495948470076121, 6.805956106371604, 2.675799971747282, 0.5860851916720746, 5.984009859987671, 5.449952382004044]], [2.1426980879856004, -2.1426980879856004], [0.8949844667855599, 0.10501553321444015], 0.0, 0.0, [0.0, 1.0]]]}";
		ObjectMapper mapper = new ObjectMapper();
		

		JsonNode jsonNode = mapper.readTree(response);
		JsonNode values = jsonNode.get("values");
		
		JsonNode index0 = values.get(0).get(26);
		double possitive = index0.get(0).asDouble();
		double negative = index0.get(1).asDouble();
		System.out.println(index0);
		System.out.println(possitive);
		System.out.println(possitive * 100 + "%");
		
		System.out.println(negative);
		System.out.println(negative * 100 + "%");
		
//		JsonNode index1 = values.get(1).get(30);
//		System.out.println(index1);
//		System.out.println(Float.parseFloat(index1.toString())*100 + "%");

		
		
	}

}

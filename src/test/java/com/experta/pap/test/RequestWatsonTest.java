package com.experta.pap.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.experta.pap.model.Accident;
import com.experta.pap.utils.AccidentUtil;

public class RequestWatsonTest {

	private static final String WML_SERVICE_CREDENTIALS_URL = "https://us-south.ml.cloud.ibm.com";
	private static final String WML_SERVICE_CREDENTIALS_USERNAME = "7b569635-bd38-437b-a0e1-03a7c406b6ce";
	private static final String WML_SERVICE_CREDENTIALS_PASSWORD = "8a925657-53a3-4d01-ba6c-9f56704c6979";

	public static void main(String[] args) throws Exception {

		String siniestro = "Leve;Contacto Con Fuego;Pierna;Quemadura Cadera Y Minf 1er Grado Exc Tob/Pie Sup Hasta 10%;Realizando Tarea Habitual;NO;SI;1;NO;NO;201601;Capital Federal;DA;Lsalvatierra;Dgonza_sup;Cemic;1425;0;12;0;27;1/1/1901;RG;Capital Federal;";
		Accident s = new Accident(siniestro);

		List<List<String>> data = new ArrayList<>();
		data.add(AccidentUtil.retrieveData(s));
		String values = AccidentUtil.purifyAccident(data.toString());

		doPrediction(values);

	}

	public static void doPrediction(String values) throws Exception {

		Map<String, String> wml_credentials = new HashMap<String, String>();
		wml_credentials.put("url", WML_SERVICE_CREDENTIALS_URL);
		wml_credentials.put("username", WML_SERVICE_CREDENTIALS_USERNAME);
		wml_credentials.put("password", WML_SERVICE_CREDENTIALS_PASSWORD);

		String wml_auth_header = "Basic " + Base64.getEncoder()
				.encodeToString((wml_credentials.get("username") + ":" + wml_credentials.get("password"))
						.getBytes(StandardCharsets.UTF_8));
		String wml_url = wml_credentials.get("url") + "/v3/identity/token";
		HttpURLConnection tokenConnection = null;
		HttpURLConnection scoringConnection = null;
		BufferedReader tokenBuffer = null;
		BufferedReader scoringBuffer = null;
		try {
			// Getting WML token
			URL tokenUrl = new URL(wml_url);
			tokenConnection = (HttpURLConnection) tokenUrl.openConnection();
			tokenConnection.setDoInput(true);
			tokenConnection.setDoOutput(true);
			tokenConnection.setRequestMethod("GET");
			tokenConnection.setRequestProperty("Authorization", wml_auth_header);
			tokenBuffer = new BufferedReader(new InputStreamReader(tokenConnection.getInputStream()));
			StringBuffer jsonString = new StringBuffer();
			String line;
			while ((line = tokenBuffer.readLine()) != null) {
				jsonString.append(line);
			}
			// Scoring request
			URL scoringUrl = new URL(
					"https://us-south.ml.cloud.ibm.com/v3/wml_instances/f3c26469-f452-4784-88eb-ca5131b6c763/deployments/8b95db81-b772-49d0-8fb9-938f401e08af/online");
			String wml_token = "Bearer " + jsonString.toString().replace("\"", "").replace("}", "").split(":")[1];
			scoringConnection = (HttpURLConnection) scoringUrl.openConnection();
			scoringConnection.setDoInput(true);
			scoringConnection.setDoOutput(true);
			scoringConnection.setRequestMethod("POST");
			scoringConnection.setRequestProperty("Accept", "application/json");
			scoringConnection.setRequestProperty("Authorization", wml_token);
			scoringConnection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
			OutputStreamWriter writer = new OutputStreamWriter(scoringConnection.getOutputStream(), "UTF-8");

			// NOTE: manually define and pass the array(s) of values to be scored in the
			// next line
			String payload = "{\"fields\": [\"Siniestro:Severidad\", \"Siniestro:Causa\", \"Siniestro:ParteCuerpo\", \"DESC_ULTIMO_DX\", \"Siniestro:Circunstancia\", \"Siniestro:FKT?\", \"Siniestro:AltaMedica?\", \"Siniestro:Diagnostivo?\", \"Siniestro:Cirugia?\", \"Siniestro:Estudios?\", \"Siniestro:Periodo\", \"Siniestrop:PrestadorProvincia\", \"Siniesto:CanalIngreso\", \"Siniestro:CaseSML\", \"Siniestro:CAseSupervisor\", \"Siniestro:Prestador\", \"Empresa:CP\", \"Empresa:Provincia\", \"SINIESTADO_NACIONALIDAD\", \"SINIESTRADO_CP\", \"SINIESTRADO_SEXO\", \"SINIESTRADO_Fh_NACIMIENTO\", \"POLIZA_Tipo_Poliza\", \"LOCALIDAD_POLIZA\"], \"values\": " + values + "}";
			writer.write(payload);
			writer.close();

			scoringBuffer = new BufferedReader(new InputStreamReader(scoringConnection.getInputStream()));
			StringBuffer jsonStringScoring = new StringBuffer();
			String lineScoring;
			while ((lineScoring = scoringBuffer.readLine()) != null) {
				jsonStringScoring.append(lineScoring);
			}
			System.out.println(jsonStringScoring);
		} catch (IOException e) {
			System.out.println("The URL is not valid.");
			System.out.println(e.getMessage());
		} finally {
			if (tokenConnection != null) {
				tokenConnection.disconnect();
			}
			if (tokenBuffer != null) {
				tokenBuffer.close();
			}
			if (scoringConnection != null) {
				scoringConnection.disconnect();
			}
			if (scoringBuffer != null) {
				scoringBuffer.close();
			}
		}

	}

}

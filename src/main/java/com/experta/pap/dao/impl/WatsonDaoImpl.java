package com.experta.pap.dao.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Properties;

import org.springframework.stereotype.Repository;

import com.experta.pap.dao.IWatsonDao;
import com.experta.pap.exceptions.ConnectionException;
import com.experta.pap.exceptions.GenericException;
import com.experta.pap.utils.Resources;

@Repository
public class WatsonDaoImpl implements IWatsonDao {

	public String predictSiniestros(String values) throws GenericException, ConnectionException {

		StringBuffer jsonStringScoring = new StringBuffer();
		HttpURLConnection tokenConnection = null;
		HttpURLConnection scoringConnection = null;
		BufferedReader tokenBuffer = null;
		BufferedReader scoringBuffer = null;

		String urlCred = "";
		String usernameCred = "";
		String passwordCred = "";
		try {
			try {
				Properties props = Resources.getProperties();
				urlCred = String.valueOf(props.get("pap.wmlservice.credentials.url"));
				usernameCred = String.valueOf(props.getProperty("pap.wmlservice.credentials.username"));
				passwordCred = String.valueOf(props.getProperty("pap.wmlservice.credentials.password"));
			} catch (Exception e) {
				e.printStackTrace();
				throw new GenericException("error loading properties file");
			}

			String wml_auth_header = "Basic " + Base64.getEncoder()
					.encodeToString((usernameCred + ":" + passwordCred).getBytes(StandardCharsets.UTF_8));
			String wml_url = urlCred + "/v3/identity/token";

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

			URL scoringUrl = new URL(
					"https://us-south.ml.cloud.ibm.com/v3/wml_instances/aaa7b437-b556-49a2-bd11-d6e71acfb1fa/deployments/11e208c7-c132-42dc-8940-018627f1fad2/online");
			String wmlToken = "Bearer " + jsonString.toString().replace("\"", "").replace("}", "").split(":")[1];

			scoringConnection = (HttpURLConnection) scoringUrl.openConnection();
			scoringConnection.setDoInput(true);
			scoringConnection.setDoOutput(true);
			scoringConnection.setRequestMethod("POST");
			scoringConnection.setRequestProperty("Accept", "application/json");
			scoringConnection.setRequestProperty("Authorization", wmlToken);
			scoringConnection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");

			OutputStreamWriter writer = new OutputStreamWriter(scoringConnection.getOutputStream(), "UTF-8");
			String payload = "{\"fields\": [\"Siniestro:Severidad\", \"Siniestro:Causa\", \"Siniestro:ParteCuerpo\", \"DESC_ULTIMO_DX\", \"Siniestro:Circunstancia\", \"Siniestro:FKT?\", \"Siniestro:AltaMedica?\", \"Siniestro:Diagnostivo?\", \"Siniestro:Cirugia?\", \"Siniestro:Estudios?\", \"Siniestro:Periodo\", \"Siniestrop:PrestadorProvincia\", \"Siniesto:CanalIngreso\", \"Siniestro:CaseSML\", \"Siniestro:CAseSupervisor\", \"Siniestro:Prestador\", \"Empresa:CP\", \"Empresa:Provincia\", \"Juicio:Abogado\", \"Juicio:Estudio\", \"Juicio:LeyInvocada\", \"ABOGADO_CP\", \"ESTUDIO_CP\", \"SINIESTADO_NACIONALIDAD\", \"SINIESTRADO_CP\", \"SINIESTRADO_SEXO\", \"SINIESTRADO_Fh_NACIMIENTO\", \"POLIZA_Tipo_Poliza\", \"LOCALIDAD_POLIZA\"], \"values\": [{replace}]}";
			String valuesTmp = payload.replace("[{replace}]", values);
			
			writer.write(valuesTmp);
			writer.close();

			scoringBuffer = new BufferedReader(new InputStreamReader(scoringConnection.getInputStream()));
			String lineScoring;

			while ((lineScoring = scoringBuffer.readLine()) != null) {
				jsonStringScoring.append(lineScoring);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new ConnectionException("error connection with watson");
		} finally {
			try {
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
			} catch (Exception e) {
			}
		}
		return jsonStringScoring.toString();
	}
}

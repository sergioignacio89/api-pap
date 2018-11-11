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

/**
 * Capa de persistencia.
 * 
 * <p>
 * Realiza la comunicacion con IBM Watson para prediccion de siniestros.
 * </p>
 * 
 * @author Sergio Massa
 *
 */
@Repository
public class WatsonDaoImpl implements IWatsonDao {

	/**
	 * Metodo que realiza la conexion con IBM Watson para predecir siniestros.
	 * 
	 * @author Sergio Massa
	 * 
	 * @param values de siniestros en formato json
	 * @return  respuesta obtenida por IBM Watson
	 * @throws GenericException
	 * @throws ConnectionException
	 * 
	 */
	public String predictAccidents(String values) throws GenericException, ConnectionException {

		StringBuffer jsonStringScoring = new StringBuffer();
		HttpURLConnection tokenConnection = null;
		HttpURLConnection scoringConnection = null;
		BufferedReader tokenBuffer = null;
		BufferedReader scoringBuffer = null;

		String scoringEndpoint = "";
		String urlCred = "";
		String usernameCred = "";
		String passwordCred = "";
		try {
			try {
				Properties props = Resources.getProperties();
				scoringEndpoint = String.valueOf(props.get("pap.wmlservice.credentials.scoringEndpoint"));
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

			URL scoringUrl = new URL(scoringEndpoint);
			String wmlToken = "Bearer " + jsonString.toString().replace("\"", "").replace("}", "").split(":")[1];

			scoringConnection = (HttpURLConnection) scoringUrl.openConnection();
			scoringConnection.setDoInput(true);
			scoringConnection.setDoOutput(true);
			scoringConnection.setRequestMethod("POST");
			scoringConnection.setRequestProperty("Accept", "application/json");
			scoringConnection.setRequestProperty("Authorization", wmlToken);
			scoringConnection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");

			OutputStreamWriter writer = new OutputStreamWriter(scoringConnection.getOutputStream(), "UTF-8");
			String payload = "{\"fields\": [\"Siniestro:Severidad\", \"Siniestro:Causa\", \"Siniestro:ParteCuerpo\", \"DESC_ULTIMO_DX\", \"Siniestro:FKT?\", \"Siniestro:AltaMedica?\", \"Siniestro:Diagnostivo?\", \"Siniestro:Cirugia?\", \"Siniestro:Estudios?\", \"Siniestro:Periodo\", \"Siniestrop:PrestadorProvincia\", \"Siniesto:CanalIngreso\", \"Siniestro:CaseSML\", \"Siniestro:CAseSupervisor\", \"Siniestro:Prestador\", \"Empresa:CP\", \"Empresa:Provincia\", \"SINIESTADO_NACIONALIDAD\", \"SINIESTRADO_CP\", \"SINIESTRADO_SEXO\", \"SINIESTRADO_Fh_NACIMIENTO\", \"POLIZA_Tipo_Poliza\", \"LOCALIDAD_POLIZA\"], \"values\": [{replace}]}";
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

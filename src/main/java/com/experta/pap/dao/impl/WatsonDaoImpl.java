package com.experta.pap.dao.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Properties;

import org.springframework.stereotype.Repository;

import com.experta.pap.dao.IWatsonDao;
import com.experta.pap.exceptions.ConnectionException;
import com.experta.pap.exceptions.GenericException;
import com.experta.pap.utils.Resources;

@Repository
public class WatsonDaoImpl implements IWatsonDao {

	public List<String> predictSiniestros(String jsonSiniestros) throws GenericException, ConnectionException {

		List<String> response = new ArrayList<>();
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
					"https://us-south.ml.cloud.ibm.com/v3/wml_instances/0c13a883-b652-4c52-ba24-238cf02679cb/deployments/1e26f7f5-cec4-4d08-886e-3275902b786b/online");
			String wmlToken = "Bearer " + jsonString.toString().replace("\"", "").replace("}", "").split(":")[1];

			scoringConnection = (HttpURLConnection) scoringUrl.openConnection();
			scoringConnection.setDoInput(true);
			scoringConnection.setDoOutput(true);
			scoringConnection.setRequestMethod("POST");
			scoringConnection.setRequestProperty("Accept", "application/json");
			scoringConnection.setRequestProperty("Authorization", wmlToken);
			scoringConnection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");

			OutputStreamWriter writer = new OutputStreamWriter(scoringConnection.getOutputStream(), "UTF-8");
			writer.write(jsonSiniestros);
			writer.close();

			scoringBuffer = new BufferedReader(new InputStreamReader(scoringConnection.getInputStream()));
			StringBuffer jsonStringScoring = new StringBuffer();
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
		return response;
	}
}

package com.experta.pap.dao;

import java.util.List;

import com.experta.pap.exceptions.ConnectionException;
import com.experta.pap.exceptions.GenericException;

public interface IWatsonDao {

	public List<String> predictSiniestros(String jsonSiniestros) throws GenericException, ConnectionException;
	
}

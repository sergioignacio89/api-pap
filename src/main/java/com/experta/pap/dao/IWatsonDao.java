package com.experta.pap.dao;

import com.experta.pap.exceptions.ConnectionException;
import com.experta.pap.exceptions.GenericException;

public interface IWatsonDao {

	public String predictSiniestros(String data) throws GenericException, ConnectionException;
	
}

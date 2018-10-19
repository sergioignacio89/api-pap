package com.experta.pap.dao;

import com.experta.pap.exceptions.ConnectionException;
import com.experta.pap.exceptions.GenericException;

public interface IWatsonDao {

	public String predictAccidents(String data) throws GenericException, ConnectionException;
	
}

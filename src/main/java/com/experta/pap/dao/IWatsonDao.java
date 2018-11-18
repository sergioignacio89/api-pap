package com.experta.pap.dao;

import com.experta.pap.exceptions.ConnectionException;
import com.experta.pap.model.WatsonResponse;

/**
 * Interfaz para conexion con IBM Watson.
 * 
 * <p>
 * Realiza la comunicacion con IBM Watson para prediccion de siniestros.
 * </p>
 * 
 * @author Sergio Massa
 *
 */
public interface IWatsonDao {

	public WatsonResponse predictAccidents(String data) throws ConnectionException;
	
}

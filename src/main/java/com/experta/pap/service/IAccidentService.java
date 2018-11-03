package com.experta.pap.service;

import java.util.List;

import com.experta.pap.exceptions.ConnectionException;
import com.experta.pap.exceptions.GenericException;
import com.experta.pap.model.Accident;
import com.experta.pap.model.AccidentInferred;

/**
 * Interfaz de servicio para la prediccion de siniestros
 * 
 * @author Sergio Massa
 * @see Accident
 * @see AccidentInferred
 *
 */
public interface IAccidentService {

	/**
	 * Metodo para desarrollar la logica de prediccion de siniestros.
	 * 
	 * @author Sergio Massa
	 * 
	 * @param accidents a predecir
	 * @return  Lista de siniestros y sus predicciones
	 * 
	 * @throws ConnectionException
	 * @throws GenericException
	 * 
	 * @see Accident
	 * @see AccidentInferred 
	 */
	public List<AccidentInferred> predictAccidents(List<Accident> accidents) throws ConnectionException, GenericException;
}

package com.experta.pap.service;

import java.util.List;

import com.experta.pap.exceptions.ConnectionException;
import com.experta.pap.exceptions.GenericException;
import com.experta.pap.exceptions.ResourcesException;
import com.experta.pap.model.Accident;
import com.experta.pap.model.AccidentInferred;
import com.experta.pap.model.RangeConfiguration;
import com.experta.pap.model.WrapperRangeConfiguration;

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
	 * @throws Exception 
	 * 
	 * @see Accident
	 * @see AccidentInferred 
	 */
	public List<AccidentInferred> predictAccidents(List<Accident> accidents) throws ConnectionException, Exception;
	
	/**
	 * Metodo para recuperar los rangos de criticidad de los siniestros
	 * 
	 * @author Sergio Massa
	 * 
	 * @return {@link WrapperRangeConfiguration} Lista de rangos
	 * 
	 * @throws GenericException
	 * @throws ResourcesException 
	 * 
	 * @see RangeConfiguration 
	 */
	public WrapperRangeConfiguration getRangesConfiguration() throws GenericException, ResourcesException;
}

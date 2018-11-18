package com.experta.pap.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.experta.pap.dao.IWatsonDao;
import com.experta.pap.exceptions.ConnectionException;
import com.experta.pap.exceptions.GenericException;
import com.experta.pap.exceptions.RequestWatsonException;
import com.experta.pap.exceptions.ResourcesException;
import com.experta.pap.model.Accident;
import com.experta.pap.model.AccidentInferred;
import com.experta.pap.model.WatsonResponse;
import com.experta.pap.model.WrapperRangeConfiguration;
import com.experta.pap.service.IAccidentService;
import com.experta.pap.utils.AccidentUtil;
import com.experta.pap.utils.JsonMapper;
import com.experta.pap.utils.StringUtil;

/**
 * Capa de servicio que implementa {@link IAccidentService}
 * 
 * <p>
 * Contiene la logica para realizar las predicciones solicitadas
 * </p>
 * 
 * @author Sergio Massa
 *
 */
@Service
public class AccidentServiceImpl implements IAccidentService {

	private static final Logger LOGGER = Logger.getLogger(FileServiceImpl.class.getName());

	@Autowired
	private IWatsonDao watsonDao;

	/**
	 * Logica para prediccion de siniestros
	 * 
	 * @author Sergio Massa
	 * 
	 * @param accidents de tipo {@link Accident}
	 * @return  Lista de siniestros y sus predicciones {@link AccidentInferred}
	 * 
	 * @throws ConnectionException
	 * @throws {@link Exception}
	 */
	public List<AccidentInferred> predictAccidents(List<Accident> accidents) throws ConnectionException, Exception {

		List<AccidentInferred> accidentsInferred = new ArrayList<>();
		try {
			List<List<String>> data = new ArrayList<>();
			
			for(Accident s : accidents) {
				data.add(AccidentUtil.retrieveData(s));
			}
			
			WatsonResponse watsonResponse = watsonDao.predictAccidents(StringUtil.purifyAccident(data.toString()));
			
			if(watsonResponse.getResponseCode() == HttpStatus.OK.value()) {
				List<String> inferredPercentage = JsonMapper.getInferredPercentage(watsonResponse.getResponse());
				accidentsInferred = JsonMapper.convertJsonToAccidentInferred(accidents, inferredPercentage);
				
			} else {
				String error = JsonMapper.getWatsonError(watsonResponse.getResponse());
				LOGGER.severe("Parametros enviados a watson incorrectos: " + error);
				throw new RequestWatsonException(
						"Valores enviados a watson inconsistentes con los tipos de datos esperados: " + error);
			}	
			
		} catch(Exception e) {
			e.printStackTrace();
			throw e;
		}

		return accidentsInferred;
	}

	
	/**
	 * Recupera los rangos de criticidad de los porcentajes de siniestros
	 * 
	 * @author Sergio Massa
	 * 
	 * @param rangos de criticidad
	 * @return  Lista de siniestros y sus predicciones {@link AccidentInferred}
	 * 
	 * @see WrapperRangeConfiguration
	 * @throws GenericException
	 * @throws ResourcesException 
	 */
	@Override
	public WrapperRangeConfiguration getRangesConfiguration() throws GenericException, ResourcesException {

		WrapperRangeConfiguration ranges = AccidentUtil.getRangeConfiguration();
		return ranges;
	}

	
}

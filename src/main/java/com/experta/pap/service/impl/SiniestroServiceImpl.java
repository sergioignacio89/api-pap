package com.experta.pap.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.experta.pap.dao.IWatsonDao;
import com.experta.pap.exceptions.ConnectionException;
import com.experta.pap.exceptions.GenericException;
import com.experta.pap.model.Predictions;
import com.experta.pap.model.Siniestro;
import com.experta.pap.model.SiniestroInferido;
import com.experta.pap.service.ISiniestroService;
import com.experta.pap.utils.JsonMapper;
import com.experta.pap.utils.SiniestroUtil;

@Service
public class SiniestroServiceImpl implements ISiniestroService {

	@Autowired
	private IWatsonDao watsonDao;

	public List<SiniestroInferido> predictSiniestros(List<Siniestro> siniestros) throws ConnectionException, GenericException {

		List<SiniestroInferido> siniestroInferred = new ArrayList<>();
		try {
			List<List<String>> data = new ArrayList<>();
			
			for(Siniestro s : siniestros) {
				data.add(SiniestroUtil.retrieveData(s));
			}
			
			String jsonStringScoring = watsonDao.predictSiniestros(SiniestroUtil.purifySiniestro(data.toString()));
			
			List<Predictions> inferredPercentage = JsonMapper.getInferredPercentage(jsonStringScoring);
			siniestroInferred = JsonMapper.convertJsonToSiniestroInferred(siniestros, inferredPercentage);
			
		} catch (ConnectionException e) {
			e.printStackTrace();
			throw e;
		} catch (GenericException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
		}

		return siniestroInferred;
	}

}

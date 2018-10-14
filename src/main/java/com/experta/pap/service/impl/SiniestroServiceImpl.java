package com.experta.pap.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.experta.pap.dao.IWatsonDao;
import com.experta.pap.exceptions.ConnectionException;
import com.experta.pap.exceptions.GenericException;
import com.experta.pap.model.Siniestro;
import com.experta.pap.service.ISiniestroService;
import com.experta.pap.utils.JsonMapper;

@Service
public class SiniestroServiceImpl implements ISiniestroService {

	@Autowired
	private IWatsonDao watsonDao;

	public List<Siniestro> predictSiniestros(List<Siniestro> siniestros) throws ConnectionException, GenericException {

		JsonMapper mapper = new JsonMapper();

		try {
			String jsonSiniestros = mapper.convertSiniestroToJson(siniestros);
			watsonDao.predictSiniestros(jsonSiniestros);

		} catch (ConnectionException e) {
			e.printStackTrace();
			throw e;
		} catch (GenericException e) {
			e.printStackTrace();
			throw e;
		}

		return null;
	}

}

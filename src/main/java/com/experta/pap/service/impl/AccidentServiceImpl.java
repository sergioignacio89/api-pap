package com.experta.pap.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.experta.pap.dao.IWatsonDao;
import com.experta.pap.exceptions.ConnectionException;
import com.experta.pap.exceptions.GenericException;
import com.experta.pap.model.Accident;
import com.experta.pap.model.AccidentInferred;
import com.experta.pap.service.IAccidentService;
import com.experta.pap.utils.JsonMapper;
import com.experta.pap.utils.AccidentUtil;

@Service
public class AccidentServiceImpl implements IAccidentService {

	@Autowired
	private IWatsonDao watsonDao;

	public List<AccidentInferred> predictAccidents(List<Accident> accidents) throws ConnectionException, GenericException {

		List<AccidentInferred> accidentsInferred = new ArrayList<>();
		try {
			List<List<String>> data = new ArrayList<>();
			
			for(Accident s : accidents) {
				data.add(AccidentUtil.retrieveData(s));
			}
			
			String jsonStringScoring = watsonDao.predictAccidents(AccidentUtil.purifyAccident(data.toString()));
			
			List<String> inferredPercentage = JsonMapper.getInferredPercentage(jsonStringScoring);
			accidentsInferred = JsonMapper.convertJsonToAccidentInferred(accidents, inferredPercentage);
			
		} catch (ConnectionException e) {
			e.printStackTrace();
			throw e;
		} catch (GenericException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
		}

		return accidentsInferred;
	}

}

package com.experta.pap.service;

import java.util.List;

import com.experta.pap.exceptions.ConnectionException;
import com.experta.pap.exceptions.GenericException;
import com.experta.pap.model.Accident;
import com.experta.pap.model.AccidentInferred;

public interface IAccidentService {

	public List<AccidentInferred> predictAccidents(List<Accident> accidents) throws ConnectionException, GenericException;
}

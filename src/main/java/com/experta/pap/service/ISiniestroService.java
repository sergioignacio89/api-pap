package com.experta.pap.service;

import java.util.List;

import com.experta.pap.exceptions.ConnectionException;
import com.experta.pap.exceptions.GenericException;
import com.experta.pap.model.Siniestro;
import com.experta.pap.model.SiniestroInferido;

public interface ISiniestroService {

	public List<SiniestroInferido> predictSiniestros(List<Siniestro> siniestros) throws ConnectionException, GenericException;
}

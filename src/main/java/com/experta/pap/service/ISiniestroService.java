package com.experta.pap.service;

import java.util.List;

import com.experta.pap.model.Siniestro;

public interface ISiniestroService {

	public List<Siniestro> predictSiniestros(List<Siniestro> siniestros);
}

package com.experta.pap.dao;

import java.util.List;

import com.experta.pap.model.Siniestro;

public interface WatsonDao {

	// TODO que devuelve?
	public void predictSiniestros(List<Siniestro> siniestros);
	
}

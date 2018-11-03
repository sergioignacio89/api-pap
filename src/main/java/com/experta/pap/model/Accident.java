package com.experta.pap.model;

import com.experta.pap.exceptions.ParseException;

/**
 * Modelo que mapea los siniestros a predecir
 * 
 * <p>
 * Cada atributo representa una columna en el excel de siniestros
 * </p>
 * 
 * @author Sergio Massa
 *
 */
public class Accident {

	private String id;
	private String siniestroSeveridad;
	private String siniestroCausa;
	private String siniestroParteCuerpo;
	private String siniestradoDescUltimoDX;
	private String siniestroCircunstancia;
	private String siniestroFKT;
	private String siniestroAltaMedica;
	private String siniestroDiagnostico;
	private String siniestroCirugia;
	private String siniestroEstudios;
	private String siniestroPeriodo;
	private String siniestroPrestadorProvincia;
	private String siniestroCanalIngreso;
	private String siniestroCaseSML;
	private String siniestroCaseSupervisor;
	private String siniestroPrestador;
	private String empresaCP;
	private String empresaProvincia;
	private String juicioTiene;
	private String siniestradoNacionalidad;
	private String siniestradoCP;
	private String siniestradoSexo;
	private String siniestradoFhNacimiento;
	private String tipoPoliza;
	private String localidadPoliza;

	public Accident() {
	}

	
	/**
	 * Constructor sobrecargado
	 * 
	 * @author Sergio Massa
	 * 
	 * @param data cuyos valores estan separados por ;
	 * <p> El orden de los valores debe coincidir con el de las columnas del excel
	 * </p>
	 * @throws ParseException
	 */
	public Accident(String data) throws ParseException {

		String[] values = data.split(";");

		if (values.length != 26) {
			throw new ParseException("Incorrect number of fields");
		}
		
		id =values[0];
		siniestroSeveridad = values[1];
		siniestroCausa = values[2];
		siniestroParteCuerpo = values[3];
		siniestradoDescUltimoDX = values[4];
		siniestroCircunstancia = values[5];
		siniestroFKT = values[6];
		siniestroAltaMedica = values[7];
		siniestroDiagnostico = values[8];
		siniestroCirugia = values[9];
		siniestroEstudios = values[10];
		siniestroPeriodo = values[11];
		siniestroPrestadorProvincia = values[12];
		siniestroCanalIngreso = values[13];
		siniestroCaseSML = values[14];
		siniestroCaseSupervisor = values[15];
		siniestroPrestador = values[16];
		empresaCP = values[17];
		empresaProvincia = values[18];
		juicioTiene = values[19];
		siniestradoNacionalidad = values[20];
		siniestradoCP = values[21];
		siniestradoSexo = values[22];
		siniestradoFhNacimiento = values[23];
		tipoPoliza = values[24];
		localidadPoliza = values[25];

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSiniestroSeveridad() {
		return siniestroSeveridad;
	}

	public void setSiniestroSeveridad(String siniestroSeveridad) {
		this.siniestroSeveridad = siniestroSeveridad;
	}

	public String getSiniestroCausa() {
		return siniestroCausa;
	}

	public void setSiniestroCausa(String siniestroCausa) {
		this.siniestroCausa = siniestroCausa;
	}

	public String getSiniestroParteCuerpo() {
		return siniestroParteCuerpo;
	}

	public void setSiniestroParteCuerpo(String siniestroParteCuerpo) {
		this.siniestroParteCuerpo = siniestroParteCuerpo;
	}

	public String getSiniestradoDescUltimoDX() {
		return siniestradoDescUltimoDX;
	}

	public void setSiniestradoDescUltimoDX(String siniestradoDescUltimoDX) {
		this.siniestradoDescUltimoDX = siniestradoDescUltimoDX;
	}

	public String getSiniestroCircunstancia() {
		return siniestroCircunstancia;
	}

	public void setSiniestroCircunstancia(String siniestroCircunstancia) {
		this.siniestroCircunstancia = siniestroCircunstancia;
	}

	public String getSiniestroFKT() {
		return siniestroFKT;
	}

	public void setSiniestroFKT(String siniestroFKT) {
		this.siniestroFKT = siniestroFKT;
	}

	public String getSiniestroAltaMedica() {
		return siniestroAltaMedica;
	}

	public void setSiniestroAltaMedica(String siniestroAltaMedica) {
		this.siniestroAltaMedica = siniestroAltaMedica;
	}

	public String getSiniestroDiagnostico() {
		return siniestroDiagnostico;
	}

	public void setSiniestroDiagnostico(String siniestroDiagnostico) {
		this.siniestroDiagnostico = siniestroDiagnostico;
	}

	public String getSiniestroCirugia() {
		return siniestroCirugia;
	}

	public void setSiniestroCirugia(String siniestroCirugia) {
		this.siniestroCirugia = siniestroCirugia;
	}

	public String getSiniestroEstudios() {
		return siniestroEstudios;
	}

	public void setSiniestroEstudios(String siniestroEstudios) {
		this.siniestroEstudios = siniestroEstudios;
	}

	public String getSiniestroPeriodo() {
		return siniestroPeriodo;
	}

	public void setSiniestroPeriodo(String siniestroPeriodo) {
		this.siniestroPeriodo = siniestroPeriodo;
	}

	public String getSiniestroPrestadorProvincia() {
		return siniestroPrestadorProvincia;
	}

	public void setSiniestroPrestadorProvincia(String siniestroPrestadorProvincia) {
		this.siniestroPrestadorProvincia = siniestroPrestadorProvincia;
	}

	public String getSiniestroCanalIngreso() {
		return siniestroCanalIngreso;
	}

	public void setSiniestroCanalIngreso(String siniestroCanalIngreso) {
		this.siniestroCanalIngreso = siniestroCanalIngreso;
	}

	public String getSiniestroCaseSML() {
		return siniestroCaseSML;
	}

	public void setSiniestroCaseSML(String siniestroCaseSML) {
		this.siniestroCaseSML = siniestroCaseSML;
	}

	public String getSiniestroCaseSupervisor() {
		return siniestroCaseSupervisor;
	}

	public void setSiniestroCaseSupervisor(String siniestroCaseSupervisor) {
		this.siniestroCaseSupervisor = siniestroCaseSupervisor;
	}

	public String getSiniestroPrestador() {
		return siniestroPrestador;
	}

	public void setSiniestroPrestador(String siniestroPrestador) {
		this.siniestroPrestador = siniestroPrestador;
	}

	public String getEmpresaCP() {
		return empresaCP;
	}

	public void setEmpresaCP(String empresaCP) {
		this.empresaCP = empresaCP;
	}

	public String getEmpresaProvincia() {
		return empresaProvincia;
	}

	public void setEmpresaProvincia(String empresaProvincia) {
		this.empresaProvincia = empresaProvincia;
	}

	public String getJuicioTiene() {
		return juicioTiene;
	}

	public void setJuicioTiene(String juicioTiene) {
		this.juicioTiene = juicioTiene;
	}

	public String getSiniestradoNacionalidad() {
		return siniestradoNacionalidad;
	}

	public void setSiniestradoNacionalidad(String siniestradoNacionalidad) {
		this.siniestradoNacionalidad = siniestradoNacionalidad;
	}

	public String getSiniestradoCP() {
		return siniestradoCP;
	}

	public void setSiniestradoCP(String siniestradoCP) {
		this.siniestradoCP = siniestradoCP;
	}

	public String getSiniestradoSexo() {
		return siniestradoSexo;
	}

	public void setSiniestradoSexo(String siniestradoSexo) {
		this.siniestradoSexo = siniestradoSexo;
	}

	public String getSiniestradoFhNacimiento() {
		return siniestradoFhNacimiento;
	}

	public void setSiniestradoFhNacimiento(String siniestradoFhNacimiento) {
		this.siniestradoFhNacimiento = siniestradoFhNacimiento;
	}

	public String getTipoPoliza() {
		return tipoPoliza;
	}

	public void setTipoPoliza(String tipoPoliza) {
		this.tipoPoliza = tipoPoliza;
	}

	public String getLocalidadPoliza() {
		return localidadPoliza;
	}

	public void setLocalidadPoliza(String localidadPoliza) {
		this.localidadPoliza = localidadPoliza;
	}

	@Override
	public String toString() {
		return "Accident [id=" + id + ", siniestroSeveridad=" + siniestroSeveridad + ", siniestroCausa="
				+ siniestroCausa + ", siniestroParteCuerpo=" + siniestroParteCuerpo + ", siniestradoDescUltimoDX="
				+ siniestradoDescUltimoDX + ", siniestroCircunstancia=" + siniestroCircunstancia + ", siniestroFKT="
				+ siniestroFKT + ", siniestroAltaMedica=" + siniestroAltaMedica + ", siniestroDiagnostico="
				+ siniestroDiagnostico + ", siniestroCirugia=" + siniestroCirugia + ", siniestroEstudios="
				+ siniestroEstudios + ", siniestroPeriodo=" + siniestroPeriodo + ", siniestroPrestadorProvincia="
				+ siniestroPrestadorProvincia + ", siniestroCanalIngreso=" + siniestroCanalIngreso
				+ ", siniestroCaseSML=" + siniestroCaseSML + ", siniestroCaseSupervisor=" + siniestroCaseSupervisor
				+ ", siniestroPrestador=" + siniestroPrestador + ", empresaCP=" + empresaCP + ", empresaProvincia="
				+ empresaProvincia + ", juicioTiene=" + juicioTiene + ", siniestradoNacionalidad="
				+ siniestradoNacionalidad + ", siniestradoCP=" + siniestradoCP + ", siniestradoSexo=" + siniestradoSexo
				+ ", siniestradoFhNacimiento=" + siniestradoFhNacimiento + ", tipoPoliza=" + tipoPoliza
				+ ", localidadPoliza=" + localidadPoliza + "]";
	}

}

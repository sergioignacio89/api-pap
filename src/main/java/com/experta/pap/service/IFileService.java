package com.experta.pap.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.experta.pap.exceptions.BusinessException;
import com.experta.pap.model.Accident;
import com.experta.pap.model.FileInfo;

/**
 * Interfaz de servicio para operar con archivos
 * 
 * @author Sergio Massa
 * @see MultipartFile
 *
 */
public interface IFileService {

	/**
	 * Metodo para desarrollar la logica de descarga de archivo en directorio
	 * temporal coonfigurable
	 * 
	 * @author Sergio Massa
	 * 
	 * @param file
	 * @return FileInfo con informacion general del archivo descargado
	 * 
	 * @throws BusinessException
	 * 
	 * @see FileInfo
	 * @see MultipartFile
	 */
	public FileInfo saveFileToLocalTemp(MultipartFile file) throws BusinessException;

	/**
	 * Metodo para desarrollar la logica de lectura de archivo.
	 * 
	 * @author Sergio Massa
	 * 
	 * @param name
	 *            del archivo a abrir
	 * @return Lista de siniestros
	 * 
	 * @throws BusinessException
	 * 
	 * @see Accident
	 */
	public List<Accident> readFile(String name) throws BusinessException;

	/**
	 * Metodo para desarrollar la logica de creacion de archivo en directorio
	 * 
	 * @author Sergio Massa
	 */
	public void createFile();
}

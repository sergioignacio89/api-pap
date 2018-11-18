package com.experta.pap.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.experta.pap.exceptions.BusinessException;
import com.experta.pap.exceptions.ExcelException;
import com.experta.pap.exceptions.FileException;
import com.experta.pap.exceptions.ResourcesException;
import com.experta.pap.model.Accident;
import com.experta.pap.model.FileInfo;
import com.experta.pap.service.IFileService;
import com.experta.pap.utils.ExcelUtil;
import com.experta.pap.utils.Resources;

/**
 * Capa de servicio que implementa {@link IFileService}
 * 
 * <p>
 * Contiene la logica para el tratamiento de archivos
 * </p>
 * 
 * @author Sergio Massa
 *
 */
@Service
public class FileServiceImpl implements IFileService {

	private static final Logger LOGGER = Logger.getLogger(FileServiceImpl.class.getName());

	/**
	 * Logica para descarga de archivos a directorio temp configurable
	 * 
	 * <p>
	 * El directorio de temp se configura mediante el archivo app-resources.properties. El mismo se recupera mediante
	 * la key "pap.location.files.temp"
	 * </p>
	 * 
	 * @author Sergio Massa
	 * 
	 * @param file de tipo {@link MultipartFile}
	 * @return  {@link FileInfo}
	 * 
	 * @throws FileException
	 * @throws ResourcesException
	 */
	public FileInfo saveFileToLocalTemp(MultipartFile file) throws FileException, ResourcesException {

		FileInfo fileInfo;
		try {
			Properties props = Resources.getProperties();

			String fileName = file.getOriginalFilename();
			File f = new File(props.getProperty("pap.location.files.temp") + File.separator + fileName);
			file.transferTo(f);

			fileInfo = new FileInfo();
			fileInfo.set_fileName(fileName);
			fileInfo.set_fileSize(file.getSize());

			LOGGER.log(Level.INFO, "Archivo descargado: " + fileInfo.toString());

		} catch(ResourcesException e) {
			LOGGER.severe(e.getMessage());
			e.printStackTrace();
			throw e;
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new FileException(e.getMessage());
		}

		return fileInfo;
	}

	/**
	 * Logica para lectura de archivo excel con siniestros
	 * 
	 * <p>
	 * El directorio de temp de deonde se recupera el excel con siniestros se
	 * obtiene mediante la key "pap.location.files.temp" del archivo
	 * app-resources.properties
	 * </p>
	 * 
	 * @author Sergio Massa
	 * 
	 * @param name
	 *            con el nombre del archivo
	 * @return {@link Accident}
	 * 
	 * @throws BusinessException
	 * @throws {@link ExcelException}
	 * @throws ResourcesException
	 */
	@Override
	public List<Accident> readFile(String name) throws BusinessException, ExcelException, ResourcesException {

		List<Accident> accidents;
		FileInputStream excelFile = null;
		try {
			Properties props = Resources.getProperties();

			File file = new File(props.getProperty("pap.location.files.temp") + File.separator + name);
			excelFile = new FileInputStream(file);

			ExcelUtil excelUtil = new ExcelUtil(Boolean.valueOf(props.getProperty("pap.files.excel.header")));
			accidents = excelUtil.fromExcelToAccidents(excelFile);

		} catch(ExcelException e) {
			LOGGER.severe("Error al mapear siniestros del excel: " + e.getMessage());
			e.printStackTrace();
			throw e;
			
		} catch(ResourcesException e) {
			LOGGER.severe(e.getMessage());
			e.printStackTrace();
			throw e;
			
		} catch (Exception e) {
			LOGGER.severe("Error");
			e.printStackTrace();
			throw new BusinessException("Fallo al leer archivo de siniestros: " + e.getMessage());

		} finally {
			try {
				if (excelFile != null) {
					excelFile.close();
				}
			} catch (Exception e) {

			}
		}

		return accidents;
	}

	/**
	 * No aplica en el contexto de la aplicacion
	 */
	public void createFile() {
		// TODO Auto-generated method stub

	}

}

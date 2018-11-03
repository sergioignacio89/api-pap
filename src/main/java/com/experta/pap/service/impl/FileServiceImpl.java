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
	 * @throws BusinessException
	 */
	public FileInfo saveFileToLocalTemp(MultipartFile file) throws BusinessException {

		FileInfo fileInfo;
		try {
			Properties props = Resources.getProperties();

			String fileName = file.getOriginalFilename();
			File f = new File(props.getProperty("pap.location.files.temp") + File.separator + fileName);
			file.transferTo(f);

			fileInfo = new FileInfo();
			fileInfo.set_fileName(fileName);
			fileInfo.set_fileSize(file.getSize());

			LOGGER.log(Level.INFO, "Downloaded file: " + fileInfo.toString());

		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("error when processing the excel file download");
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
	 */
	@Override
	public List<Accident> readFile(String name) throws BusinessException {

		List<Accident> accidents;
		FileInputStream excelFile = null;
		try {
			Properties props = Resources.getProperties();

			File file = new File(props.getProperty("pap.location.files.temp") + File.separator + name);
			excelFile = new FileInputStream(file);

			ExcelUtil excelUtil = new ExcelUtil(Boolean.valueOf(props.getProperty("pap.files.excel.header")));
			accidents = excelUtil.fromExcelToAccidents(excelFile);

		} catch (Exception e) {
			LOGGER.severe("Error");
			e.printStackTrace();
			throw new BusinessException("error when processing the excel file");

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

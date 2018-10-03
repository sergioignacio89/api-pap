package com.experta.pap.service.impl;

import java.io.File;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.experta.pap.exceptions.BusinessException;
import com.experta.pap.model.FileInfo;
import com.experta.pap.model.Siniestro;
import com.experta.pap.service.IFileService;
import com.experta.pap.utils.Resources;

@Service
public class FileServiceImpl implements IFileService {

	private static final Logger LOGGER = Logger.getLogger(FileServiceImpl.class.getName());

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

	@Override
	public List<Siniestro> readFile(String name) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	public void createFile() {
		// TODO Auto-generated method stub

	}

}

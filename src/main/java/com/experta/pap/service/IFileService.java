package com.experta.pap.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.experta.pap.exceptions.BusinessException;
import com.experta.pap.model.FileInfo;
import com.experta.pap.model.Siniestro;

public interface IFileService {

	public FileInfo saveFileToLocalTemp(MultipartFile file) throws BusinessException;
	public List<Siniestro> readFile(String name) throws BusinessException;
	public void createFile();
}

package com.experta.pap.controller;

import java.io.File;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.message.MessageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.experta.pap.model.FileInfo;
import com.experta.pap.model.Siniestro;
import com.experta.pap.service.IFileService;
import com.experta.pap.service.ISiniestroService;

@RestController(value = "/siniestros")
public class SiniestroController {

	private static final Logger logger = LogManager.getLogger("SiniestroController");
	 
	private final String FILE_UPLOAD_LOCATION = "C:\\Users\\Sergio\\Documents\\fileupload";

	@Autowired
	private ISiniestroService siniestroService;
	@Autowired
	private IFileService fileService;

	@RequestMapping(value = "/upload", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<FileInfo> uploadFile(@RequestParam("file") MultipartFile file) {

		ResponseEntity<FileInfo> response;

		if (!file.isEmpty()) {
			try {
				String fileName = file.getOriginalFilename();
				File f = new File(FILE_UPLOAD_LOCATION + File.separator + fileName);
				file.transferTo(f);

				FileInfo fileInfo = new FileInfo();
				fileInfo.setFileName(fileName);
				fileInfo.setFileSize(file.getSize());

				response = new ResponseEntity<FileInfo>(fileInfo, HttpStatus.OK);

			} catch (Exception e) {
				e.printStackTrace();
				response = new ResponseEntity<FileInfo>(HttpStatus.INTERNAL_SERVER_ERROR);
			}

		} else {
			response = new ResponseEntity<FileInfo>(HttpStatus.BAD_REQUEST);
		}
		return response;
	}

	public List<Siniestro> predictSiniestros(){
		//TODO
		return null;
	}
}

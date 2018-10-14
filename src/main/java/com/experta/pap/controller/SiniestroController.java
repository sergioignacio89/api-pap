package com.experta.pap.controller;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
import com.experta.pap.model.dto.ResponseSiniestrosDTO;
import com.experta.pap.service.IFileService;
import com.experta.pap.service.ISiniestroService;

@RestController
@RequestMapping("/siniestros")
public class SiniestroController {

	private static final Logger LOGGER = Logger.getLogger(SiniestroController.class.getName());

	@Autowired
	private ISiniestroService siniestroService;
	@Autowired
	private IFileService fileService;

	@RequestMapping(value = "/upload", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<FileInfo> uploadSiniestrosFile(@RequestParam("file") MultipartFile file,
			HttpSession session) {

		ResponseEntity<FileInfo> response;

		if ((file == null) || (file.isEmpty())) {
			LOGGER.log(Level.WARNING, "the excel file is empty");
			response = new ResponseEntity<FileInfo>(HttpStatus.BAD_REQUEST);
			return response;
		}

		try {
			FileInfo fileInfo = fileService.saveFileToLocalTemp(file);

			session.setAttribute("file", fileInfo);
			response = new ResponseEntity<FileInfo>(fileInfo, HttpStatus.OK);

		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, e.getMessage());
			e.printStackTrace();
			response = new ResponseEntity<FileInfo>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return response;
	}

	@RequestMapping(value = "/predict", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseSiniestrosDTO> predictSiniestros(HttpServletRequest request) {

		// TODO
		ResponseEntity<ResponseSiniestrosDTO> response;
		ResponseSiniestrosDTO responseSiniestrosDTO = new ResponseSiniestrosDTO();

		HttpSession session = request.getSession(false);

		if (session == null) {
			String errorMessage = "error when recovering the user session";
			LOGGER.log(Level.SEVERE, errorMessage);

			responseSiniestrosDTO.set_errorMessage(errorMessage);
			response = new ResponseEntity<>(responseSiniestrosDTO, HttpStatus.BAD_REQUEST);
			return response;
		}

		Object objFile = session.getAttribute("file");

		if ((objFile == null) && !(objFile instanceof FileInfo)) {

			String errorMessage = "error when recovering the excel file: " + objFile;
			LOGGER.log(Level.SEVERE, errorMessage);

			responseSiniestrosDTO.set_errorMessage(errorMessage);
			response = new ResponseEntity<>(responseSiniestrosDTO, HttpStatus.INTERNAL_SERVER_ERROR);
			return response;
		}

		FileInfo fileInfo = (FileInfo) objFile;
		try {

			List<Siniestro> siniestros = fileService.readFile(fileInfo.get_fileName());
			siniestroService.predictSiniestros(siniestros);
			responseSiniestrosDTO.set_siniestros(siniestros);
			response = new ResponseEntity<>(responseSiniestrosDTO, HttpStatus.OK);
			return response;

		} catch (Exception e) {
			String errorMessage = "error when processing siniestros: " + e.getMessage();
			LOGGER.log(Level.SEVERE, errorMessage);
			e.printStackTrace();

			responseSiniestrosDTO.set_errorMessage(errorMessage);
			response = new ResponseEntity<>(responseSiniestrosDTO, HttpStatus.INTERNAL_SERVER_ERROR);
			return response;
		}
	}
}

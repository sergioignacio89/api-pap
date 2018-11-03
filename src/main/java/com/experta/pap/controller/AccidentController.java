package com.experta.pap.controller;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.experta.pap.model.Accident;
import com.experta.pap.model.AccidentInferred;
import com.experta.pap.model.FileInfo;
import com.experta.pap.model.dto.ResponseInferredDTO;
import com.experta.pap.service.IAccidentService;
import com.experta.pap.service.IFileService;

/**
 * Controlador para prediccion de siniestros.
 * 
 * <p>
 * Las predicciones se realizaran recibiendo como input un excel.
 * </p>
 * 
 * @author Sergio Massa
 *
 */
@RestController
@RequestMapping("/accidents")
public class AccidentController {

	private static final Logger LOGGER = Logger.getLogger(AccidentController.class.getName());

	@Autowired
	private IAccidentService accidentService;
	@Autowired
	private IFileService fileService;

	/**
	 * Endpoint para prediccion de siniestros.
	 * 
	 * @author Sergio Massa
	 * 
	 * @param file de tipo {@link MultipartFile}
	 * @return  Wrapper con lista de siniestros
	 * 
	 * @see AccidentInferred
	 * @see ResponseInferredDTO 
	 */
	
	@CrossOrigin
	@RequestMapping(value = "/predict", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseInferredDTO> uploadAccidentsFile(@RequestParam("file") MultipartFile file) {

		ResponseEntity<ResponseInferredDTO> response;
		ResponseInferredDTO responseAccidentDTO = new ResponseInferredDTO();

		if ((file == null) || (file.isEmpty())) {
			String errorMessage = "the excel file is empty";
			LOGGER.log(Level.SEVERE, errorMessage);
			responseAccidentDTO.set_errorMessage(errorMessage);
			response = new ResponseEntity<ResponseInferredDTO>(responseAccidentDTO, HttpStatus.BAD_REQUEST);
			return response;
		}

		try {
			FileInfo fileInfo = fileService.saveFileToLocalTemp(file);

			List<Accident> accidents = fileService.readFile(fileInfo.get_fileName());
			
			if(accidents.size() == 0) {
				String errorMessage = "no accidents were found in the excel file";
				LOGGER.severe(errorMessage);
				responseAccidentDTO.set_errorMessage(errorMessage);
				response = new ResponseEntity<ResponseInferredDTO>(responseAccidentDTO, HttpStatus.BAD_REQUEST);
				return response;
			}
			
			List<AccidentInferred> accidentInferred = accidentService.predictAccidents(accidents);

			responseAccidentDTO.set_accidents(accidentInferred);
			response = new ResponseEntity<>(responseAccidentDTO, HttpStatus.OK);

		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, e.getMessage());
			e.printStackTrace();
			responseAccidentDTO.set_errorMessage(e.getMessage());
			response = new ResponseEntity<ResponseInferredDTO>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return response;
	}

}

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

import com.experta.pap.exceptions.ConnectionException;
import com.experta.pap.exceptions.ExcelException;
import com.experta.pap.exceptions.FileException;
import com.experta.pap.exceptions.RequestWatsonException;
import com.experta.pap.exceptions.ResourcesException;
import com.experta.pap.model.Accident;
import com.experta.pap.model.AccidentInferred;
import com.experta.pap.model.FileInfo;
import com.experta.pap.model.WrapperRangeConfiguration;
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
	 * @return  Wrapper con lista de siniestros y los rangos de criticidad de los porcentajes de siniestros
	 * 
	 * @see AccidentInferred
	 * @see WrapperRangeConfiguration
	 * @see ResponseInferredDTO 
	 */
	
	@CrossOrigin
	@RequestMapping(value = "/predict", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseInferredDTO> uploadAccidentsFile(@RequestParam("file") MultipartFile file) {

		ResponseEntity<ResponseInferredDTO> response;
		ResponseInferredDTO responseAccidentDTO = new ResponseInferredDTO();

		if ((file == null) || (file.isEmpty())) {
			String errorMessage = "El archivo excel no tiene contenido";
			LOGGER.log(Level.SEVERE, errorMessage);
			responseAccidentDTO.setErrorMessage(errorMessage);
			response = new ResponseEntity<ResponseInferredDTO>(responseAccidentDTO, HttpStatus.BAD_REQUEST);
			return response;
		}
		try {
			FileInfo fileInfo = fileService.saveFileToLocalTemp(file);

			List<Accident> accidents = fileService.readFile(fileInfo.get_fileName());

			if (accidents.size() == 0) {
				String errorMessage = "El archivo de excel no contiene siniestros a predecir";
				LOGGER.severe(errorMessage);
				responseAccidentDTO.setErrorMessage(errorMessage);
				response = new ResponseEntity<ResponseInferredDTO>(responseAccidentDTO, HttpStatus.BAD_REQUEST);
				return response;
			}

			WrapperRangeConfiguration rangesConfiguration = accidentService.getRangesConfiguration();
			List<AccidentInferred> accidentInferred = accidentService.predictAccidents(accidents);

			responseAccidentDTO.setAccidents(accidentInferred);
			responseAccidentDTO.setRanges(rangesConfiguration);

			response = new ResponseEntity<>(responseAccidentDTO, HttpStatus.OK);

		} catch (ConnectionException e) {
			LOGGER.log(Level.SEVERE, e.getMessage());
			e.printStackTrace();
			
			responseAccidentDTO.setFriendlyError(
					"Error en la conexion a Watson. Revise las credenciales en el archivo properties de configuracion");
			responseAccidentDTO.setErrorMessage(e.getMessage());
			
			response = new ResponseEntity<ResponseInferredDTO>(responseAccidentDTO, HttpStatus.INTERNAL_SERVER_ERROR);

		} catch (ExcelException e) {
			LOGGER.log(Level.SEVERE, e.getMessage());
			e.printStackTrace();
			
			responseAccidentDTO.setFriendlyError(
					"Error al mapear siniestros del archivo excel. Compruebe el formato del mismo");
			responseAccidentDTO.setErrorMessage(e.getMessage());
			
			response = new ResponseEntity<ResponseInferredDTO>(responseAccidentDTO, HttpStatus.INTERNAL_SERVER_ERROR);

		} catch (FileException e) {
			LOGGER.log(Level.SEVERE, e.getMessage());
			e.printStackTrace();
			responseAccidentDTO.setFriendlyError(
					"Error al guardar excel de siniestros en el directorio de temporales");
			responseAccidentDTO.setErrorMessage(e.getMessage());
			
			response = new ResponseEntity<ResponseInferredDTO>(responseAccidentDTO, HttpStatus.INTERNAL_SERVER_ERROR);

		} catch (RequestWatsonException e) {
			LOGGER.log(Level.SEVERE, e.getMessage());
			e.printStackTrace();
			responseAccidentDTO.setFriendlyError(
					"Error en consultar a IBM Watson");
			responseAccidentDTO.setErrorMessage(e.getMessage());
			
			response = new ResponseEntity<ResponseInferredDTO>(responseAccidentDTO, HttpStatus.INTERNAL_SERVER_ERROR);

		} catch (ResourcesException e) {
			LOGGER.log(Level.SEVERE, e.getMessage());
			e.printStackTrace();
			responseAccidentDTO.setFriendlyError("Se produjo un error con el archivo de configuracion. Compruebe que se encuentre correctamente configurado");
			responseAccidentDTO.setErrorMessage(e.getMessage());
			
			response = new ResponseEntity<ResponseInferredDTO>(responseAccidentDTO, HttpStatus.INTERNAL_SERVER_ERROR);

		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, e.getMessage());
			e.printStackTrace();
			responseAccidentDTO.setFriendlyError("Error desconocido");
			responseAccidentDTO.setErrorMessage(e.getMessage());
			
			response = new ResponseEntity<ResponseInferredDTO>(responseAccidentDTO, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return response;
	}
		
}

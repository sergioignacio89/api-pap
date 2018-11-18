package com.experta.pap.utils;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.experta.pap.controller.AccidentController;
import com.experta.pap.enumerators.DefaultValuesEnum;
import com.experta.pap.exceptions.ExcelException;
import com.experta.pap.exceptions.ParseException;
import com.experta.pap.exceptions.RowExcelException;
import com.experta.pap.model.Accident;

/**
 * Clase utilitaria para tratamiento del excel de siniestros
 * 
 * @author Sergio Massa
 *
 */
public class ExcelUtil {

	private static final Logger LOGGER = Logger.getLogger(AccidentController.class.getName());

	private boolean header;

	public ExcelUtil(boolean header) {
		this.header = header;

	}

	/**
	 * Convierte el excel en siniestros
	 * 
	 * <p>
	 * Metodo principal que contiene toda la logica de mapeo
	 * </p>
	 * <p>
	 * El orden de las columnas debe coincidir con el orden de los atributos de la
	 * clase {@link Accident}
	 * </p>
	 * 
	 * @author Sergio Massa
	 * 
	 * @param excelFile
	 *            de tipo {@link FileInputStream}
	 * 
	 * @return lista de{@link Accident}
	 * 
	 * @throws ExcelException
	 */
	public List<Accident> fromExcelToAccidents(FileInputStream excelFile) throws ExcelException {

		List<Accident> accidents = new ArrayList<Accident>();
		Workbook workbook = null;
		try {
			workbook = new XSSFWorkbook(excelFile);

			for (Sheet sheet : workbook) {

				int i = 0;
				for (Row row : sheet) {

					if ((i == 0) && header) {
						i++;
						continue;
					}
					String rowValue = readRow(row);

					Accident accident = new Accident(rowValue);
					accidents.add(accident);
				}
				i++;
			}
		} catch(ParseException e) {
			LOGGER.severe("Fallo al instanciar siniestro: " + e.getMessage());
			e.printStackTrace();
			throw new ExcelException(e.getMessage());
			
		} catch(RowExcelException e) {
			LOGGER.severe("Fallo al mapear siniestro: " + e.getMessage());
			e.printStackTrace();
			throw new ExcelException(e.getMessage());
			
		} catch (Exception e) {
			String errorMessage = "Fallo al parsear excel: " + e.getMessage();
			LOGGER.severe(errorMessage);
			e.printStackTrace();
			throw new ExcelException(errorMessage);

		} finally {
			try {
				if (workbook != null) {
					workbook.close();
				}
			} catch (Exception e) {

			}
		}

		return accidents;
	}

	/**
	 * Convierte cada registro del excel en un siniestro.
	 * 
	 * <p>
	 * El orden de las columnas debe coincidir con el orden de los atributos de la
	 * clase {@link Accident}
	 * </p>
	 * 
	 * @author Sergio Massa
	 * 
	 * @param registro
	 *            de tipo {@link Row}
	 * 
	 * @return siniestro en String
	 * 
	 * @throws RowExcelException
	 */
	private String readRow(Row row) throws RowExcelException {

		StringBuilder rowValue = new StringBuilder();

		try {
			for (int i1 = 0; i1 < 25; i1++) {
				String cellValue;

				Cell cell = row.getCell(i1);

				switch (i1) {
				case 0:
					if (cell == null) {
						cellValue = DefaultValuesEnum.string.getValue();
					} else {
						String cellTmp = cell.toString();
						cellValue = cellTmp;
						// if(StringUtil.isNumber(cellTmp)) {
						// cellValue = StringUtil.convertToInteger(cellTmp);
						// }else {
						// cellValue = cellTmp;
						// }
					}
					break;
				case 7:
				case 10:
				case 16:
				case 17:
				case 18: // campo juicioTiene?
				case 19:
				case 20:
				case 21:
					if (cell == null || cell.toString().equals("")) {
						cellValue = DefaultValuesEnum.number.getValue();
					} else {
						String cellTmp = cell.toString();
						cellValue = StringUtil.convertToInteger(cellTmp);
					}
					break;
				case 22:
					if (cell == null) {
						cellValue = DefaultValuesEnum.date.getValue();
					} else {
						cellValue = StringUtil.parseToDate(cell.toString());
					}
					break;
				default:
					if (cell == null) {
						cellValue = DefaultValuesEnum.string.getValue();
					} else {
						cellValue = cell.getRichStringCellValue().getString().trim();
					}
				}
				rowValue.append(cellValue).append(";");
			}
		} catch (Exception e) {
			String errorMessage = "Row number[" + (row.getRowNum() + 1) + "] Accident number["
					+ row.getCell(0) + "] - " + e.getMessage();
			LOGGER.severe(errorMessage);
			e.printStackTrace();
			throw new RowExcelException(errorMessage);
		}
		
		return rowValue.toString();
	}
}

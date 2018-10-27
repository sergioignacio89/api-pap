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
import com.experta.pap.model.Accident;

public class ExcelUtil {

	private static final Logger LOGGER = Logger.getLogger(AccidentController.class.getName());

	private boolean header;

	public ExcelUtil(boolean header) {
		this.header = header;

	}

	public List<Accident> fromExcelToAccidents(FileInputStream excelFile) throws Exception {

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

					StringBuilder rowValue = new StringBuilder();

					for (int i1 = 0; i1 < 24; i1++) {
						String cellValue;
						Cell cell = row.getCell(i1);

						switch (i1) {
						case 5:
						case 6:
						case 7:
						case 8:
						case 9:
						case 10:
						case 16:
						case 17:
						case 18:
						case 19:
						case 20:
							if (cell == null) {
								cellValue = DefaultValuesEnum.number.getValue();
							} else {
								String cellTmp = cell.toString();
								cellValue = (StringUtil.purifyInteger(cellTmp));
							}
							break;
						case 21:
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

					Accident accident = new Accident(rowValue.toString());
					accidents.add(accident);
				}
				i++;
			}

		} catch (Exception e) {
			LOGGER.severe("Error parsing excel");
			throw e;

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

}

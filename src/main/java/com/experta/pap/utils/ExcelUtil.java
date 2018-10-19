package com.experta.pap.utils;

import java.io.FileInputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.experta.pap.model.Accident;

public class ExcelUtil {

	private boolean header;

	public ExcelUtil(boolean header) {
		this.header = header;

	}

	public List<Accident> fromExcelToAccidents(FileInputStream excelFile) {

		List<Accident> accidents = new ArrayList<Accident>();
		Workbook workbook = null;
		try {
			workbook = new XSSFWorkbook(excelFile);
//			DataFormatter dataFormatter = new DataFormatter();

			for (Sheet sheet : workbook) {

				int i = 0;
				for (Row row : sheet) {

					if ((i == 0) && header) {
						i++;
						continue;
					}

					StringBuilder rowValue = new StringBuilder();
					DecimalFormat decimalFormat = new DecimalFormat("0.#####");

					for (Cell cell : row) {

						String cellValue;

						switch (cell.getCellTypeEnum()) {
						
						case STRING:
							cellValue = cell.getRichStringCellValue().getString();
							break;
						
						case NUMERIC:
							if (DateUtil.isCellDateFormatted(cell)) {
								Date date = cell.getDateCellValue();
								SimpleDateFormat sdf1 = new SimpleDateFormat("yyy-MM-dd");
								cellValue = sdf1.format(date);
								
							} else {
								cellValue = String.valueOf(cell.getNumericCellValue());
								cellValue = decimalFormat.format(Double.valueOf(cellValue));
							}
							break;
						
						case BLANK:
						default:
							cellValue = "";
							break;
						}
						
						rowValue.append(cellValue).append(";");
					}

//					for (int i1 = 0; i1 < 25; i1++) {
//						String cellValue;
//						Cell cell = row.getCell(i1);
//						
//						switch(i1) {
//						case 7:
//						case 10:
//						case 16:
//						case 17:
//						case 18:
//						case 19:
//						case 20:
//							cellValue = (cell.toString().equals("-") || cell.toString().equals("")) ? cellValue = "0"
//									: cell.toString();
//							break;
//						case 21:
//							cellValue = (cell.toString().equals("-") || cell.toString().equals("")) ? cellValue = "3/10/1954"
//							: cell.toString();
//							
//							SimpleDateFormat sdf1 = new SimpleDateFormat("yyy-MM-dd");
//							cellValue = sdf1.parse(cellValue);
//							break;
//						default:
//							cellValue = cell.getRichStringCellValue().getString();
//						}
//						rowValue.append(cellValue).append(";");
//					}
					
					Accident accident = new Accident(rowValue.toString());
					accidents.add(accident);
				}
				i++;
			}

		} catch (Exception e) {
			e.printStackTrace();
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

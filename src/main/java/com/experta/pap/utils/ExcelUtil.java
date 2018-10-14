package com.experta.pap.utils;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.experta.pap.model.Siniestro;

public class ExcelUtil {

	private boolean header;

	public ExcelUtil(boolean header) {
		this.header = header;

	}

	public List<Siniestro> fromExcelToSiniestros(FileInputStream excelFile) {

		List<Siniestro> siniestros = new ArrayList<Siniestro>();
		Workbook workbook = null;
		try {
			workbook = new XSSFWorkbook(excelFile);
			DataFormatter dataFormatter = new DataFormatter();

			for (Sheet sheet : workbook) {

				int i = 0;
				for (Row row : sheet) {

					if ((i == 0) && header) {
						i++;
						continue;
					}

					StringBuilder rowValue = new StringBuilder();

					for (Cell cell : row) {

						String cellValue = dataFormatter.formatCellValue(cell);

						if (cellValue == null) {
							cellValue = "";
						}
						rowValue.append(cellValue).append(";");
					}

					Siniestro siniestro = new Siniestro(rowValue.toString());
					siniestros.add(siniestro);
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

		return siniestros;
	}

}

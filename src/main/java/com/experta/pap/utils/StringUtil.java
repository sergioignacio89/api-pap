package com.experta.pap.utils;

import java.text.Normalizer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.experta.pap.enumerators.DateFormatEnum;
import com.experta.pap.enumerators.DefaultValuesEnum;
import com.experta.pap.exceptions.InputTypeException;

public class StringUtil {

	public static String purifyAccident(String data) {
		data = stripAccents(data);
		data = change—toN(data);
		data = avoidLineBreak(data);
		return data;
	}

	private static String stripAccents(String data) {
		data = Normalizer.normalize(data, Normalizer.Form.NFD);
		data = data.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
		return data;
	}

	public static String change—toN(String data) {
		data = data.replaceAll("Ò", "n");
		return data;
	}

	public static String avoidLineBreak(String data) {
		data = data.replaceAll("\r\n", "");
		return data;
	}
	public static boolean isNumber(String s) {
		try {
			Float.parseFloat(s);
		} catch (NumberFormatException e) {
			return false;
		} catch (NullPointerException e) {
			return false;
		}
		return true;
	}

	public static String convertToInteger(String number) throws InputTypeException {

		number = number.trim();
		
		try {
			int i = (int) Float.parseFloat(number);
			number = String.valueOf(i);
		} catch (Exception e) {
			throw new InputTypeException("Error parsing number: " + number);
		}
		return number;
	}

	public static String parseToDate(String strDate) {

		strDate = strDate.trim();
		if (strDate.equals("-") || strDate.equals("")) {
			strDate = DefaultValuesEnum.date.getValue();
		} else {

			boolean b = false;

			for (DateFormatEnum format : DateFormatEnum.values()) {

				SimpleDateFormat parseFrom = new SimpleDateFormat(format.getFormat());
				SimpleDateFormat parseTo = new SimpleDateFormat(DateFormatEnum.yyyy_MM_dd.getFormat());

				try {
					Date date = parseFrom.parse(strDate);
					strDate = parseTo.format(date);
					b = true;
					break;
				} catch (ParseException e) {
					System.out.println(
							"ERROR: date not parsed with format : " + format.getFormat() + ": " + e.getMessage());
					continue;
				}
			}
			if (!b) {
				strDate = DefaultValuesEnum.date.getValue();
			}
		}
		return strDate;
	}

}

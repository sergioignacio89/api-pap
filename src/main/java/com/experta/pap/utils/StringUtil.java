package com.experta.pap.utils;

import java.text.Normalizer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.experta.pap.enumerators.DateFormatEnum;
import com.experta.pap.enumerators.DefaultValuesEnum;

public class StringUtil {

	public static String purifyAccident(String data) {
		data = stripAccents(data);
		data = change—toN(data);
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

	public static boolean isInteger(String s) {
		try {
			Integer.parseInt(s);
		} catch (NumberFormatException e) {
			return false;
		} catch (NullPointerException e) {
			return false;
		}
		return true;
	}

	public static String purifyInteger(String number) {

		number = number.trim();
		if (isInteger(number)) {
			int i = Integer.parseInt(number);
			number = String.valueOf(i);
		} else {
			number = DefaultValuesEnum.number.getValue();
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

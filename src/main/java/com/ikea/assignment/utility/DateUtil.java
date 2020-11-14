package com.ikea.assignment.utility;

import com.ikea.assignment.constant.WarehouseConstants;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class DateUtil {

	/**
	 * This method convert String date Time to LocalDateTime
	 * @param stringDateTime
	 * @return dateTime
	 */
	public static LocalDateTime convertStringToLocalDateTime(String stringDateTime){
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(WarehouseConstants.DATETIME_FORMAT);
		return LocalDateTime.parse(stringDateTime, formatter);
	}
}

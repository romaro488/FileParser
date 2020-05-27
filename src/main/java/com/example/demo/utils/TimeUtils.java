package com.example.demo.utils;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import static com.example.demo.utils.Constants.NEW_YOK_ZONE_ID;

public class TimeUtils {


	public static Optional<ZonedDateTime> formatDateTime(String time) {
		try {
			DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyMMddHHmmss")
					.withZone(NEW_YOK_ZONE_ID);
			ZonedDateTime dateTime = ZonedDateTime.parse(time, dateTimeFormatter);
			return Optional.of(dateTime);
		} catch (Exception e) {
			return Optional.empty();
		}
	}
}


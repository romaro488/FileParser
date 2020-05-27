package com.example.demo.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

public class TimeUtils {

	public static Optional<Date> formatDateTime(String time) {
		SimpleDateFormat dateTimeFormatter = new SimpleDateFormat("yyMMddHHmmss");

		try {
			Date parse = dateTimeFormatter.parse(time);

			return Optional.of(parse);
		} catch (Exception e) {
			return Optional.empty();
		}
	}
}


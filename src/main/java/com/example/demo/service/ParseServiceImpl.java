package com.example.demo.service;

import com.example.demo.utils.TimeUtils;
import com.example.demo.utils.Tuple;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static com.example.demo.utils.Constants.TAG_ENDS_AT;
import static com.example.demo.utils.Constants.TAG_STARTS_AT;
import static com.example.demo.utils.Constants.TIMESTAMP_ENDS_AT;
import static com.example.demo.utils.Constants.TIMESTAMP_STARTS_AT;

@Service
@RequiredArgsConstructor
public class ParseServiceImpl implements ParseService<List<Tuple<String, Date>>> {
	private InputStream stream;
	private UserService userService;

	@Override
	public void setSource(InputStream inputStream) {
		this.stream = inputStream;
	}

	public List<Tuple<String, ZonedDateTime>> findChampions() {

		if (stream == null) {
			throw new IllegalArgumentException("source has to be set");
		}
		List<Tuple<String, ZonedDateTime>> participants = new LinkedList<>();
		try (Stream<String> lines = new BufferedReader(new InputStreamReader(stream)).lines()) {
			lines.forEach(line -> {
				parseInput(line).ifPresent(participants::add);
			});
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return participants;
	}

	public Optional<Tuple<String, ZonedDateTime>> parseInput(String input) {
		String userId = input.substring(TAG_STARTS_AT, TAG_ENDS_AT);
		String time = input.substring(TIMESTAMP_STARTS_AT, TIMESTAMP_ENDS_AT);

		Optional<ZonedDateTime> date = TimeUtils.formatDateTime(time);

		return date.map(e -> {
			Tuple<String, ZonedDateTime> tuple = new Tuple<>();
			tuple.first = userId;
			tuple.second = e;

			return tuple;
		});
	}
}

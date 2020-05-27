package com.example.demo.service;

import com.example.demo.utils.Tuple;

import java.io.InputStream;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;


public interface ParseService<T> {

	void setSource(InputStream source);

	List<Tuple<String, ZonedDateTime>> findChampions();

	Optional<Tuple<String, ZonedDateTime>> parseInput(String input);
}


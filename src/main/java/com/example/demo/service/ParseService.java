package com.example.demo.service;

import com.example.demo.utils.Tuple;

import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.Optional;


public interface ParseService<T> {

	void setSource(InputStream source);

	List<Tuple<String, Date>> findChampions();

	Optional<Tuple<String, Date>> parseInput(String input);
}


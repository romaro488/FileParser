package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ParseController {
	private final UserService userService;

	public ParseController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping(path = "/findAll")
	public List<User> findUsers() {
		return userService.getSortedUsers(10);
	}
}

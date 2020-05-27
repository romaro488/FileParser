package com.example.demo.service;

import com.example.demo.enums.Sort;
import com.example.demo.model.User;

import java.util.List;

public interface UserService {

	List<User> getSortedUsers(int limit);

	List<User> sortUsers(int limit, Sort sortBy);
}

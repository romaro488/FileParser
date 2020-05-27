package com.example.demo;

import com.example.demo.model.User;
import com.example.demo.service.UserServiceImpl;
import com.example.demo.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class DemoApplication {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("app-context.xml");

		UserService service = context.getBean("userServiceImpl", UserServiceImpl.class);

		List<User> users = service.getSortedUsers(10);
		for (User user : users) {
			long differences = user.getEndDate().getTime() - user.getStartDate().getTime();

			System.out.println(user + ", time differences: " + differences);
		}
	}
}
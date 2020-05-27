package com.example.demo.service;

import com.example.demo.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/app-context.xml")
public class UserServiceTest implements ApplicationContextAware {
	private ApplicationContext context;

	List<User> expectedUsers = List.of(
			new User("058001c6df07", new Date(1471071695000L), new Date(1471074474000L)),
			new User("05800221804b", new Date(1471071694000L), new Date(1471074598000L)),
			new User("05800220eba8", new Date(1471071695000L), new Date(1471074748000L)),
			new User("0580022197f2", new Date(1471071695000L), new Date(1471074903000L)));


	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		context = applicationContext;
	}

	@Test
	public void test_should_sort_users_and_return_result() {
		UserService service = context.getBean("userServiceImpl", UserServiceImpl.class);
		List<User> users = service.getSortedUsers(4);

		Assert.assertEquals(expectedUsers, users);
	}
}

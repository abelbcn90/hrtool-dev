package com.wedonegood;

import org.junit.Test;
import org.junit.runner.RunWith;
import com.wedonegood.login.model.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HrtoolLoginApplicationTests {

	@Autowired
	UserService userService;

	@Test
	public void contextLoads() {
	}

	@Test
	public void testPwd() {
		//userService.changePassword(-1L, "1");
	}

}

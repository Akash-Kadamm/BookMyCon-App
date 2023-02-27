package com.bookmycon.controller;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import com.bookmycon.model.Login;
import com.bookmycon.model.User;
import com.bookmycon.service.LoginService;
import com.bookmycon.utils.ResponseMessage;

@RunWith(MockitoJUnitRunner.class)
public  class LoginControllerTest {
	
	@Mock
	LoginService loginService;

	@InjectMocks
	LoginController loginController;
	
	Login login;
	
	Map<String, Object> response = new HashMap<>();
	
	 User user;
	
	 
	@Before
	public void setUp() {
		setData();
		setMock();
	}
	
	private void setMock() {
		when(loginService.loginCheckByEmailAndPassword(login)).thenReturn(response);
	}
	
	private void setData() {
		user=new User(1, "Akash Kadam", "akashkad@cybage.com", "ak@123", "user", "7038967693");
		 login= new Login("akashkad@cybage.com", "ak@123");
		 
	}
	
	
	@Test
	public void testLoginCheckByPassword_ConditionReturnUser() {
		response.put("user", user);
		ResponseEntity<Object> actual=loginController.loginCheckByPassword(login);
		assertEquals( response, actual.getBody());	
		
	}

	@Test
	public void testLoginCheckByPassword_ConditionReturnInvalidAndPasswordMessage() {
		 login= new Login("akashkadcybage.com", "ak@12");
		response.put("message", ResponseMessage.INVALID_EMAIL_AND_PASSWORD.getMessage());
		ResponseEntity<Object> actual=loginController.loginCheckByPassword(login);
		assertEquals( response.get("message"), actual.getBody());	
		
	}
}

package com.bookmycon.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import com.bookmycon.model.Login;
import com.bookmycon.model.User;
import com.bookmycon.repository.LoginRepository;
import com.bookmycon.utils.ResponseMessage;

@RunWith(MockitoJUnitRunner.class)
public class LoginServiceTest {
   
	@Mock
	LoginRepository loginRepo ;
	
	@InjectMocks
	LoginService loginService;
	
	public static final String EMAIL="akashkad@cybage.com";
	
	User user;
	
	@Before
	public void setUp() {
		 user=new User(1, "Akash Kadam", "akashkad@cybage.com", "ak@123", "user", "7038967693");
	}

	@Test
	public void testUserByEmail_conditionGetUserObject() {
		
		Mockito.when(loginRepo.findByUserEmail(EMAIL)).thenReturn(user);
		assertEquals(user,loginService.userByEmail(EMAIL));
	}
	
	

	@Test
	public void testUserByEmail_conditionNotFoundUserObjectIsNull() {
		when(loginRepo.findByUserEmail(anyString())).thenReturn(null);
	assertEquals(null,loginService.userByEmail(EMAIL));
	}
	
	
	@Test
	public void testIsUserExists_ConditionNullReturn() {
		when(loginRepo.findByUserEmail(anyString())).thenReturn(null);
		assertEquals(true, loginService.isUserExists(EMAIL));
		
	}
	
	@Test
	public void testIsUserExists_ConditionUserObjectReturn() {
		when(loginRepo.findByUserEmail(EMAIL)).thenReturn(user);
		assertEquals(false, loginService.isUserExists(EMAIL));
		
	}
	
	@Test
	public void testloginCheckByEmailAndPassword_ConditionPasswordMattached() {
		when(loginRepo.findByUserEmail(EMAIL)).thenReturn(user);
		Map<String, Object> actual = loginService.loginCheckByEmailAndPassword(new Login(EMAIL, "ak@123"));
		assertEquals( user,actual.get("user"));
	}
	
	@Test
	public void testloginCheckByEmailAndPassword_ConditionPasswordNotMattached() {
		when(loginRepo.findByUserEmail(EMAIL)).thenReturn(user);
		Map<String, Object> actual = loginService.loginCheckByEmailAndPassword(new Login(EMAIL, "ak@1234"));
		assertEquals(ResponseMessage.WRONG_PASSWORD.getMessage(),actual.get("message"));
		
	}
	
	@Test
	public void testloginCheckByEmailAndPassword_ConditionUserNotFound() {
		when(loginRepo.findByUserEmail("ak@cybage.com")).thenReturn(null);
		Map<String, Object> actual = loginService.loginCheckByEmailAndPassword(new Login("ak@cybage.com", "ak@1234"));
		assertEquals( ResponseMessage.USER_NOT_FOUND.getMessage(),actual.get("message"));
		
	}

}

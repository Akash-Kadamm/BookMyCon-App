package com.bookmycon.controller;



import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import com.bookmycon.model.User;
import com.bookmycon.service.LoginService;
import com.bookmycon.service.UserService;
import com.bookmycon.utils.ResponseMessage;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {
	
	@Mock
	UserService userService;
	
	@Mock
	LoginService loginService;
	
	@InjectMocks
	UserController userController;

	Map<String, Object> response=new HashMap<>();
	
	User user;
	
	@Before
	public void setUp() {
		setData();
		setMock();
	}
	
	private void setData() {
		user=new User(1, "Akash Kadam", "akashkad@cybage.com", "ak@123", "user", "7038967693");
	}
	
	
	
	private void setMock() {
		when(userService.userRegistration(user)).thenReturn(response);
		when(userService.updateUserProfile(user)).thenReturn(response);
	}

	@Test
	public void testRegisterUser_ConditionUserRegisterSuccessfully() {
		when(loginService.isUserExists(user.getUserEmail())).thenReturn(true);
		response.put("message", ResponseMessage.USER_ADDED_SUCCESSFULLY.getMessage());
		ResponseEntity<Object> actual=userController.registerUser(user);
		assertEquals(response, actual.getBody());
		
	}
	
	
	@Test
	public void testRegisterUser_ConditionUserRegisterFailed() {
		when(userService.userRegistration(user)).thenReturn(null);
		when(loginService.isUserExists(anyString())).thenReturn(true);
		response.put("message", ResponseMessage.USER_ADDED_FAILED.getMessage());
		ResponseEntity<Object> actual=userController.registerUser(user);
		assertEquals(response, actual.getBody());
		
	}
	
	@Test
	public void testRegisterUser_ConditionUserAlreadyExists() {
		when(loginService.isUserExists(anyString())).thenReturn(false);
		response.put("message", ResponseMessage.USER_ALREADY_EXISTS_WITH_THIS_EMAIL.getMessage());
		ResponseEntity<Object> actual=userController.registerUser(user);
		assertEquals(response, actual.getBody());
		
	}

	
	@Test
	public void testRegisterUser_ConditionUserInvalidEmailAndPassword() {
		user=new User(1, "Akash Kadam", "akashkadcybage.com", "ak@12", "user", "7038967693");
		response.put("message", ResponseMessage.INVALID_EMAIL_AND_PASSWORD.getMessage());
		ResponseEntity<Object> actual=userController.registerUser(user);
		assertEquals(response, actual.getBody());
		
	}
	
	
	
	@Test
	public void testRegisterUser_ConditionUserUserIsNull() {
		response.put("message", ResponseMessage.USER_ADDED_FAILED.getMessage());
		ResponseEntity<Object> actual=userController.registerUser(null);
		assertEquals(response, actual.getBody());
		
	}
	
	
	@Test
	public void testUpdateProfile_ConditionUserUpdateSuccessfully() {
		response.put("message",ResponseMessage.USER_UPDATED_SUCCESSFULLY.getMessage());
		ResponseEntity<Object> actual=userController.updateProfile(user);
		assertEquals(response, actual.getBody());	
	}
	
	@Test
	public void testUpdateProfile_ConditionGettingUserNull() {
		response.put("message",ResponseMessage.USER_FAILED_TO_UPDATE_PROFILE.getMessage());
		ResponseEntity<Object> actual=userController.updateProfile(null);
		assertEquals(response, actual.getBody());	
	}
	
	

	@Test
    public	void testGetAllUser() {
		List<User> list=new ArrayList<>();
		list.add(user);
		when(userService.showAllUser()).thenReturn(list);
		ResponseEntity<List<User>> actual=userController.getAllUser();
		assertEquals(list, actual.getBody());	
	}

}

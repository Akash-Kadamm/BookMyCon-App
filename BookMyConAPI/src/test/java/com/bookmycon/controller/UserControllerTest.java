package com.bookmycon.controller;



import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bookmycon.dto.HousekeepingDto;
import com.bookmycon.dto.UserRequestDTO;
import com.bookmycon.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.bookmycon.model.User;
import com.bookmycon.service.LoginService;
import com.bookmycon.service.UserService;
import com.bookmycon.utils.ResponseMessage;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

	@Mock
	UserService userService;

	@Mock
	LoginService loginService;
	@Mock
	RestTemplate restTemplate;

	@InjectMocks
	UserController userController;

	@Mock
	UserRepository userRepository;

	Map<String, Object> response = new HashMap<>();

	User user;
	UserRequestDTO userRequestDTO;
	MultipartFile thumbnail = null;

	HousekeepingDto housekeepingDto;

	@Before
	public void setUp() {
		setData();
		setMock();
	}

	private void setData() {
		user = new User(1, "Akash Kadam", "akashkad@cybage.com", "ak@123", "user", "7038967693", "e3da01b141884ca0a4d01568551bdf2f");
		userRequestDTO = new UserRequestDTO(1, "Akash Kadam", "akashkad@cybage.com", "ak@123", "user", "7038967693", thumbnail);
	}


	private void setMock() {
		when(userService.userRegistration(user, null)).thenReturn(response);
		when(userService.updateUserProfile(user)).thenReturn(response);
		when(userRepository.save(user)).thenReturn(user);
	}

	@Test
	public void testRegisterUser_ConditionUserRegisterSuccessfully() {
		when(loginService.isUserExists(user.getUserEmail())).thenReturn(true);
		response.put("message", ResponseMessage.USER_ADDED_SUCCESSFULLY.getMessage());
		ResponseEntity<Object> actual = userController.registerUser(userRequestDTO);
		assertEquals(response, actual.getBody());

	}


	@Test
	public void testRegisterUser_ConditionUserRegisterFailed() {
		when(userService.userRegistration(user, thumbnail)).thenReturn(null);
		when(loginService.isUserExists(anyString())).thenReturn(true);
		response.put("message", ResponseMessage.USER_ADDED_FAILED.getMessage());
		ResponseEntity<Object> actual = userController.registerUser(userRequestDTO);
		assertEquals(response, actual.getBody());

	}

	@Test
	public void testRegisterUser_ConditionUserAlreadyExists() {
		when(loginService.isUserExists(anyString())).thenReturn(false);
		response.put("message", ResponseMessage.USER_ALREADY_EXISTS_WITH_THIS_EMAIL.getMessage());
		ResponseEntity<Object> actual = userController.registerUser(userRequestDTO);
		assertEquals(response, actual.getBody());

	}


	@Test
	public void testRegisterUser_ConditionUserInvalidEmailAndPassword() {
		user = new User(1, "Akash Kadam", "akashkadcybage.com", "ak@12", "user", "7038967693", "e3da01b141884ca0a4d01568551bdf2f");
		response.put("message", ResponseMessage.INVALID_EMAIL_AND_PASSWORD.getMessage());
		userRequestDTO.setUserEmail("akashkadcybage.com");
		ResponseEntity<Object> actual = userController.registerUser(userRequestDTO);
		assertEquals(response, actual.getBody());

	}


	@Test
	public void testRegisterUser_ConditionUserUserIsNull() {
		response.put("message", ResponseMessage.USER_ADDED_FAILED.getMessage());
		ResponseEntity<Object> actual = userController.registerUser(null);
		assertEquals(response, actual.getBody());

	}


	@Test
	public void testUpdateProfile_ConditionUserUpdateSuccessfully() {
		response.put("message", ResponseMessage.USER_UPDATED_SUCCESSFULLY.getMessage());
		ResponseEntity<Object> actual = userController.updateProfile(user);
		assertEquals(response, actual.getBody());
	}

	@Test
	public void testUpdateProfile_ConditionGettingUserNull() {
		response.put("message", ResponseMessage.USER_FAILED_TO_UPDATE_PROFILE.getMessage());
		ResponseEntity<Object> actual = userController.updateProfile(null);
		assertEquals(response, actual.getBody());
	}

	@Test
	public void testGetAllUser() {
		List<User> list = new ArrayList<>();
		list.add(user);
		when(userService.showAllUser()).thenReturn(list);
		ResponseEntity<List<User>> actual = userController.getAllUser();
		assertEquals(list, actual.getBody());
	}

	@Test
	public void testGetUserByEmailId() {
		when(userService.findByUserEmail(anyString())).thenReturn(user);
		ResponseEntity<?> actualResult = userController.getUserByEmailId(user.getUserEmail());
		assertEquals(HttpStatus.OK, actualResult.getStatusCode());
	}

	@Test
	public void testGetAllUsers() {
		when(userService.findAllUsers()).thenReturn(List.of(user));
		ResponseEntity<?> actualResult = userController.getAllUsers();
		assertEquals(HttpStatus.OK, actualResult.getStatusCode());
	}



	@Test
	public void testAddHousekeepingRequest() {
		housekeepingDto = new HousekeepingDto(1L, "audi1", "Ac and floor");
		when(restTemplate.postForEntity(anyString(), any(Object.class), any())).thenReturn(new ResponseEntity<>(housekeepingDto, HttpStatus.OK));
		Object actualResult = userController.addHousekeepingRequest(housekeepingDto);
		assertEquals(ResponseEntity.class, actualResult.getClass());
	}

}

package com.bookmycon.service;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.bookmycon.repository.LoginRepository;
import com.bookmycon.utils.StorageService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import com.bookmycon.model.User;
import com.bookmycon.repository.UserRepository;
import com.bookmycon.utils.ResponseMessage;
import org.springframework.web.multipart.MultipartFile;


@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

	@Mock
	UserRepository userRepo;

	@InjectMocks
	UserService userService;

	@Mock
	LoginRepository loginRepository;

	User user;
	@Mock
	StorageService storageService;

	@Before
	public void setUp() {
		user = new User(1, "Akash Kadam", "akashkadcybage.com", "ak@12", "user", "7038967693", "e3da01b141884ca0a4d01568551bdf2f");

	}


	@Test
	public void testUserRegistration_conditionReturnUserObject() {
//		String thumbnail1 = "e3da01b141884ca0a4d01568551bdf2f";
		MultipartFile thumbnail2 = null;
		when(userRepo.save(user)).thenReturn(user);
		when(storageService.store(thumbnail2)).thenReturn("Debasis.jpg");
		user.setThumbnail("Debasis.jpg");
		Map<String, Object> response = userService.userRegistration(user, thumbnail2);
		assertEquals(user, response.get("user"));
	}

	@Test
	public void testUserRegistration_ConditionException() {
		when(userRepo.save(null)).thenReturn(new Exception());
		Map<String, Object> response = userService.userRegistration(null, null);
		assertEquals(ResponseMessage.USER_ADDED_FAILED.getMessage(), response.get("message"));

	}

	@Test
	public void testUpdateUserProfile_ConditionUpdateed() {
		when(userRepo.findByUserId(user.getUserId())).thenReturn(user);
		System.out.println(user);
		when(userRepo.save(user)).thenReturn(user);
		Map<String, Object> actual = userService.updateUserProfile(user);
		assertEquals(user, actual.get("user"));
	}

	@Test
	public void testUpdateUserProfile_ConditionFailedToUpdat() {
		when(userRepo.findByUserId(user.getUserId())).thenReturn(user);
		when(userRepo.save(user)).thenReturn(null);
		Map<String, Object> actual = userService.updateUserProfile(user);
		assertEquals(ResponseMessage.USER_FAILED_TO_UPDATE_PROFILE.getMessage(), actual.get("message"));
	}

	@Test
	public void testShowAllUser_ConditionReturnUserList() {
		List<User> userList = new ArrayList<>();
		User user2 = new User(2, "Admin", "admin@cybage.com", "admin@123", "admin", "7038967694", "e3da01b141884ca0a4d01568551bdf2f");
		userList.add(user);
		userList.add(user2);
		when(userRepo.findAll()).thenReturn(userList);
		List<User> actual = userService.showAllUser();
		assertEquals(userList, actual);

	}

	@Test
	public void testFindByUserEmail() {
		String email="akashkad@cybage.com";
		when(loginRepository.findByUserEmail(ArgumentMatchers.anyString())).thenReturn(user);
		User actualUser=userService.findByUserEmail(email);
		assertEquals(user.getUserEmail(),actualUser.getUserEmail());

	}

	@Test
	public void testFindAllUsers() {
		when(userRepo.findAll()).thenReturn(List.of(user));
		List<User> actualUsers=userService.findAllUsers();
		assertEquals(1,actualUsers.size());
	}

	
	
}

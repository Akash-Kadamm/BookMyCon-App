package com.ioffice.service;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import com.ioffice.model.User;
import com.ioffice.repository.UserRepository;
import com.ioffice.utils.ResponseMessage;


@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
	UserRepository userRepo;
    
    @InjectMocks
    UserService userService;
    
    User user;
    
 
	
	@Before
	public void setUp() {
		 user=new User(1, "Akash Kadam", "akashkad@cybage.com", "ak@123", "user", "7038967693");
		}

	
	@Test
	public  void testUserRegistration_conditionReturnUserObject() {
		when(userRepo.save(user)).thenReturn(user);
		Map<String, Object> response=userService.userRegistration(user);
		assertEquals(user,response.get("user"));
	}

	
	@Test
	public void testUpdateUserProfile_ConditionUpdateed() {
		when(userRepo.findByUserId(user.getUserId())).thenReturn(user);
		System.out.println(user);
		when(userRepo.save(user)).thenReturn(user);
		Map<String, Object> actual=userService.updateUserProfile(user);
		assertEquals(user,actual.get("user"));	
	}
	
	@Test
	public void testUpdateUserProfile_ConditionFailedToUpdat() {
		when(userRepo.findByUserId(user.getUserId())).thenReturn(user);
		when(userRepo.save(user)).thenReturn(null);
		Map<String, Object> actual=userService.updateUserProfile(user);
		assertEquals(ResponseMessage.USER_FAILED_TO_UPDATE_PROFILE.getMessage(),actual.get("message"));	
	}
	
	@Test
	public void testShowAllUser() {
		List<User> userList=new ArrayList<>();
		User user2=new User(2, "Admin", "admin@cybage.com", "admin@123", "admin", "7038967694");
		userList.add(user);
		userList.add(user2);
		when(userRepo.findAll()).thenReturn(userList);
		List<User> actual=userService.showAllUser();
		assertEquals( userList,actual);
		
	}
}

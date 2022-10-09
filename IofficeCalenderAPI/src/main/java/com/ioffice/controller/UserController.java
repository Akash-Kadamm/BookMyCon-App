package com.ioffice.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ioffice.model.User;
import com.ioffice.service.LoginService;
import com.ioffice.service.UserService;
import com.ioffice.utils.ResponseMessage;

@CrossOrigin("*")
@RestController
@RequestMapping("user")
public class UserController {
	
	@Autowired
	private UserService userService;

	@Autowired
	private LoginService loginService;
	
	Logger logger=Logger.getLogger(UserController.class);
	
	
	
	
	/*
	 * Register new user
	 * 
	 * check-upcoming user object , pattern check , is user already exists
	 * 
	 * @param User
	 * @return Response entity contain object and httpStatus code
	 *  
	 * */
	@PostMapping("registration")
	public ResponseEntity<Object> registerUser(@RequestBody User user){
		
		Map<String, Object> response=new HashMap<>();
		String emailPattern="^[^@ ]+@[^@ ]+\\.[^@ .]{2,}$";
		String passwordPattern=".{6}.*";
		
		if(user!=null) {
			logger.debug("User  object is not null ");
			if( Pattern.matches(emailPattern,user.getUserEmail()) 
				&& Pattern.matches(passwordPattern,user.getUserPassword())){
				logger.debug("Pattern Matched.");
				
				if(loginService.isUserExists(user.getUserEmail())
						) {
					logger.debug("Check email is already exists.");
					 response=userService.userRegistration(user);
					 
					 if(response!=null) {
						    logger.info("user object: "+response);
							response.put("message", ResponseMessage.USER_ADDED_SUCCESSFULLY.getMessage());
							return new ResponseEntity<>(response, HttpStatus.OK);
						}else {
							logger.error("User failed to register.");
							response = new HashMap<>();
							response.put("message",ResponseMessage.USER_ADDED_FAILED.getMessage());
							return new ResponseEntity<>(response, HttpStatus.OK);
						}
				}else {
					logger.error("User already exists with this email"+user.getUserEmail());
					response.put("message", ResponseMessage.USER_ALREADY_EXISTS_WITH_THIS_EMAIL.getMessage());
					return new ResponseEntity<>(response, HttpStatus.OK);
				}
			}else {
				logger.error("Patterns are not matched ");
				response.put("message", ResponseMessage.INVALID_EMAIL_AND_PASSWORD.getMessage());
				return new ResponseEntity<>(response, HttpStatus.OK);
			}
		}else {
			logger.error("Upcoming user object is getting null");
			response.put("message",ResponseMessage.USER_ADDED_FAILED.getMessage());	
		}
		return new ResponseEntity<>(response, HttpStatus.OK);

		
	}
	
	
	
	
	/*
	 * Update User Profile
	 * 
	 * @param User
	 * @return updated User object
	 * 
	 * */
	@PostMapping("updateProfile")
	public ResponseEntity<Object> updateProfile(@RequestBody User user){
		Map<String, Object> response=new HashMap<>();
				
		if(user!=null) {
			logger.debug("User object is not nulll");
			logger.info("user object: "+user);
			response=userService.updateUserProfile(user);
			response.put("message", ResponseMessage.USER_UPDATED_SUCCESSFULLY.getMessage());
		}else {
			logger.error("Getting user object null");
			response.put("message", ResponseMessage.USER_FAILED_TO_UPDATE_PROFILE.getMessage());
		}
		return new ResponseEntity<Object>(response,HttpStatus.OK);
	}
	
	
	

	
	
	
	/*
	 * Retrieves all users which are register  
	 * 
	 * @param 
	 * @return List of users
	 * 
	 * */
	@GetMapping("allUser")
	public ResponseEntity<List<User>>  getAllUser(){
		logger.debug("getting all users.");
		return  new ResponseEntity<List<User>>(userService.showAllUser(),HttpStatus.OK);		
			
	}
	
	
	
	
}

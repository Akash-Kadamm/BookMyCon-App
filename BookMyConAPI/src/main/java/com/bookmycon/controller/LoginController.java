package com.bookmycon.controller;

import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookmycon.model.Login;
import com.bookmycon.service.LoginService;
import com.bookmycon.utils.ResponseMessage;


@CrossOrigin("*")
@RestController
@RequestMapping("login")
public class LoginController {

	
	@Autowired
	LoginService loginService;       
	
    Logger logger=Logger.getLogger(LoginController.class);
	
    
    
	/*
	 * Login according to role of user
	 * 
	 * @param Login object contain user credentials
	 * @return User object
	 * 
	 * */
	@PostMapping("loginCheck")
	public ResponseEntity<Object> loginCheckByPassword(@RequestBody Login login){
	
		String emailPattern="^[^@ ]+@[^@ ]+\\.[^@ .]{2,}$";
		String passwordPattern=".{6}.*";
		
		if(Pattern.matches(emailPattern,login.getEmail()) 
				&& Pattern.matches( passwordPattern,login.getPassword())) {
			logger.debug("Pattern Matched.");
			return new ResponseEntity<>(loginService.loginCheckByEmailAndPassword(login),HttpStatus.OK);	
		}
		
		logger.error("Pattern not matched Invalid email and password");
		return new ResponseEntity<>(ResponseMessage.INVALID_EMAIL_AND_PASSWORD.getMessage(),HttpStatus.OK);
			
	}
	
	
	
}

package com.bookmycon.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookmycon.model.Login;
import com.bookmycon.model.User;
import com.bookmycon.repository.LoginRepository;
import com.bookmycon.utils.ResponseMessage;

@Service
public class LoginService {

	@Autowired
	LoginRepository loginRepo;
	
	Logger logger=Logger.getLogger(LoginService.class);
	
	
	
	/*
	 * Retrieve User object  by Email
	 * 
	 * @param email
	 * @return User object
	 * 
	 * */
	public User userByEmail(String email) {
		if (loginRepo.findByUserEmail(email) == null) {
			logger.error("User not found");
			return null;
		} else {
			logger.debug("Retrieve User Object ");
			return loginRepo.findByUserEmail(email);
		}
	}
	
	
	
	/*
	 * Validate Login according to role of user
	 * 
	 * @param Login object contain user credentials
	 * @return User object
	 * 
	 * */
	public Map<String, Object> loginCheckByEmailAndPassword(Login login)  {
		Map<String, Object> response = new HashMap<>();
        logger.info("Login creditials: "+login);
		
		String email=login.getEmail();
		String password=login.getPassword();
		
	    User user=loginRepo.findByUserEmail(email);
	    logger.info("User found: "+user);
	    
	    if(user!=null) {
	    	if(user.getUserPassword().equals(password)) {
	    		logger.debug("Password is matched");
	    		 response.put("user", user);
	    		 return response;
	    	}else {
	    		logger.error("Password is not matched");
	    		response.put("message", ResponseMessage.WRONG_PASSWORD.getMessage());
	    		return response;
	    	}
	    }else {
	    	logger.error("User is not found");
	    	response.put("message", ResponseMessage.USER_NOT_FOUND.getMessage());
	    	return response;
	    }
	}
	
	
	
	/*
	 * Check is user already exists with Email.
	 * 
	 * @param String  user email
	 * @return boolean 
	 * 
	 * */
	public boolean isUserExists(String email) {
		if (loginRepo.findByUserEmail(email) == null) {
			logger.debug("User Not found");
			return true;
		} else {
			logger.debug("User Found");
			return false;
		}
	}
	
}

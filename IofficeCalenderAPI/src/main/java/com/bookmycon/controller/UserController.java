package com.bookmycon.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import com.bookmycon.utils.GuestPass;
import com.bookmycon.utils.PdfGenerator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookmycon.model.User;
import com.bookmycon.service.LoginService;
import com.bookmycon.service.UserService;
import com.bookmycon.utils.ResponseMessage;

import javax.servlet.http.HttpServletResponse;

@CrossOrigin("*")
@RestController
@RequestMapping("user")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private LoginService loginService;

	Logger logger = Logger.getLogger(UserController.class);

	/*
	 * Register new user
	 * 
	 * check-upcoming user object , pattern check , is user already exists
	 * 
	 * @param User
	 * 
	 * @return Response entity contain object and httpStatus code
	 * 
	 */
	@PostMapping("/registration")
	public ResponseEntity<Object> registerUser(@RequestBody User user) {

		Map<String, Object> response = new HashMap<>();
		String emailPattern = "^[^@ ]+@[^@ ]+\\.[^@ .]{2,}$";
		String passwordPattern = ".{6}.*";

		if (user != null) {
			logger.debug("User  object is not null ");
			if (Pattern.matches(emailPattern, user.getUserEmail())
					&& Pattern.matches(passwordPattern, user.getUserPassword())) {
				logger.debug("Pattern Matched.");

				if (loginService.isUserExists(user.getUserEmail())) {
					logger.debug("Check email is already exists.");
					user.setUserRole("user");
					response = userService.userRegistration(user);

					if (response != null) {
						logger.info("user object: " + response);

						response.put("message", ResponseMessage.USER_ADDED_SUCCESSFULLY.getMessage());
						return new ResponseEntity<>(response, HttpStatus.OK);
					} else {
						logger.error("User failed to register.");
						response = new HashMap<>();
						response.put("message", ResponseMessage.USER_ADDED_FAILED.getMessage());
						return new ResponseEntity<>(response, HttpStatus.OK);
					}
				} else {
					logger.error("User already exists with this email" + user.getUserEmail());
					response.put("message", ResponseMessage.USER_ALREADY_EXISTS_WITH_THIS_EMAIL.getMessage());
					return new ResponseEntity<>(response, HttpStatus.OK);
				}
			} else {
				logger.error("Patterns are not matched ");
				response.put("message", ResponseMessage.INVALID_EMAIL_AND_PASSWORD.getMessage());
				return new ResponseEntity<>(response, HttpStatus.OK);
			}
		} else {
			logger.error("Upcoming user object is getting null");
			response.put("message", ResponseMessage.USER_ADDED_FAILED.getMessage());
		}
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	/*
	 * Update User Profile
	 * 
	 * @param User
	 * 
	 * @return updated User object
	 *
	 */
	@PostMapping("updateProfile")
	public ResponseEntity<Object> updateProfile(@RequestBody User user) {
		Map<String, Object> response = new HashMap<>();

		if (user != null) {
			logger.debug("User object is not nulll");
			logger.info("user object: " + user);
			response = userService.updateUserProfile(user);
			response.put("message", ResponseMessage.USER_UPDATED_SUCCESSFULLY.getMessage());
		} else {
			logger.error("Getting user object null");
			response.put("message", ResponseMessage.USER_FAILED_TO_UPDATE_PROFILE.getMessage());
		}
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}

	/*
	 * Retrieves all users which are register
	 * 
	 * @param
	 * 
	 * @return List of users
	 * 
	 */
	@GetMapping("allUser")
	public ResponseEntity<List<User>> getAllUser() {
		logger.debug("getting all users.");

		return new ResponseEntity<List<User>>(userService.showAllUser().stream()
				.filter(user -> user.getUserRole().equals("user")).collect(Collectors.toList()), HttpStatus.OK);

	}

	@GetMapping("/get-user-by-email/{email}")
	public ResponseEntity<?> getUserByEmailId(@PathVariable String email) {
		return new ResponseEntity<>(userService.findByUserEmail(email), HttpStatus.OK);
	}

	@GetMapping("/allUsers")
	public ResponseEntity<List<User>> getAllUsers() {
		List<User> list =userService.findAllUsers();
		return new ResponseEntity<List<User>>(list, HttpStatus.OK);
	}

	@GetMapping("/export-to-pdf")
	public void generatePdfFile(HttpServletResponse response) throws IOException
	{
		response.setContentType("application/pdf");
		DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD:HH:MM:SS");
		String currentDateTime = dateFormat.format(new Date());
		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=Report Generation " + currentDateTime + ".pdf";
		response.setHeader(headerKey, headerValue);
		List < User > listOfUsers = userService.findAllUsers();
		PdfGenerator generator = new PdfGenerator();
//		System.out.println(response.getHeader("Content-Disposition"));
		generator.generate(listOfUsers, response);
	}

	@GetMapping("/export-to-pass/{email}")
	public void generatePassFile(HttpServletResponse response , @PathVariable String email) throws IOException
	{
		response.setContentType("application/pdf");
		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=Pass Generation " + ".pdf";
		response.setHeader(headerKey, headerValue);

		User userDetails=userService.findByUserEmail(email);
		System.out.println(userDetails);
		GuestPass guestPass = new GuestPass();
		guestPass.generatePass(userDetails, response);
	}
}

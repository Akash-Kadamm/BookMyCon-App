package com.bookmycon.controller;

import com.bookmycon.model.Guest;
import com.bookmycon.model.User;
import com.bookmycon.service.GuestService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/guest")
@CrossOrigin(origins = "http://localhost:3000")


public class GuestController {
	private static final Logger logger = LoggerFactory.getLogger(GuestController.class);
	@Autowired
	private GuestService guestService;

	@Autowired
	private ObjectMapper objectMapper;

	@GetMapping("/allGuest")
	public ResponseEntity<List<Guest>> getAllGuest() {
		return new ResponseEntity<>(guestService.findAllGuests(), HttpStatus.OK);
	}






	@PostMapping("/addGuest")
	public ResponseEntity<?> addGuest(
			@RequestParam("guestName") String guestName,
			@RequestParam("guestCompany") String guestCompany,
			@RequestParam("guestMobileNo") String guestMobileNo,
			@RequestParam("guestEmail") String guestEmail,
			@RequestParam("thumbnail") MultipartFile thumbnail,
			@RequestParam("users") String usersJson) {

		try {
			logger.info("Received request to add guest with name: {}", guestName);

			// Convert usersJson string to a User object
			User user = objectMapper.readValue(usersJson, User.class);

			// Create and set up the Guest object
			Guest guest = new Guest();
			guest.setGuestName(guestName);
			guest.setGuestCompany(guestCompany);
			guest.setGuestMobileNo(guestMobileNo);
			guest.setGuestEmail(guestEmail);
			guest.setUsers(user);


			// Save the guest
			guestService.save(guest, thumbnail);

			logger.info("Guest added successfully: {}", guestName);
			return new ResponseEntity<>("Guest added successfully", HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error adding guest", e);
			return new ResponseEntity<>("Failed to add guest: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}




	@GetMapping("/allGuest/{userId}")
	public ResponseEntity<List<Guest>> getAllGuest(@PathVariable int userId) {
		List<Guest> guest = guestService.getAllGuestByUserId(userId);
		return new ResponseEntity<>(guest, HttpStatus.OK);
	}







	@GetMapping("/export-to-pass/{name}")
	public void generatePassFile(HttpServletResponse response, @PathVariable String name) throws IOException {
		response.setContentType("application/pdf");
		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=Guest_Pass_" + name + ".pdf";
		response.setHeader(headerKey, headerValue);

//		Guest guestDetails = guestService.findByGuestName(name);
//		GuestPass guestPass = new GuestPass();
//		guestPass.generatePass(guestDetails, response);
	}

	@DeleteMapping("/delete-guest/{id}")
	public ResponseEntity<String> deleteGuest(@PathVariable int id) {
		guestService.deleteByGuestId(id);
		return new ResponseEntity<>("Guest deleted", HttpStatus.OK);
	}

	@GetMapping("/get-guest-by-name/{name}")
	public ResponseEntity<?> getGuestByName(@PathVariable String name) {
		return new ResponseEntity<>(guestService.findByGuestName(name), HttpStatus.OK);
	}
}

package com.bookmycon.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import com.bookmycon.dto.GuestRequestDTO;
import com.bookmycon.model.Guest;
import com.bookmycon.model.User;
import com.bookmycon.repository.UserRepository;
import com.bookmycon.service.GuestService;
import com.bookmycon.utils.GuestPass;
import com.bookmycon.utils.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/guest")
public class GuestController {
	
	@Autowired
	private GuestService guestService;

	@Autowired
	private UserRepository userRepository;

	
	@GetMapping("/allGuest")
	public ResponseEntity<List<Guest>> getAllGuest() {
		return new ResponseEntity<List<Guest>>(guestService.findAllGuests(), HttpStatus.OK);
	}

	@GetMapping("/allGuest/{userId}")
	public ResponseEntity<List<Guest>> getAllGuest(@PathVariable int userId) {
		User user = userRepository.findByUserId(userId);
		List<Guest> guest = guestService.getAllGuestByUserId(user.getUserId());
		return new ResponseEntity<List<Guest>>(guest, HttpStatus.OK);
	}

	@GetMapping("/export-to-pass/{name}")
	public void generatePassFile(HttpServletResponse response, @PathVariable String name) throws IOException
	{
		response.setContentType("application/pdf");
		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=Guest Pass Generation " + ".pdf";
		response.setHeader(headerKey, headerValue);
		Guest guestDetails=guestService.findByGuestName(name);
		System.out.println(guestDetails);
		GuestPass guestPass = new GuestPass();
		guestPass.generatePass(guestDetails, response);
	}

	@DeleteMapping("/delete-guest/{id}")
	public ResponseEntity<String> deleteGuest(@PathVariable int id){
		guestService.deleteByGuestId(id);
		return new ResponseEntity<>("Guest deleted",HttpStatus.OK);
	}

	@GetMapping("/get-guest-by-name/{name}")
	public ResponseEntity<?> getGuestByName(@PathVariable String name) {
		return new ResponseEntity<>(guestService.findByGuestName(name), HttpStatus.OK);
	}
}

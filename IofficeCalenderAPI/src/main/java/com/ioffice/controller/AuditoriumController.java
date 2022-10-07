package com.ioffice.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ioffice.model.Auditoriums;
import com.ioffice.model.Booking;
import com.ioffice.repository.AuditoriumRepository;
import com.ioffice.repository.BookingRepository;
import com.ioffice.service.AuditoriumService;
import com.ioffice.service.BookingService;
import com.ioffice.utils.ResponseMessage;

@CrossOrigin("*")
@RestController
@RequestMapping("/admin")
public class AuditoriumController {
	@Autowired
	private AuditoriumService auditoriumService;
	@Autowired
	private BookingService bookingService;
	@Autowired
	private AuditoriumRepository auditoriumRepository;
	@Autowired
	private BookingRepository bookingRepository;
	
	Logger logger=Logger.getLogger(AuditoriumController.class);

	@PostMapping("/addAudi")
	public ResponseEntity<Object> addAuditorium(@RequestBody Auditoriums auditorium) {
		auditoriumService.addAuditorium(auditorium);
		return new ResponseEntity<Object>(ResponseMessage.AUDITORIUM_ADDED.getMessage(),HttpStatus.CREATED);

	}
	
	@GetMapping("/getAll")
	public ResponseEntity<List<Auditoriums>> getAllAuditoriums(){
		return new ResponseEntity<List<Auditoriums>>(auditoriumService.showAll(), HttpStatus.OK);
	}
	
	
	
	
	/*
	 * Update Auditorium
	 * 
	 * @param Auditorium Id- Path Variable and Auditorium object
	 * @return Updated Auditorium Object
	 *  
	 * 
	 * */
	@PutMapping("/{id}")
	public ResponseEntity<Object> updateAuditorium(@PathVariable int id, @RequestBody Auditoriums auditorium) {
	    logger.info("Auditorium id:"+id+" Auditorium object: "+auditorium);
		logger.debug("Calling the Update Auditorium service method");
		auditoriumService.updateAuditorium(id, auditorium);
		return new ResponseEntity<Object>(ResponseMessage.AUDITORIUM_UPDATED.getMessage(), HttpStatus.OK);
	}
	
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteAuditorium(@PathVariable int id) {
		
		System.out.println(id);
		Auditoriums auditoriumObj= (Auditoriums)auditoriumRepository.getById(id);
		
		List<Booking> listBooking= bookingRepository.findByAduitoriamId(auditoriumObj);
		
	//	Booking objBooking= listBooking.get(0);
		for  (Booking booking : listBooking) {
			booking.setAduitoriamId(null);
			bookingService.addBooking(booking);
		}
	
	
		auditoriumService.deleteById(id);
		
		return new ResponseEntity<String>("record deleted", HttpStatus.OK);
	}
	
	@GetMapping("/getAudi/{id}")
	public ResponseEntity<Object> getAuditoriumByID(@PathVariable int id) {
		Map<String, Object> response=new HashMap<>();
		response=auditoriumService.getAuditoriumById(id);
		response.put("message", ResponseMessage.GETTING_AUDITORIUM.getMessage());
		return new ResponseEntity<Object>(response,HttpStatus.OK);
	}
	

}

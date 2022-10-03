package com.ioffice.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.ioffice.model.Booking;
import com.ioffice.service.BookingService;


@RestController
@RequestMapping("/admins")
public class BookingController {

	@Autowired
	BookingService bookingService;
	
	
	@GetMapping("/getAllBookings")
	public ResponseEntity<List<Booking>> getAllBookings(){
		return new ResponseEntity<List<Booking>>(bookingService.showAll(), HttpStatus.OK);
	}
	
	
	@PostMapping("/addBooking")
	public ResponseEntity<String> addBooking(@RequestBody Booking booking){	
	System.out.println(booking);
		

		bookingService.addBooking(booking);
		System.out.println(booking);
	
		return new ResponseEntity<String>("record added successfully", HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Optional<Booking>> getBookingById(@PathVariable(value = "id") int id){
		Optional<Booking> booking=bookingService.getBookingById(id);
		
	return new ResponseEntity<Optional<Booking>>(booking,HttpStatus.OK);
	}
	
	

	@PutMapping("/{id}")
	public ResponseEntity<String> editBooking(@PathVariable(value = "id") int id, @RequestBody Booking booking){
	
		bookingService.editBooking(id, booking);
	return new ResponseEntity<String>("record updated",HttpStatus.OK);
	}
	
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteBookingById(@PathVariable int id){
	bookingService.deleteBooking(id);
		return new ResponseEntity<String>("record deleted",HttpStatus.OK);
	}
}

package com.ioffice.controller;

import java.awt.print.Book;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

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

import com.ioffice.dto.BookingDTO;
import com.ioffice.model.Booking;
import com.ioffice.repository.AuditoriumRepository;
import com.ioffice.repository.UserRepository;
import com.ioffice.service.AuditoriumService;
import com.ioffice.service.BookingService;
import com.ioffice.service.UserService;

@CrossOrigin("*")
@RestController
@RequestMapping("/admins")
public class BookingController {

	@Autowired
	BookingService bookingService;
	@Autowired
	private AuditoriumRepository auditoriumService;
	@Autowired
	private UserRepository userService;
	@GetMapping("/getAllBookings")
	public ResponseEntity<List<Booking>> getAllBookings() {
		return new ResponseEntity<List<Booking>>(bookingService.showAll(), HttpStatus.OK);
	}

	@PostMapping("/addBooking")
	public ResponseEntity<String> addBooking(@RequestBody Booking booking) {
		System.out.println(booking);
		bookingService.addBooking(booking);
		System.out.println(booking);
		return new ResponseEntity<String>("record added successfully", HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Optional<Booking>> getBookingById(@PathVariable(value = "id") int id) {
		Optional<Booking> booking = bookingService.getBookingById(id);
		return new ResponseEntity<Optional<Booking>>(booking, HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<String> editBooking(@PathVariable(value = "id") int id, @RequestBody Booking booking) {
		bookingService.editBooking(id, booking);
		return new ResponseEntity<String>("record updated", HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteBookingById(@PathVariable int id) {
		bookingService.deleteBooking(id);
		return new ResponseEntity<String>("record deleted", HttpStatus.OK);
	}
	
	@GetMapping("/get-all-bookings/{userId}")
	public ResponseEntity<Stream<BookingDTO>> getAllBookingsOfUser(@PathVariable int userId){
		return new ResponseEntity<Stream<BookingDTO>>(bookingService.getBookingByUserId(userId).stream().map(element->BookingDTO.entityToDto(element)), HttpStatus.OK);
	}
	
	@GetMapping("/get-all-bookings")
	public ResponseEntity<Stream<BookingDTO>> findAllBookings(){
		Booking book = null;
		book = new Booking(0, auditoriumService.findById(1).get() , userService.findById(2).get(), LocalDate.of(2022, 10, 16), LocalDate.of(2022, 10, 18), 
				LocalTime.of(8, 00), LocalTime.of(9, 00), "Project Meeting");
//		book = new Booking(0, auditoriumService.findById(1).get() , userService.findById(2).get(), LocalDate.of(2022, 10, 3), LocalDate.of(2022, 10, 3), 
//				LocalTime.of(7, 00), LocalTime.of(13, 00), "meeting");
//		book = new Booking(0, auditoriumService.findById(1).get() , userService.findById(2).get(),  LocalDate.of(2022, 10, 25), LocalDate.of(2022, 10, 29), 
//				LocalTime.of(10, 00), LocalTime.of(11, 00), "party");
//		bookingService.addBooking(book);
		
		
		return new ResponseEntity<Stream<BookingDTO>>(bookingService.showAll().stream().map(element->BookingDTO.entityToDto(element)), HttpStatus.OK);
	}
}

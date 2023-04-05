package com.bookmycon.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import com.bookmycon.dto.BookingsDTO;
import com.bookmycon.model.Auditoriums;
import com.bookmycon.repository.UserRepository;
import com.bookmycon.service.AuditoriumService;
import com.bookmycon.utils.PdfOfBooking;
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

import com.bookmycon.model.Booking;
import com.bookmycon.service.BookingService;
import com.bookmycon.dto.BookingDTO;

import javax.servlet.http.HttpServletResponse;

@CrossOrigin("*")
@RestController
@RequestMapping("/admins")
public class BookingController {

	@Autowired
	BookingService bookingService;
	@Autowired
	UserRepository userRepository;

	@Autowired
	AuditoriumService auditoriumService;
	@GetMapping("/getAllBookings")
	public ResponseEntity<List<Booking>> getAllBookings() {
		return new ResponseEntity<List<Booking>>(bookingService.showAll(), HttpStatus.OK);
	}

	@PostMapping("/addBooking")
	public ResponseEntity<Booking> addBooking(@RequestBody Booking booking) {

		return new ResponseEntity<Booking>(bookingService.addBooking(booking), HttpStatus.CREATED);
	}

	@PostMapping("/addBookings")
	public ResponseEntity<Booking> addBookings(@RequestBody BookingsDTO bookingsDTO) {


		System.out.println(bookingsDTO);
		Booking booking=new Booking();
		booking.setBookingId(bookingsDTO.getBookingId());
		booking.setAduitoriamId(bookingsDTO.getAduitoriamId());
		booking.setUserId(bookingsDTO.getUserId());
		booking.setBookingAgenda(bookingsDTO.getBookingAgenda());
		booking.setBookingDateFrom(bookingsDTO.getBookingDateFrom());
		booking.setBookingDateTo(bookingsDTO.getBookingDateTo());



		LocalTime time;

		if(bookingsDTO.getBookingTimeFrom().equals("9"))
		{
			time= LocalTime.parse("09:00:00");
			booking.setBookingTimeFrom(time);
		}
		else if (bookingsDTO.getBookingTimeFrom().equals("10"))
		{
			time= LocalTime.parse("10:00:00");
			booking.setBookingTimeFrom(time);
		}
		else if (bookingsDTO.getBookingTimeFrom().equals("11"))
		{
			time= LocalTime.parse("11:00:00");
			booking.setBookingTimeFrom(time);
		}
		else if (bookingsDTO.getBookingTimeFrom().equals("12"))
		{
			time= LocalTime.parse("12:00:00");
			booking.setBookingTimeFrom(time);
		}
		else if (bookingsDTO.getBookingTimeFrom().equals("13"))
		{
			time= LocalTime.parse("13:00:00");
			booking.setBookingTimeFrom(time);
		}
		else if (bookingsDTO.getBookingTimeFrom().equals("14"))
		{
			time= LocalTime.parse("14:00:00");
			booking.setBookingTimeFrom(time);
		}
		else if (bookingsDTO.getBookingTimeFrom().equals("15"))
		{
			time= LocalTime.parse("15:00:00");
			booking.setBookingTimeFrom(time);
		}
		else if (bookingsDTO.getBookingTimeFrom().equals("16"))
		{
			time= LocalTime.parse("16:00:00");
			booking.setBookingTimeFrom(time);
		}
		else if (bookingsDTO.getBookingTimeFrom().equals("17"))
		{
			time= LocalTime.parse("17:00:00");
			booking.setBookingTimeFrom(time);
		}
		else if (bookingsDTO.getBookingTimeFrom().equals("18"))
		{
			time= LocalTime.parse("18:00:00");
			booking.setBookingTimeFrom(time);
		}
		else
		{

			time= LocalTime.parse("00:00:00");
			booking.setBookingTimeFrom(time);
		}



		if(bookingsDTO.getBookingTimeTO().equals("9"))
		{
			time= LocalTime.parse("09:00:00");
			booking.setBookingTimeTO(time);
		}
		else if (bookingsDTO.getBookingTimeTO().equals("10"))
		{
			time= LocalTime.parse("10:00:00");
			booking.setBookingTimeTO(time);
		}
		else if (bookingsDTO.getBookingTimeTO().equals("11"))
		{
			time= LocalTime.parse("11:00:00");
			booking.setBookingTimeTO(time);
		}
		else if (bookingsDTO.getBookingTimeTO().equals("12"))
		{
			time= LocalTime.parse("12:00:00");
			booking.setBookingTimeTO(time);
		}
		else if (bookingsDTO.getBookingTimeTO().equals("13"))
		{
			time= LocalTime.parse("13:00:00");
			booking.setBookingTimeTO(time);
		}
		else if (bookingsDTO.getBookingTimeTO().equals("14"))
		{
			time= LocalTime.parse("14:00:00");
			booking.setBookingTimeTO(time);
		}
		else if (bookingsDTO.getBookingTimeTO().equals("15"))
		{
			time= LocalTime.parse("15:00:00");
			booking.setBookingTimeTO(time);
		}
		else if (bookingsDTO.getBookingTimeTO().equals("16"))
		{
			time= LocalTime.parse("16:00:00");
			booking.setBookingTimeTO(time);
		}
		else if (bookingsDTO.getBookingTimeTO().equals("17"))
		{
			time= LocalTime.parse("17:00:00");
			booking.setBookingTimeTO(time);
		}
		else if (bookingsDTO.getBookingTimeTO().equals("18"))
		{
			time= LocalTime.parse("18:00:00");
			booking.setBookingTimeTO(time);
		}
		else
		{

			time= LocalTime.parse("00:00:00");
			booking.setBookingTimeTO(time);
		}



		return new ResponseEntity<Booking>(bookingService.addBooking(booking), HttpStatus.CREATED);
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

	@GetMapping("/searchBooking/{dateFrom}/{dateTo}")
	public ResponseEntity<List<Booking>> searchBooking(@PathVariable String dateFrom,
			@PathVariable String dateTo) {
		
		//DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-mm-dd");
		 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		 String str1 = dateFrom; 
	        String[] arrOfStr1 = str1.split("-", 5); 
	  
	       String newdateFrom=arrOfStr1[2]+"/"+arrOfStr1[1]+"/"+arrOfStr1[0];
		 
		 
	       String str2 = dateTo; 
	        String[] arrOfStr2 = str2.split("-", 5); 
	  
	       String newdateTo=arrOfStr2[2]+"/"+arrOfStr2[1]+"/"+arrOfStr2[0];
		 
	       LocalDate from=LocalDate.parse(newdateFrom, formatter);
			LocalDate to=LocalDate.parse(newdateTo, formatter);
	       
//		LocalDate from=LocalDate.parse(dateFrom, formatter);
//		LocalDate to=LocalDate.parse(dateTo, formatter);
		return new ResponseEntity<List<Booking>>(bookingService.getByDateFromDateTo(from, to), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteBookingById(@PathVariable int id) {
		bookingService.deleteBooking(id);
		return new ResponseEntity<String>("Booking Deleted", HttpStatus.OK);
	}
	
	
	@GetMapping("getAllBookings/{userId}")
	public ResponseEntity<List<Booking>> getAllBookingsOfUser(@PathVariable int userId){
		return new ResponseEntity<List<Booking>>(bookingService.getBookingByUserId(userId),HttpStatus.OK);
	}
	
	@GetMapping("/get-all-bookings/{userId}")
	public ResponseEntity<Stream<BookingDTO>> getAlllBookingsOfUser(@PathVariable int userId){
		return new ResponseEntity<Stream<BookingDTO>>(bookingService.getBookingByUserId(userId).stream().map(element->BookingDTO.entityToDto(element)), HttpStatus.OK);
	}
	
	@GetMapping("/get-all-bookings")
	public ResponseEntity<Stream<BookingDTO>> findAllBookings(){		
		return new ResponseEntity<Stream<BookingDTO>>(bookingService.showAll().stream().map(element->BookingDTO.entityToDto(element)), HttpStatus.OK);
	}

	@GetMapping("/export-to-pdf-book")
	public void generatePdfFileForBooking(HttpServletResponse response) throws IOException
	{
//		response.setContentType("application/pdf");
//		DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD:HH:MM:SS");
//		String currentDateTime = dateFormat.format(new Date());
//		String headerKey = "Content-Disposition";
//		String headerValue = "attachment; filename=Booking Report Generation " + currentDateTime + ".pdf";
//		response.setHeader(headerKey, headerValue);
		List <Booking> bookingList = bookingService.showAll();
		PdfOfBooking generator = new PdfOfBooking();
		System.out.println(bookingList);
		generator.generateBooking(bookingList, response);
	}



	@GetMapping("/AuditoriumBookingByAuditoriumNameAndDate/{auditoriumName}/{dateFrom}")
	public  ResponseEntity<List<Booking>> getAllBookingByAuditoriamsNameAndDate(@PathVariable String auditoriumName, @PathVariable String dateFrom)
	{
		Auditoriums audi=auditoriumService.findByAuditoriumByName(auditoriumName).get(0);
		return new ResponseEntity<List<Booking>>(bookingService.getAllBookingByAuditoriumNameAndDate(audi,  LocalDate.parse(dateFrom)), HttpStatus.OK);

	}


}

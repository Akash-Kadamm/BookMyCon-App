package com.bookmycon.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;
import com.bookmycon.repository.UserRepository;
import com.bookmycon.utils.PdfOfBooking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.bookmycon.model.Booking;
import com.bookmycon.service.BookingService;
import com.bookmycon.dto.BookingDTO;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/admins")
@CrossOrigin(origins = "http://localhost:3000")
public class BookingController {

    @Autowired
    BookingService bookingService;

    @GetMapping("/getAllBookings")
    public ResponseEntity<List<Booking>> getAllBookings() {
        return new ResponseEntity<List<Booking>>(bookingService.showAll(), HttpStatus.OK);
    }


    @PostMapping("/addBooking")
    public ResponseEntity<Booking> addBooking(@RequestBody Booking booking) {
        return new ResponseEntity<Booking>(bookingService.addBooking(booking), HttpStatus.CREATED);
    }

    @PostMapping("")

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

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String str1 = dateFrom;
        String[] arrOfStr1 = str1.split("-", 5);

        String newdateFrom=arrOfStr1[2]+"/"+arrOfStr1[1]+"/"+arrOfStr1[0];


        String str2 = dateTo;
        String[] arrOfStr2 = str2.split("-", 5);

        String newdateTo=arrOfStr2[2]+"/"+arrOfStr2[1]+"/"+arrOfStr2[0];

        LocalDate from=LocalDate.parse(newdateFrom, formatter);
        LocalDate to=LocalDate.parse(newdateTo, formatter);

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
    @GetMapping("/booked-auditoriums/count")
    public long getBookedAuditoriumCount(
            @RequestParam(value = "endDate",required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            @RequestParam(value = "endTime",required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime endTime) {
        return bookingService.getBookedAuditoriumCount(endDate, endTime);
    }

//  @GetMapping("/get-all-bookings/{userId}")
//  public ResponseEntity<Stream<BookingDTO>> getAlllBookingsOfUser(@PathVariable int userId){
//      return new ResponseEntity<Stream<BookingDTO>>(bookingService.getBookingByUserId(userId).stream().map(element->BookingDTO.entityToDto(element)), HttpStatus.OK);
//  }
//
//  @GetMapping("/get-all-bookings")
//  public ResponseEntity<Stream<BookingDTO>> findAllBookings(){
//      return new ResponseEntity<Stream<BookingDTO>>(bookingService.showAll().stream().map(element->BookingDTO.entityToDto(element)), HttpStatus.OK);
//  }
//
//  @GetMapping("/export-to-pdf-book")
//  public void generatePdfFileForBooking(HttpServletResponse response) throws IOException
//  {
//      List <Booking> bookingList = bookingService.showAll();
//      PdfOfBooking generator = new PdfOfBooking();
//      System.out.println(bookingList);
//      generator.generateBooking(bookingList, response);
//  }
}
package com.bookmycon.controller;

import com.bookmycon.model.Booking;
import com.bookmycon.model.BookingRequest;
import com.bookmycon.service.DemoBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/booking")
public class DemoBooking {
    @Autowired
    private DemoBookingService bookingService;

    @PostMapping("/book")
    public ResponseEntity<Booking> bookAuditorium(@RequestBody BookingRequest bookingRequest) {
        Booking booking = bookingService.bookAuditorium(bookingRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(booking);
    }
}

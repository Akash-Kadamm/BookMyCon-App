package com.bookmycon.controller;

import com.bookmycon.model.Booking;
import com.bookmycon.repository.BookingRepository;
import com.bookmycon.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/bookings")
public class BookingController {

    @Autowired  BookingRepository bookingRepository;
    @Autowired
    private BookingService bookingService;

    private Map<String, Boolean> bookings = new HashMap<>();
    @GetMapping
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }
    @PutMapping("/{id}")
    public Booking updateBooking(@PathVariable("id") int bookingId, @RequestBody Booking booking) {
        return bookingService.updateBooking(bookingId, booking);
    }

//    @PostMapping
//    public Booking bookConferenceHall(@RequestBody Booking booking) {
//        return bookingRepository.save(booking);
//    }
//
//    @DeleteMapping("/{id}")
//    public void cancelBooking(@PathVariable int bookingId) {
//        bookingRepository.deleteById(bookingId);
//    }
//    @PostMapping("/book/{type}")
//    public void book(@PathVariable String type) {
//        if (bookings.containsKey(type)) {
//            bookings.put(type, false);
//        }
//    }
//
//    @PostMapping("/cancel/{type}")
//    public void cancel(@PathVariable String type) {
//        if (bookings.containsKey(type)) {
//            bookings.put(type, true);
//        }
//    }
}

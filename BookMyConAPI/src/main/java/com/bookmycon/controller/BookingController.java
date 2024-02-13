package com.bookmycon.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api")
public class BookingController {

    private Map<String, Boolean> bookings = new HashMap<>();

    public BookingController() {

        bookings.put("conferenceHall", true);
        bookings.put("cabin", true);
        bookings.put("cafeteria", true);
        bookings.put("playZone", true);
    }

    @GetMapping("/bookings")
    public Map<String, Boolean> getBookings() {
        return bookings;
    }

    @PostMapping("/book/{type}")
    public void book(@PathVariable String type) {
        if (bookings.containsKey(type)) {
            bookings.put(type, false);
        }
    }

    @PostMapping("/cancel/{type}")
    public void cancel(@PathVariable String type) {
        if (bookings.containsKey(type)) {
            bookings.put(type, true);
        }
    }

}

package com.bookmycon.controller;

import com.bookmycon.service.AuditoriumService;
import com.bookmycon.service.BookingService;
import com.bookmycon.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@CrossOrigin("*")
public class AdminController {

    @Autowired
    public UserService userService;

    @Autowired
    public AuditoriumService auditoriumService;

    @Autowired
    public BookingService bookingService;

    @GetMapping("/userCount")
    public ResponseEntity<?> findUserCount(){
        return new ResponseEntity<>(userService.findAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/audiCount")
    public ResponseEntity<?> findaudiCount() {
        return new ResponseEntity<>(auditoriumService.showAll(), HttpStatus.OK);
    }

    @GetMapping("/bookingCount")
    public ResponseEntity<?> findbookingCount() {
        return new ResponseEntity<>(bookingService.showAll(), HttpStatus.OK);
    }
}

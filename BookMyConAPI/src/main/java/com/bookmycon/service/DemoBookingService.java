package com.bookmycon.service;


import com.bookmycon.model.Auditoriums;
import com.bookmycon.model.Booking;
import com.bookmycon.model.BookingRequest;
import com.bookmycon.model.User;
import com.bookmycon.repository.AuditoriumRepository;
import com.bookmycon.repository.BookingRepository;
import com.bookmycon.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.time.LocalDate;
import java.time.LocalTime;

@Service
public class DemoBookingService {
    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private AuditoriumRepository auditoriumsRepository;

    @Autowired
    private UserRepository userRepository;

    public Booking bookAuditorium(BookingRequest bookingRequest) {
        Auditoriums auditorium = auditoriumsRepository.findById(bookingRequest.getAuditoriumId())
                .orElseThrow(() -> new NotFoundException("Auditorium not found"));

        User user = userRepository.findById(bookingRequest.getUserId())
                .orElseThrow(() -> new NotFoundException("User not found"));

        Booking booking = new Booking();
        booking.setAduitoriamId(auditorium);
        booking.setUserId(user);
        booking.setBookingDateFrom(LocalDate.now());
        booking.setBookingDateTo(LocalDate.now());
        booking.setBookingTimeFrom(LocalTime.now());
        booking.setBookingTimeTO(LocalTime.now());
        booking.setBookingAgenda(bookingRequest.getBookingAgenda());

        return bookingRepository.save(booking);
    }
}

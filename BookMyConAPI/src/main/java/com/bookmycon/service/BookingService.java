package com.bookmycon.service;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookmycon.model.Auditoriums;
import com.bookmycon.model.Booking;

import com.bookmycon.repository.BookingRepository;
import com.bookmycon.repository.UserRepository;
import org.apache.log4j.Logger;

@Service
public class BookingService {

	@Autowired
	BookingRepository bookingRepository;
	@Autowired
	UserRepository userRepository;

	Logger logger=Logger.getLogger(BookingService.class);

	public List<com.bookmycon.model.Booking> showAll() {

		logger.info("Retrieving all bookings");
		return bookingRepository.findAll();
	}

	public Booking addBooking(Booking booking) {
		logger.info("Adding new booking: " + booking.toString());
		return 	bookingRepository.save(booking);

	}

	public Optional<Booking> getBookingById(int bookingId) {

		logger.info("Retrieving booking by ID: " + bookingId);
		return bookingRepository.findById(bookingId);
	}

	public void deleteBooking(int id) {

		logger.info("Deleting booking with ID: " + id);
		bookingRepository.deleteById(id);
	}

	public void editBooking(int id, Booking booking) {
		logger.info("Updating booking with ID: " + id + ", new booking: " + booking.toString());
		bookingRepository.save(booking);
	}

	public List<Booking> getByDateFromDateTo(LocalDate dateFrom, LocalDate dateTo) {
		logger.info("Retrieving bookings between dates: " + dateFrom + " and " + dateTo);
		return bookingRepository.findByBookingDateFromAndBookingDateTo(dateFrom, dateTo);
	}

//	public List<Booking> getByAuditoriumId(Auditoriums auditoriums) {
//		logger.info("Retrieving bookings for auditorium: " + auditoriums.toString());
//		return bookingRepository.findByAduitoriamId(auditoriums);
//	}

	
	public List<Booking> getBookingByUserId(int userId) {
		logger.info("Retrieving bookings for user with ID: " + userId);
		return bookingRepository.findByUserId(userRepository.findById(userId).get());	
	}

	
	public List<Booking> getAllBookingOfUser(int userId){
		logger.info("Retrieving all bookings for user with ID: " + userId);
		return bookingRepository.findByUserId(userId);
	}
}

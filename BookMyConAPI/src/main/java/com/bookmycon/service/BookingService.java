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

@Service
public class BookingService {

	@Autowired
	BookingRepository bookingRepository;
	@Autowired
	UserRepository userRepository;

	public List<com.bookmycon.model.Booking> showAll() {
		return bookingRepository.findAll();
	}

	public Booking addBooking(Booking booking) {

		return 	bookingRepository.save(booking);

	}

	public Optional<Booking> getBookingById(int bookingId) {
		return bookingRepository.findById(bookingId);
	}

	public void deleteBooking(int id) {
		bookingRepository.deleteById(id);
	}

	public void editBooking(int id, Booking booking) {
		bookingRepository.save(booking);
	}

	public List<Booking> getByDateFromDateTo(LocalDate dateFrom, LocalDate dateTo) {
		return bookingRepository.findByBookingDateFromAndBookingDateTo(dateFrom, dateTo);
	}

	public List<Booking> getByAuditoriumId(Auditoriums auditoriums) {
		return bookingRepository.findByAduitoriamId(auditoriums);
	}

	
	public List<Booking> getBookingByUserId(int userId) {
		return bookingRepository.findByUserId(userRepository.findById(userId).get());	
	}

	
	public List<Booking> getAllBookingOfUser(int userId){   
		return bookingRepository.findByUserId(userId);
	}




	public List<Booking> getAllBookingByAuditoriumNameAndDate(Auditoriums auditoriums, LocalDate dateFrom){
		return bookingRepository.findByAduitoriamIdAndBookingDateFrom(auditoriums,dateFrom);
	}
}

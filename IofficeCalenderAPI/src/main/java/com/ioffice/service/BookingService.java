package com.ioffice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ioffice.dto.BookingDTO;
import com.ioffice.model.Auditoriums;
import com.ioffice.model.Booking;
import com.ioffice.repository.BookingRepository;
import com.ioffice.repository.UserRepository;

@Service
public class BookingService {

	@Autowired
	private BookingRepository bookingRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	public List<Booking> showAll() {
		return bookingRepository.findAll();
	}

    public void addBooking(Booking booking) {
		System.out.println(booking);
		bookingRepository.save(booking);
	}

	public Optional<Booking> getBookingById(int  bookingId) {
		return bookingRepository.findById(bookingId);
	}

	public void deleteBooking(int id) {
		bookingRepository.deleteById(id);
	}

	public void editBooking(int id,Booking booking) {
		bookingRepository.save(booking);	
	}
	
	public List<Booking> getByAuditoriumId(Auditoriums auditoriums  ) {
		return bookingRepository.findByAduitoriamId(auditoriums);	
	}
	
	public List<Booking> getBookingByUserId(int userId) {
		return bookingRepository.findByUserId(userRepository.findById(userId).get());	
	}

}

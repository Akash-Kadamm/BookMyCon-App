package com.ioffice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ioffice.model.Booking;
import com.ioffice.repository.BookingRepository;

@Service
public class BookingService {

	@Autowired
	BookingRepository bookingRepository;
	public List<com.ioffice.model.Booking> showAll() {
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

}

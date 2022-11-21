package com.ioffice.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.ioffice.model.Auditoriums;
import com.ioffice.model.Booking;
import com.ioffice.model.User;
import com.ioffice.repository.BookingRepository;

@RunWith(MockitoJUnitRunner.class)
public class BookingServiceTest {

	@Mock
	BookingRepository bookingRepo;
	
	@InjectMocks
	BookingService bookingService;
	
	List<Booking> bookings;
	
	@Before
	public void setUp() {
		setData();
		setMock();
		
	}
	
	private void setData() {
		bookings=Arrays.asList(new Booking(),new Booking());
	}
	
	private void setMock() {
		when(bookingRepo.findByUserId(anyInt())).thenReturn(bookings);
	}
	
	
	
	@org.junit.Test
	public void testGetAllBookingOfUser() {
		List<Booking> actual=bookingService.getAllBookingOfUser(1);
		assertEquals(bookings,actual);
	}
	
}

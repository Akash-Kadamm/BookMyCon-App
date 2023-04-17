package com.bookmycon.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.bookmycon.model.User;
import com.bookmycon.repository.UserRepository;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import com.bookmycon.model.Booking;
import com.bookmycon.repository.BookingRepository;

@RunWith(MockitoJUnitRunner.class)
public class BookingServiceTest {
	@Mock
	BookingRepository bookingRepo;
	
	@InjectMocks
	BookingService bookingService;

	@Mock
	UserRepository userRepository;
	
	List<Booking> bookings;
	Booking booking;
	User user;



	@Before
	public void setUp() {
		setData();
		setMock();
	}
	
	private void setData() {
		booking = new Booking();
		user=new User(1, "Akash Kadam", "akashkadcybage.com", "ak@12", "user", "7038967693","e3da01b141884ca0a4d01568551bdf2f");

	}
	
	private void setMock() {
		//when(bookingRepo.findAll()).thenReturn(bookings);
	}

	@Test
	public void testGetAllBookingOfUser() {
		List<Booking> expectedBookings = new ArrayList<>();
		when(bookingRepo.findByUserId(1)).thenReturn(expectedBookings);
		List<Booking> actualBookings = bookingService.getAllBookingOfUser(1);
		assertEquals(expectedBookings, actualBookings);
		verify(bookingRepo, times(1)).findByUserId(1);
	}

	@Test
	public void testAddBooking() {
		Booking booking = new Booking();
		booking.setBookingId(1);
		booking.setBookingAgenda("Test Booking");
		Booking savedBooking = new Booking();
		savedBooking.setBookingId(1);
		savedBooking.setBookingAgenda("Test Booking");
		when(bookingRepo.save(booking)).thenReturn(savedBooking);
		Booking result = bookingService.addBooking(booking);
		verify(bookingRepo, times(1)).save(booking);
		assertNotNull(result);
		assertEquals(savedBooking.getBookingId(), result.getBookingId());
		assertEquals(savedBooking.getBookingAgenda(), result.getBookingAgenda());
	}

	@Test
	public void testGetBookingById() {
		Booking booking1 = new Booking();
		booking1.setBookingId(1);
		booking1.setBookingAgenda("Test Area");
		when(bookingRepo.findById(ArgumentMatchers.anyInt())).thenReturn(Optional.of(booking1));
		Optional<Booking> foundBooking = bookingService.getBookingById(1);
		assertEquals(booking1.getBookingAgenda(), foundBooking.get().getBookingAgenda());
	}

	@Test
	public void testDeleteBooking() {
 	    int bookingId = 1;
		bookingService.deleteBooking(bookingId);
		verify(bookingRepo, times(1)).deleteById(bookingId);
	}

	@Test
	public void testEditBooking() {
		int bookingId = 1;
		bookingService.editBooking(bookingId,booking);
		verify(bookingRepo, times(1)).save(any(Booking.class));
	}
	@Test
	public void testGetByDateFromDateTo() {
		LocalDate dateFrom= LocalDate.of(2020, 1, 8); ;
		LocalDate dateTo=LocalDate.of(2020, 1, 9);;
		when(bookingRepo.findByBookingDateFromAndBookingDateTo(dateFrom,dateTo)).thenReturn(List.of(booking));
		List<Booking> actual=bookingService.getByDateFromDateTo(dateFrom,dateTo);
		assertEquals(1,actual.size());
	}

	@Test
	public void testGetBookingByUserId() {
		int userId = 1;
		when(bookingRepo.findByUserId(any(User.class))).thenReturn(List.of(booking));
		when(userRepository.findById(userId)).thenReturn(Optional.of(user));
		List<Booking> actual=bookingService.getBookingByUserId(userId);
		assertEquals(1,actual.size());
	}

	@Test
	public void testShowAll() {
		when(bookingRepo.findAll()).thenReturn(List.of(booking));
		List<Booking> actual=bookingService.showAll();
		assertEquals(1,actual.size());
	}

}
	


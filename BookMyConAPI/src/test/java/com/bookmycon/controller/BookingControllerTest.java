package com.bookmycon.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.bookmycon.model.Booking;
import com.bookmycon.service.BookingService;
import com.bookmycon.utils.PdfOfBooking;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletResponse;

public class BookingControllerTest {

    @InjectMocks
    private BookingController bookingController;
    @Mock
    private HttpServletResponse response;
    @Mock
    private BookingService bookingService;
    @Mock
    private PdfOfBooking generator;


    private List<Booking> bookings;
    private Booking booking;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        booking = new Booking();
        booking.setBookingId(1);

    }

    @Test
    public void testGetAllBookings() {
        Booking booking1 = new Booking();
        Booking booking2 = new Booking();
        List<Booking> bookings = Arrays.asList(booking1, booking2);

        when(bookingService.showAll()).thenReturn(bookings);

        ResponseEntity<List<Booking>> responseEntity = bookingController.getAllBookings();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(bookings, responseEntity.getBody());
    }


    @Test
    public void testAddBooking() {
        when(bookingService.addBooking(booking)).thenReturn(booking);

        ResponseEntity<Booking> responseEntity = bookingController.addBooking(booking);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(booking, responseEntity.getBody());
        verify(bookingService).addBooking(booking);
    }

    @Test
    public void testGetBookingById() {
        when(bookingService.getBookingById(Mockito.anyInt())).thenReturn(Optional.of(booking));

        ResponseEntity<Optional<Booking>> responseEntity = bookingController.getBookingById(1);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(booking, responseEntity.getBody().get());
    }


    @Test
    public void testDeleteBookingById() {
        int bookingId = 123;
        doNothing().when(bookingService).deleteBooking(bookingId);

        ResponseEntity<String> response = bookingController.deleteBookingById(bookingId);

        verify(bookingService).deleteBooking(bookingId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Booking Deleted", response.getBody());
    }


    @Test
    public void testGetAllBookingsOfUser() {
        int userId = 123;
        List<Booking> bookings = Arrays.asList(
                new Booking(1, LocalDate.now(), 1, "Test booking 1"),
                new Booking(2, LocalDate.now(), 1, "Test booking 2")
        );
        when(bookingService.getBookingByUserId(userId)).thenReturn(bookings);

        ResponseEntity<List<Booking>> response = bookingController.getAllBookingsOfUser(userId);

        verify(bookingService).getBookingByUserId(userId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(bookings, response.getBody());
    }

@Test
public void testEditBooking() {
    Booking booking = new Booking();
    booking.setBookingId(1);
    booking.setBookingAgenda("Meeting");
    booking.setBookingDateFrom(LocalDate.now());
    booking.setBookingDateTo(LocalDate.now());

    bookingController.editBooking(1, booking);

    verify(bookingService).editBooking(1, booking);

    ResponseEntity<String> responseEntity = bookingController.editBooking(1, booking);
    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
}
    @Test
    public void testSearchBooking() {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate from = LocalDate.parse("01/01/2022", formatter);
        LocalDate to = LocalDate.parse("31/01/2022", formatter);
        List<Booking> bookingList = Arrays.asList(new Booking(), new Booking());
        when(bookingService.getByDateFromDateTo(from, to)).thenReturn(bookingList);

        ResponseEntity<List<Booking>> responseEntity = bookingController.searchBooking("2022-01-01", "2022-01-31");

        verify(bookingService, times(1)).getByDateFromDateTo(from, to);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        assertEquals(bookingList, responseEntity.getBody());
    }
}


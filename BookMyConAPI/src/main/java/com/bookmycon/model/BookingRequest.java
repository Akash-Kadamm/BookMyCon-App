package com.bookmycon.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class BookingRequest {
    private int auditoriumId;
    private int userId;
    private LocalDate bookingDateFrom;
    private LocalDate bookingDateTo;
    private LocalTime bookingTimeFrom;
    private LocalTime bookingTimeTo;
    private String bookingAgenda;

    // Constructors, getters, and setters

    public BookingRequest() {
    }

    public BookingRequest(int auditoriumId, int userId, LocalDate bookingDateFrom, LocalDate bookingDateTo, LocalTime bookingTimeFrom, LocalTime bookingTimeTo, String bookingAgenda) {
        this.auditoriumId = auditoriumId;
        this.userId = userId;
        this.bookingDateFrom = bookingDateFrom;
        this.bookingDateTo = bookingDateTo;
        this.bookingTimeFrom = bookingTimeFrom;
        this.bookingTimeTo = bookingTimeTo;
        this.bookingAgenda = bookingAgenda;
    }

    public int getAuditoriumId() {
        return auditoriumId;
    }

    public void setAuditoriumId(int auditoriumId) {
        this.auditoriumId = auditoriumId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public LocalDate getBookingDateFrom() {
        return bookingDateFrom;
    }

    public void setBookingDateFrom(LocalDate bookingDateFrom) {
        this.bookingDateFrom = bookingDateFrom;
    }

    public LocalDate getBookingDateTo() {
        return bookingDateTo;
    }

    public void setBookingDateTo(LocalDate bookingDateTo) {
        this.bookingDateTo = bookingDateTo;
    }

    public LocalTime getBookingTimeFrom() {
        return bookingTimeFrom;
    }

    public void setBookingTimeFrom(LocalTime bookingTimeFrom) {
        this.bookingTimeFrom = bookingTimeFrom;
    }

    public LocalTime getBookingTimeTo() {
        return bookingTimeTo;
    }

    public void setBookingTimeTo(LocalTime bookingTimeTo) {
        this.bookingTimeTo = bookingTimeTo;
    }

    public String getBookingAgenda() {
        return bookingAgenda;
    }

    public void setBookingAgenda(String bookingAgenda) {
        this.bookingAgenda = bookingAgenda;
    }
}

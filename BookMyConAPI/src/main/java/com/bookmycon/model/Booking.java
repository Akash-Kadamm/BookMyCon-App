package com.bookmycon.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "booking")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="booking_id")
    private int bookingId;

    @OneToOne
    @JoinColumn(name = "aduitoriam_id")
    private Auditoriums aduitoriamId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userId;

    @Column(name="booking_date_from")
    private LocalDate bookingDateFrom;

    @Column(name="booking_date_to")
    private LocalDate bookingDateTo;

    @Column(name="booking_time_from")
    private LocalTime bookingTimeFrom;

    @Column(name="booking_time_to")
    private LocalTime bookingTimeTO;

    @Column(name="booking_agenda")
    private String bookingAgenda;

    @JsonIgnore
    @OneToMany(mappedBy = "booking",fetch = FetchType.LAZY)
    List<Order> orders;

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public Auditoriums getAduitoriamId() {
        return aduitoriamId;
    }

    public void setAduitoriamId(Auditoriums aduitoriamId) {
        this.aduitoriamId = aduitoriamId;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
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

    public LocalTime getBookingTimeTO() {
        return bookingTimeTO;
    }

    public void setBookingTimeTO(LocalTime bookingTimeTO) {
        this.bookingTimeTO = bookingTimeTO;
    }

    public String getBookingAgenda() {
        return bookingAgenda;
    }

    public void setBookingAgenda(String bookingAgenda) {
        this.bookingAgenda = bookingAgenda;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public Booking(int i, LocalDate now, int i1, String s) {
    }

    public Booking(String johnDoe, String s) {
    }
}

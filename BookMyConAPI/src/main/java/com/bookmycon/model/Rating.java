package com.bookmycon.model;

import javax.persistence.*;

@Entity
@Table(name = "ratings")
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ratingId;

    private int bookingRating;
    private int snacksRating;
    private int housekeepingRating;
    private String remarks;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // Constructors
    public Rating() {}

    public Rating(int bookingRating, int snacksRating, int housekeepingRating, String remarks, User user) {
        this.bookingRating = bookingRating;
        this.snacksRating = snacksRating;
        this.housekeepingRating = housekeepingRating;
        this.remarks = remarks;
        this.user = user;
    }

    // Getters and Setters
    public int getRatingId() {
        return ratingId;
    }

    public void setRatingId(int ratingId) {
        this.ratingId = ratingId;
    }

    public int getBookingRating() {
        return bookingRating;
    }

    public void setBookingRating(int bookingRating) {
        this.bookingRating = bookingRating;
    }

    public int getSnacksRating() {
        return snacksRating;
    }

    public void setSnacksRating(int snacksRating) {
        this.snacksRating = snacksRating;
    }

    public int getHousekeepingRating() {
        return housekeepingRating;
    }

    public void setHousekeepingRating(int housekeepingRating) {
        this.housekeepingRating = housekeepingRating;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

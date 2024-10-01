package com.bookmycon.dto;

public class RatingDTO {
    private int userId;
    private int bookingRating;
    private int snacksRating;
    private int housekeepingRating;
    private String remarks;

    // Getters and Setters
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public int getBookingRating() { return bookingRating; }
    public void setBookingRating(int bookingRating) { this.bookingRating = bookingRating; }

    public int getSnacksRating() { return snacksRating; }
    public void setSnacksRating(int snacksRating) { this.snacksRating = snacksRating; }

    public int getHousekeepingRating() { return housekeepingRating; }
    public void setHousekeepingRating(int housekeepingRating) { this.housekeepingRating = housekeepingRating; }

    public String getRemarks() { return remarks; }
    public void setRemarks(String remarks) { this.remarks = remarks; }
}

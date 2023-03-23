package com.bookmycon.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity(name = "ratings")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ratingId;
    private int bookingRating;
    private int snacksRating;
    private int housekeepingRating;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    public int getBookingRating() {
        return bookingRating;
    }

    public void setBookingRating(int bookingRating) {
        this.bookingRating = bookingRating;
    }

    private String remarks;

//    public int getRating() {
//        return rating;
//    }

    public String getRemarks() {
        return remarks;
    }

//    public void setRating(int rating) {
//        this.rating = rating;
//    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
// private int userId;
}

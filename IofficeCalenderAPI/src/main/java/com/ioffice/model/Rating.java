package com.ioffice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity(name = "ratings")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ratingId;
    private int rating;

    private String remarks;

    public int getRating() {
        return rating;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
// private int userId;
}

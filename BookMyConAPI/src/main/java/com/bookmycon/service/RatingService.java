package com.bookmycon.service;

import com.bookmycon.model.Rating;
import java.util.List;

public interface RatingService {
    void addRating(Rating rating);
    List<Rating> getAllRatings();
    Rating getRatingById(int id);
    void editRating(int id, Rating rating);
    void deleteRating(int id);
}

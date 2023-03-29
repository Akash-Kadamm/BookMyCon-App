package com.bookmycon.service;

import com.bookmycon.model.Rating;

import com.bookmycon.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.log4j.Logger;

import java.util.List;

@Service
public class RatingService {
    @Autowired
    private RatingRepository ratingRepository;

    Logger logger=Logger.getLogger(RatingService.class);
    public List<Rating> getAll() {
        logger.info("Getting all ratings");
        return ratingRepository.findAll();
    }

    public void addRating(Rating rating) {
        logger.info("Adding rating with details: ");
        ratingRepository.save(rating);
    }

    public void editRating(int id, Rating rating) {
        logger.info("Editing rating with id: {0}" + id);
        ratingRepository.save(rating);
    }

    public void deleteRating(int id) {
        logger.info("Deleting rating with id: {0}" + id);
        ratingRepository.deleteById(id);
    }
}

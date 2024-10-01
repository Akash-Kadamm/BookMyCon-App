package com.bookmycon.service;

import com.bookmycon.model.Rating;
import com.bookmycon.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RatingServiceImpl implements RatingService {

    @Autowired
    private RatingRepository ratingRepository;

    @Override
    public void addRating(Rating rating) {
        ratingRepository.save(rating);
    }

    @Override
    public List<Rating> getAllRatings() {
        return ratingRepository.findAll();
    }

    @Override
    public Rating getRatingById(int id) {
        Optional<Rating> rating = ratingRepository.findById(id);
        return rating.orElse(null);
    }

    @Override
    public void editRating(int id, Rating rating) {
        if (ratingRepository.existsById(id)) {
            rating.setRatingId(id);
            ratingRepository.save(rating);
        }
    }

    @Override
    public void deleteRating(int id) {
        if (ratingRepository.existsById(id)) {
            ratingRepository.deleteById(id);
        }
    }
}

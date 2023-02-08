package com.ioffice.service;

import com.ioffice.model.Rating;

import com.ioffice.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingService {
    @Autowired
   private RatingRepository ratingRepository;
    public List<Rating> getAll() {
        return  ratingRepository.findAll();
    }
}

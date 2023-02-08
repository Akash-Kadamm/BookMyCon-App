package com.ioffice.controller;

import com.ioffice.model.Rating;
import com.ioffice.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ratings")
public class RatingController {
    @Autowired
    private RatingService ratingService;
    @GetMapping("/getRatings")
    public ResponseEntity<List<Rating>> getRatings() {
        return new ResponseEntity<List<Rating>>(ratingService.getAll(), HttpStatus.OK);

    }

    @PostMapping("/addRating")
    public ResponseEntity<String> addRating(@RequestBody Rating rating){
        ratingService.addRating(rating);
        return new ResponseEntity<String>("Rating added successfully",HttpStatus.CREATED);
    }


}

package com.bookmycon.controller;

import com.bookmycon.dto.RatingDTO;
import com.bookmycon.model.Rating;
import com.bookmycon.repository.UserRepository;
import com.bookmycon.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/ratings")
public class RatingController {
    @Autowired
    private RatingService ratingService;
    @Autowired
    private UserRepository userRepository;


    @GetMapping("/getRatings")
    public ResponseEntity<List<Rating>> getRatings() {
        return new ResponseEntity<List<Rating>>(ratingService.getAll(), HttpStatus.OK);

    }

    @PostMapping("/addRating")
    public ResponseEntity<String> addRating(@RequestBody RatingDTO ratingdto) {
        Rating rating = new Rating(0, ratingdto.getBookingRating(), ratingdto.getSnacksRating(), ratingdto.getHousekeepingRating(), userRepository.findByUserId(ratingdto.getUserId()), ratingdto.getRemarks());
        ratingService.addRating(rating);

        return new ResponseEntity<String>("Rating added successfully", HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    public ResponseEntity<String> editRating(@PathVariable("id") int id, @RequestBody Rating rating) {
        ratingService.editRating(id, rating);
        return new ResponseEntity<String>("rating updated", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRating(@PathVariable("id") int id) {
        ratingService.deleteRating(id);

        return new ResponseEntity<String>("rating deleted", HttpStatus.OK);

    }

}

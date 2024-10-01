package com.bookmycon.controller;

import com.bookmycon.dto.RatingDTO;
import com.bookmycon.model.Rating;
import com.bookmycon.model.User;
import com.bookmycon.repository.UserRepository;
import com.bookmycon.service.RatingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ratings")
@CrossOrigin(origins = "http://localhost:3000") // Allow CORS
public class RatingController {

    private static final Logger logger = LoggerFactory.getLogger(RatingController.class);

    @Autowired
    private RatingService ratingService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/addRating")
    public ResponseEntity<String> addRating(@RequestBody RatingDTO ratingDTO) {
        try {
            User user = userRepository.findByUserId(ratingDTO.getUserId());
            if (user == null) {
                return new ResponseEntity<>("User not found", HttpStatus.BAD_REQUEST);
            }

            Rating rating = new Rating();
            rating.setBookingRating(ratingDTO.getBookingRating());
            rating.setSnacksRating(ratingDTO.getSnacksRating());
            rating.setHousekeepingRating(ratingDTO.getHousekeepingRating());
            rating.setRemarks(ratingDTO.getRemarks());
            rating.setUser(user);

            ratingService.addRating(rating);
            logger.info("Rating added: {}", rating);
            return new ResponseEntity<>("Rating added successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Error adding rating", e);
            return new ResponseEntity<>("Error adding rating", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getAllRatings")
    public ResponseEntity<List<Rating>> getAllRatings() {
        try {
            List<Rating> ratings = ratingService.getAllRatings();
            return new ResponseEntity<>(ratings, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error retrieving ratings", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getRating/{id}")
    public ResponseEntity<Rating> getRatingById(@PathVariable int id) {
        try {
            Rating rating = ratingService.getRatingById(id);
            if (rating == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(rating, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error retrieving rating with ID " + id, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/editRating/{id}")
    public ResponseEntity<String> editRating(@PathVariable int id, @RequestBody RatingDTO ratingDTO) {
        try {
            User user = userRepository.findByUserId(ratingDTO.getUserId());
            if (user == null) {
                return new ResponseEntity<>("User not found", HttpStatus.BAD_REQUEST);
            }

            Rating rating = new Rating();
            rating.setBookingRating(ratingDTO.getBookingRating());
            rating.setSnacksRating(ratingDTO.getSnacksRating());
            rating.setHousekeepingRating(ratingDTO.getHousekeepingRating());
            rating.setRemarks(ratingDTO.getRemarks());
            rating.setUser(user);

            ratingService.editRating(id, rating);
            return new ResponseEntity<>("Rating updated successfully", HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error updating rating with ID " + id, e);
            return new ResponseEntity<>("Error updating rating", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteRating/{id}")
    public ResponseEntity<String> deleteRating(@PathVariable int id) {
        try {
            ratingService.deleteRating(id);
            return new ResponseEntity<>("Rating deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error deleting rating with ID " + id, e);
            return new ResponseEntity<>("Error deleting rating", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

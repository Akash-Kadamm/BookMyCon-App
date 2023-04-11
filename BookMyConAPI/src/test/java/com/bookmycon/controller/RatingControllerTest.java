package com.bookmycon.controller;

import com.bookmycon.dto.RatingDTO;
import com.bookmycon.model.Product;
import com.bookmycon.model.Rating;
import com.bookmycon.model.User;
import com.bookmycon.repository.UserRepository;
import com.bookmycon.service.RatingService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class RatingControllerTest {

    @Mock
    private RatingService ratingService;

    @InjectMocks
    private RatingController ratingController;

    @Mock
    private UserRepository userRepository;
    private User user;

    Rating rating;
    @Before
    public void setUp() {
        setData();
    }

    public void setData() {
        rating = new Rating();
    }

    @Test
    public void testGetAllRating() {
        List<Rating> ratingList = new ArrayList<>();
        ratingList.add(rating);
        when(ratingService.getAll()).thenReturn(ratingList);
        ResponseEntity<List<Rating>> actual = ratingController.getRatings();
        Assert.assertEquals(ratingList, actual.getBody());
    }

    @Test
    public void testAddRating() {
        // Set up test data

        RatingDTO ratingdto = new RatingDTO();
        ratingdto.setBookingRating(4);
        ratingdto.setSnacksRating(3);
        ratingdto.setHousekeepingRating(5);
        ratingdto.setUserId(2);
        ratingdto.setRemarks("Great experience!");

        User user = new User();
        user.setUserId(2);
        user.setUserName("testuser");
        user.setUserPassword("testpassword");

        when(userRepository.findByUserId(2)).thenReturn(user);

        // Call the method being tested
        ResponseEntity<String> responseEntity = ratingController.addRating(ratingdto);

        // Verify the results
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals("Rating added successfully", responseEntity.getBody());

        verify(ratingService, times(1)).addRating(any(Rating.class));
    }

    @Test
    public void testEditRating() {
        Rating rating = new Rating();
        rating.setBookingRating(5);
        rating.setHousekeepingRating(4);
        rating.setSnacksRating(2);

        rating.setRemarks("Average service");



        doNothing().when(ratingService).editRating(1, rating);

        ResponseEntity<String> responseEntity = ratingController.editRating(1, rating);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("rating updated", responseEntity.getBody());
    }


    @Test
    public void testDeleteRating() {
        int id = 1;
        doNothing().when(ratingService).deleteRating(id);
        ResponseEntity<String> response = ratingController.deleteRating(id);
        verify(ratingService, times(1)).deleteRating(id);
        assertEquals("rating deleted", response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

}

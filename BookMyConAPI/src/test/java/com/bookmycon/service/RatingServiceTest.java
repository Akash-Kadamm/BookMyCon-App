package com.bookmycon.service;

import com.bookmycon.model.Rating;
import com.bookmycon.repository.RatingRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class RatingServiceTest {

    @Mock
    RatingRepository ratingRepository;

    @InjectMocks
    RatingService ratingService;

    Optional<Rating> rating;
    int id;
    
    List<Rating> ratingList = new ArrayList<>();

    Rating ratings;

    @Before
    public  void setUp(){
       // MockitoAnnotations.initMocks(this);
        setData();
        setMocks();
    }
    private void setMocks() {
        when(ratingRepository.findAll()).thenReturn(ratingList);
    }
    private void setData() {
        rating = Optional.of(new Rating());
        ratings = new Rating();
    }

    @Test
    public void testAllRatings() {
        List<Rating> actual = ratingService.getAll();
        assertEquals(ratingList,actual);
    }

    @Test
    public void testUpdateRating() {
        ratingService.editRating(id,ratings);
        verify(ratingRepository, times(1)).save(ratings);
    }

    @Test
    public void testDeleteRating(){
        ratingService.deleteRating(id);
        verify(ratingRepository,times(1)).deleteById(id);
    }
//    @Test
//    public void testAddRating() {
//        ratingService.addRating(ratings);
//        verify(ratingRepository, times(1)).save(ratings);
//    }
    @Test
    public void testAddRating() {
        Rating rating = new Rating();
        rating.setBookingRating(3);
        rating.setHousekeepingRating(5);
        rating.setSnacksRating(4);

        rating.setRemarks("Great service!");

        //doNothing().when(ratingRepository).save(rating);

        ratingService.addRating(rating);

        verify(ratingRepository, times(1)).save(rating);
    }
//    @Test
//    public void testEditRating() {
//        int id = 3;
//        Rating originalRating = new Rating();
//        originalRating.setBookingRating(3);
//        originalRating.setHousekeepingRating(4);
//        originalRating.setSnacksRating(2);
//        originalRating.setRemarks("Great service!");
//        when(ratingRepository.findById(id)).thenReturn(Optional.of(originalRating));
//
//        Rating updatedRating = new Rating();
//        updatedRating.setBookingRating(3);
//        updatedRating.setHousekeepingRating(5);
//        updatedRating.setSnacksRating(4);
//        updatedRating.setRemarks("Good service!");
//        ratingService.editRating(id, updatedRating);
//
//        ArgumentCaptor<Rating> ratingCaptor = ArgumentCaptor.forClass(Rating.class);
//        verify(ratingRepository, times(1)).save(ratingCaptor.capture());
//        Rating savedRating = ratingCaptor.getValue();
//
//        assertEquals(updatedRating.getBookingRating(), savedRating.getBookingRating());
//        assertEquals(updatedRating.getRemarks(), savedRating.getRemarks());
//    }
}

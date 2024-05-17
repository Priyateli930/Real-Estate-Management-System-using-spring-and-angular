package com.remsnew.service.impl;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.remsnew.entity.Ratings;
import com.remsnew.repository.RatingsRepository;
import com.remsnew.service.impl.RatingsServiceImpl;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class RatingsServiceImplTest {

    @InjectMocks
    private RatingsServiceImpl ratingsService;

    @Mock
    private RatingsRepository ratingsDao;

    @SuppressWarnings("deprecation")
	@BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddRatings() {
        Ratings ratings = new Ratings();
        when(ratingsDao.save(ratings)).thenReturn(ratings);
        Ratings result = ratingsService.addRatings(ratings);
        assertEquals(ratings, result);
    }

    @Test
    public void testDeleteRatings() {
        int ratingId = 1;
        doNothing().when(ratingsDao).deleteById(ratingId);
        assertTrue(ratingsService.deleteRatings(ratingId));
    }

    @Test
    public void testUpdateRatings() {
        int userId = 1;
        Ratings ratingUpdates = new Ratings();
        Ratings ratings = new Ratings();
        when(ratingsDao.findById(userId)).thenReturn(Optional.of(ratings));
        when(ratingsDao.save(ratings)).thenReturn(ratings);
        Ratings result = ratingsService.updateRatings(userId, ratingUpdates);
        assertEquals(ratings, result);
    }

    @Test
    public void testUpdateRatings_RatingsNotFound() {
        int userId = 1;
        Ratings ratingUpdates = new Ratings();
        when(ratingsDao.findById(userId)).thenReturn(Optional.empty());
        assertThrows(NoSuchElementException.class, () -> ratingsService.updateRatings(userId, ratingUpdates));
    }

    @Test
    public void testGetAllRatings() {
        Pageable pageable = mock(Pageable.class);
        @SuppressWarnings("unchecked")
		Page<Ratings> page = mock(Page.class);
        when(ratingsDao.findAll(pageable)).thenReturn(page);
        assertEquals(page, ratingsService.getAllRatings(pageable));
    }

    @Test
    public void testGetAllRatingsByPropertyId() {
        int propertyId = 1;
        List<Ratings> ratingsList = Collections.singletonList(new Ratings());
        when(ratingsDao.findByProperty_Propertyid(propertyId)).thenReturn(ratingsList);
        assertEquals(ratingsList, ratingsService.getAllRatingsByPropertyId(propertyId));
    }
}

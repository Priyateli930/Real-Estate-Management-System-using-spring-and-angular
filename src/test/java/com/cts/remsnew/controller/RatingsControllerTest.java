package com.remsnew.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.remsnew.controller.RatingsController;
import com.remsnew.entity.Ratings;
import com.remsnew.exception.UserNotFoundException;
import com.remsnew.services.RatingsService;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class RatingsControllerTest {

    @Mock
    private RatingsService service;

    @InjectMocks
    private RatingsController controller;

    @SuppressWarnings("deprecation")
	@BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }
 
    @Test
    public void testFindAll_V1_Positive() {
        // Arrange
        Ratings rating = new Ratings(1, null, null, 5, "test", "test");
        List<Ratings> ratingList = Arrays.asList(rating);
        Page<Ratings> ratingPage = new PageImpl<>(ratingList);

        when(service.getAllRatings(any())).thenReturn(ratingPage);

        // Act
        ResponseEntity<Page<Ratings>> result = controller.getAllRatings(0, 1, "ratingid");

        // Assert
        assertEquals(1, result.getBody().getContent().size());
        assertEquals(5, result.getBody().getContent().get(0).getRatingvalue());

        verify(service, times(1)).getAllRatings(any());
        verifyNoMoreInteractions(service);
    }

    @Test
    public void testFindAll_V2_Positive() {
        // Arrange
        Ratings rating = new Ratings(1, null, null, 5, "test", "test");
        List<Ratings> ratingList = Arrays.asList(rating);

        when(service.getAllRatingsByPropertyId(anyInt())).thenReturn(ratingList);

        // Act
        ResponseEntity<List<Ratings>> result = controller.getAllRatingsByPropertyId(1);

        // Assert
        assertEquals(1, result.getBody().size());
        assertEquals(5, result.getBody().get(0).getRatingvalue());

        verify(service, times(1)).getAllRatingsByPropertyId(anyInt());
        verifyNoMoreInteractions(service);
    }

    @Test
    public void testUpdateRatings_Positive() throws UserNotFoundException {
        // Arrange
        Ratings rating = new Ratings(1, null, null, 5, "test", "test");

        when(service.updateRatings(anyInt(), any())).thenReturn(rating);

        // Act
        ResponseEntity<Ratings> result = controller.updateRatings(1, rating);

        // Assert
        assertEquals(5, result.getBody().getRatingvalue());

        verify(service, times(1)).updateRatings(anyInt(), any());
        verifyNoMoreInteractions(service);
    }

    @Test
    public void testDeleteRatings_Positive() {
        // Arrange
        when(service.deleteRatings(anyInt())).thenReturn(true);

        // Act
        ResponseEntity<Ratings> result = controller.deleteRatings(1);

        // Assert
        assertEquals(HttpStatus.OK, result.getStatusCode());

        verify(service, times(1)).deleteRatings(anyInt());
        verifyNoMoreInteractions(service);
    }

    @Test
    public void testAddRatings_Positive() {
        // Arrange
        Ratings rating = new Ratings(1, null, null, 5, "test", "test");

        when(service.addRatings(any())).thenReturn(rating);

        // Act
        ResponseEntity<Ratings> result = controller.addRatings(rating);

        // Assert
        assertEquals(5, result.getBody().getRatingvalue());

        verify(service, times(1)).addRatings(any());
        verifyNoMoreInteractions(service);
    }
}

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

import com.remsnew.controller.PropImagesController;
import com.remsnew.entity.PropImages;
import com.remsnew.exception.UserNotFoundException;
import com.remsnew.services.PropImagesService;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class PropImagesControllerTest {

    @Mock
    private PropImagesService service;

    @InjectMocks
    private PropImagesController controller;

    @SuppressWarnings("deprecation")
	@BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindAll_V1_Positive() {
        // Arrange
        PropImages propImage = new PropImages("image1", 1, 1);
        List<PropImages> propImageList = Arrays.asList(propImage);
        Page<PropImages> propImagePage = new PageImpl<>(propImageList);

        when(service.getAllPropImages(any())).thenReturn(propImagePage);

        // Act
        ResponseEntity<Page<PropImages>> result = controller.getAllPropImages(0, 10, "pr_id");

        // Assert
        assertEquals(1, result.getBody().getContent().size());
        assertEquals("image1", result.getBody().getContent().get(0).getPropImages());

        verify(service, times(1)).getAllPropImages(any());
        verifyNoMoreInteractions(service);
    }

    @Test
    public void testFindAll_V2_Positive() {
        // Arrange
        PropImages propImage = new PropImages("image1", 1, 1);
        List<PropImages> propImageList = Arrays.asList(propImage);

        when(service.getAllPropImagesByPropertyId(anyInt())).thenReturn(propImageList);

        // Act
        ResponseEntity<List<PropImages>> result = controller.getAllPropImagesByPropertyId(1);

        // Assert
        assertEquals(1, result.getBody().size());
        assertEquals("image1", result.getBody().get(0).getPropImages());

        verify(service, times(1)).getAllPropImagesByPropertyId(anyInt());
        verifyNoMoreInteractions(service);
    }

    @Test
    public void testUpdatePropertyImages_Positive() throws UserNotFoundException {
        // Arrange
        PropImages propImage = new PropImages("image1", 1, 1);

        when(service.updatePropImages(anyInt(), any())).thenReturn(propImage);

        // Act
        ResponseEntity<PropImages> result = controller.updatePropertyImages(1, propImage);

        // Assert
        assertEquals("image1", result.getBody().getPropImages());

        verify(service, times(1)).updatePropImages(anyInt(), any());
        verifyNoMoreInteractions(service);
    }

    @Test
    public void testDeletePropertyImages_Positive() {
        // Arrange
        when(service.deletePropImages(anyInt())).thenReturn(true);

        // Act
        ResponseEntity<PropImages> result = controller.deletePropertyImages(1);

        // Assert
        assertEquals(HttpStatus.OK, result.getStatusCode());

        verify(service, times(1)).deletePropImages(anyInt());
        verifyNoMoreInteractions(service);
    }

    @Test
    public void testAddPropImages_Positive() {
        // Arrange
        PropImages propImage = new PropImages("image1", 1, 1);

        when(service.addPropImages(any())).thenReturn(propImage);

        // Act
        ResponseEntity<PropImages> result = controller.addPropImages(propImage);

        // Assert 
        assertEquals("image1", result.getBody().getPropImages());

        verify(service, times(1)).addPropImages(any());
        verifyNoMoreInteractions(service);
    }
}

package com.remsnew.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.remsnew.entity.PropImages;
import com.remsnew.repository.PropImagesRepository;
import com.remsnew.service.impl.PropImagesServiceImpl;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PropImagesServiceImplTest {

    @InjectMocks
    private PropImagesServiceImpl propImagesService;

    @Mock
    private PropImagesRepository propImagesDao;

    @SuppressWarnings("deprecation")
	@BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddPropImages() {
        PropImages propImages = new PropImages();
        when(propImagesDao.save(propImages)).thenReturn(propImages);
        PropImages result = propImagesService.addPropImages(propImages);
        assertEquals(propImages, result);
    }

    @Test
    public void testDeletePropImages() {
        int propertyId = 1;
        doNothing().when(propImagesDao).deleteById(propertyId);
        assertTrue(propImagesService.deletePropImages(propertyId));
    }

    @Test
    public void testUpdatePropImages() {
        int imageId = 1;
        PropImages propImageUpdates = new PropImages();
        PropImages propImages = new PropImages();
        when(propImagesDao.findById(imageId)).thenReturn(Optional.of(propImages));
        when(propImagesDao.save(propImages)).thenReturn(propImages);
        PropImages result = propImagesService.updatePropImages(imageId, propImageUpdates);
        assertEquals(propImages, result);
    }

    @Test
    public void testUpdatePropImages_PropImagesNotFound() {
        int imageId = 1;
        PropImages propImageUpdates = new PropImages();
        when(propImagesDao.findById(imageId)).thenReturn(Optional.empty());
        assertThrows(NoSuchElementException.class, () -> propImagesService.updatePropImages(imageId, propImageUpdates));
    }

    @Test
    public void testGetAllPropImages() {
        Pageable pageable = mock(Pageable.class);
        @SuppressWarnings("unchecked")
		Page<PropImages> page = mock(Page.class);
        when(propImagesDao.findAll(pageable)).thenReturn(page);
        assertEquals(page, propImagesService.getAllPropImages(pageable));
    }

    @Test
    public void testGetAllPropImagesByPropertyId() {
        int propertyId = 1;
        List<PropImages> propImagesList = Collections.singletonList(new PropImages());
        when(propImagesDao.findByPropertyid(propertyId)).thenReturn(propImagesList);
        assertEquals(propImagesList, propImagesService.getAllPropImagesByPropertyId(propertyId));
    }
}

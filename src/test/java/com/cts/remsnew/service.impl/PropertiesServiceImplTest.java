package com.remsnew.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.remsnew.entity.Properties;
import com.remsnew.exception.PropertyNotFoundException;
import com.remsnew.repository.PropertiesRepository;
import com.remsnew.service.impl.PropertiesServiceImpl;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PropertiesServiceImplTest {

    @InjectMocks
    private PropertiesServiceImpl propertiesService;

    @Mock
    private PropertiesRepository propertiesDao;

    @SuppressWarnings("deprecation")
	@BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllPropertiesByCity() {
        String city = "Test City";
        Pageable pageable = mock(Pageable.class);
        @SuppressWarnings("unchecked")
		Page<Properties> page = mock(Page.class);
        when(propertiesDao.findByCity(city, pageable)).thenReturn(page);
        assertEquals(page, propertiesService.getAllPropertiesByCity(city, pageable));
    }

    @Test
    public void testAddProperty() {
        Properties properties = new Properties();
        when(propertiesDao.save(properties)).thenReturn(properties);
        Properties result = propertiesService.addProperty(properties);
        assertEquals(properties, result);
    }

    @Test
    public void testDeleteProperty() {
        int propertyId = 1;
        doNothing().when(propertiesDao).deleteById(propertyId);
        assertTrue(propertiesService.deleteProperty(propertyId));
    }

    @Test
    public void testUpdateProperty() {
        int propertyId = 1;
        Properties propertyUpdates = new Properties();
        Properties properties = new Properties();
        when(propertiesDao.findById(propertyId)).thenReturn(Optional.of(properties));
        when(propertiesDao.save(properties)).thenReturn(properties);
        Properties result = propertiesService.updateProperty(propertyId, propertyUpdates);
        assertEquals(properties, result);
    }

    @Test
    public void testUpdateProperty_PropertyNotFound() {
        int propertyId = 1;
        Properties propertyUpdates = new Properties();
        when(propertiesDao.findById(propertyId)).thenReturn(Optional.empty());
        assertThrows(NoSuchElementException.class, () -> propertiesService.updateProperty(propertyId, propertyUpdates));
    }

    @Test
    public void testGetAllProperties() {
        Pageable pageable = mock(Pageable.class);
        @SuppressWarnings("unchecked")
		Page<Properties> page = mock(Page.class);
        when(propertiesDao.findAll(pageable)).thenReturn(page);
        assertEquals(page, propertiesService.getAllProperties(pageable));
    }

    @Test
    public void testGetAllPropertiesByPrice() throws PropertyNotFoundException {
        double minPrice = 100.0;
        double maxPrice = 200.0;
        Pageable pageable = mock(Pageable.class);
        @SuppressWarnings("unchecked")
		Page<Properties> page = mock(Page.class);
        when(propertiesDao.findByPriceBetween(minPrice, maxPrice, pageable)).thenReturn(page);
        assertEquals(page, propertiesService.getAllPropertiesByPrice(minPrice, maxPrice, pageable));
    }

    @Test
    public void testGetAllPropertiesByPrice_PropertyNotFound() {
        double minPrice = 100.0;
        double maxPrice = 200.0;
        Pageable pageable = mock(Pageable.class);
        when(propertiesDao.findByPriceBetween(minPrice, maxPrice, pageable)).thenReturn(null);
        assertThrows(PropertyNotFoundException.class, () -> propertiesService.getAllPropertiesByPrice(minPrice, maxPrice, pageable));
    }

    @Test
    public void testGetAllPropertiesByPropertytype() throws PropertyNotFoundException {
        String propertyType = "Test Type";
        Pageable pageable = mock(Pageable.class);
        @SuppressWarnings("unchecked")
		Page<Properties> page = mock(Page.class);
        when(propertiesDao.findByPropertytype(propertyType, pageable)).thenReturn(page);
        assertEquals(page, propertiesService.getAllPropertiesByPropertytype(propertyType, pageable));
    }

    @Test
    public void testGetAllPropertiesByPropertytype_PropertyNotFound() {
        String propertyType = "Test Type";
        Pageable pageable = mock(Pageable.class);
        when(propertiesDao.findByPropertytype(propertyType, pageable)).thenReturn(null);
        assertThrows(PropertyNotFoundException.class, () -> propertiesService.getAllPropertiesByPropertytype(propertyType, pageable));
    }

    @Test
    public void testGetAllPropertiesByFilters() throws PropertyNotFoundException {
        String city = "Test City";
        double minPrice = 100.0;
        double maxPrice = 200.0;
        String propStatus = "Test Status";
        String propertyType = "Test Type";
        Pageable pageable = mock(Pageable.class);
        @SuppressWarnings("unchecked")
		Page<Properties> page = mock(Page.class);
        when(propertiesDao.findByCityAndPriceBetweenAndPropstatusAndPropertytype(city, minPrice, maxPrice, propStatus, propertyType, pageable)).thenReturn(page);
        assertEquals(page, propertiesService.getAllPropertiesByFilters(city, minPrice, maxPrice, propStatus, propertyType, pageable));
    }

    @Test
    public void testGetAllPropertiesByFilters_PropertyNotFound() {
        String city = "Test City";
        double minPrice = 100.0;
        double maxPrice = 200.0;
        String propStatus = "Test Status";
        String propertyType = "Test Type";
        Pageable pageable = mock(Pageable.class);
        when(propertiesDao.findByCityAndPriceBetweenAndPropstatusAndPropertytype(city, minPrice, maxPrice, propStatus, propertyType, pageable)).thenReturn(null);
        assertThrows(PropertyNotFoundException.class, () -> propertiesService.getAllPropertiesByFilters(city, minPrice, maxPrice, propStatus, propertyType, pageable));
    }
}

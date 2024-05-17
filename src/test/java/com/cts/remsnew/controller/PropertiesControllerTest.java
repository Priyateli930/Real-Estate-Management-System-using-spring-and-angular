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

import com.remsnew.controller.PropertiesController;
import com.remsnew.entity.Properties;
import com.remsnew.exception.PropertyNotFoundException;
import com.remsnew.exception.UserNotFoundException;
import com.remsnew.services.PropertiesService;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class PropertiesControllerTest {

    @Mock
    private PropertiesService service;

    @InjectMocks
    private PropertiesController controller;

    @SuppressWarnings("deprecation")
	@BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindAll_V1_Positive() {
        // Arrange
        Properties property = new Properties("Property1", "Address1", "Address2", "State", "City", 100.0f, "Residential", "Available");
        List<Properties> propertyList = Arrays.asList(property);
        Page<Properties> propertyPage = new PageImpl<>(propertyList);

        when(service.getAllProperties(any())).thenReturn(propertyPage);

        // Act
        ResponseEntity<Page<Properties>> result = controller.getAllProperties(0, 10, "propertyid");

        // Assert
        assertEquals(1, result.getBody().getContent().size());
        assertEquals("Property1", result.getBody().getContent().get(0).getAddressline1());

        verify(service, times(1)).getAllProperties(any());
        verifyNoMoreInteractions(service);
    }

    @Test
    public void testFindAll_V3_Positive() {
        // Arrange
        Properties property = new Properties("Property1", "Address1", "Address2", "State", "City", 100.0f, "Residential", "Available");
        List<Properties> propertyList = Arrays.asList(property);
        Page<Properties> propertyPage = new PageImpl<>(propertyList);

        when(service.getAllPropertiesByCity(anyString(), any())).thenReturn(propertyPage);

        // Act
        ResponseEntity<Page<Properties>> result = controller.getAllPropertiesByCity("New York", 0, 10, "propertyid");

        // Assert
        assertEquals(1, result.getBody().getContent().size());
        assertEquals("Property1", result.getBody().getContent().get(0).getAddressline1());

        verify(service, times(1)).getAllPropertiesByCity(anyString(), any());
        verifyNoMoreInteractions(service);
    }

    @Test
    public void testFindAll_V4_Positive() throws PropertyNotFoundException {
        // Arrange
        Properties property = new Properties("Property1", "Address1", "Address2", "State", "City", 100.0f, "Residential", "Available");
        List<Properties> propertyList = Arrays.asList(property);
        Page<Properties> propertyPage = new PageImpl<>(propertyList);

        when(service.getAllPropertiesByPrice(anyDouble(), anyDouble(), any())).thenReturn(propertyPage);

        // Act
        ResponseEntity<Page<Properties>> result = controller.getAllPropertiesByPrice(100.0, 200.0, "New York", 0, 10, "propertyid");

        // Assert
        assertEquals(1, result.getBody().getContent().size());
        assertEquals("Property1", result.getBody().getContent().get(0).getAddressline1());

        verify(service, times(1)).getAllPropertiesByPrice(anyDouble(), anyDouble(), any());
        verifyNoMoreInteractions(service);
    }

    @Test
    public void testGetPropertiesByPropertytype_Positive() throws PropertyNotFoundException {
        // Arrange
        Properties property = new Properties("Property1", "Address1", "Address2", "State", "City", 100.0f, "Residential", "Available");
        List<Properties> propertyList = Arrays.asList(property);
        Page<Properties> propertyPage = new PageImpl<>(propertyList);

        when(service.getAllPropertiesByPropertytype(anyString(), any())).thenReturn(propertyPage);

        // Act
        ResponseEntity<Page<Properties>> result = controller.getPropertiesByPropertytype("Residential", 0, 10, "propertyid");

        // Assert
        assertEquals(1, result.getBody().getContent().size());
        assertEquals("Property1", result.getBody().getContent().get(0).getAddressline1());

        verify(service, times(1)).getAllPropertiesByPropertytype(anyString(), any());
        verifyNoMoreInteractions(service);
    }

    @Test
    public void testGetPropertiesByFilters_Positive() throws PropertyNotFoundException {
        // Arrange
        Properties property = new Properties("Property1", "Address1", "Address2", "State", "City", 100.0f, "Residential", "Available");
        List<Properties> propertyList = Arrays.asList(property);
        Page<Properties> propertyPage = new PageImpl<>(propertyList);

        when(service.getAllPropertiesByFilters(anyString(), anyDouble(), anyDouble(), anyString(), anyString(), any())).thenReturn(propertyPage);

        // Act
        ResponseEntity<Page<Properties>> result = controller.getPropertiesByFilters("New York", 100.0, 200.0, "Available", "Residential", 0, 10, "propertyid");

        // Assert
        assertEquals(1, result.getBody().getContent().size());
        assertEquals("Property1", result.getBody().getContent().get(0).getAddressline1());

        verify(service, times(1)).getAllPropertiesByFilters(anyString(), anyDouble(), anyDouble(), anyString(), anyString(), any());
        verifyNoMoreInteractions(service);
    }

    @Test
    public void testUpdateProperties_Positive() throws UserNotFoundException {
        // Arrange
        Properties property = new Properties("Property1", "Address1", "Address2", "State", "City", 100.0f, "Residential", "Available");

        when(service.updateProperty(anyInt(), any())).thenReturn(property);

        // Act
        ResponseEntity<Properties> result = controller.updateProperties(1, property);

        // Assert
        assertEquals("Property1", result.getBody().getAddressline1());

        verify(service, times(1)).updateProperty(anyInt(), any());
        verifyNoMoreInteractions(service);
    }

    @Test
    public void testDeleteProperties_Positive() {
        // Arrange
        when(service.deleteProperty(anyInt())).thenReturn(true);

        // Act
        ResponseEntity<Properties> result = controller.deleteProperties(1);

        // Assert
        assertEquals(HttpStatus.OK, result.getStatusCode());

        verify(service, times(1)).deleteProperty(anyInt());
        verifyNoMoreInteractions(service);
    }

    @Test
    public void testAddProperty_Positive() {
        // Arrange
        Properties property = new Properties("Property1", "Address1", "Address2", "State", "City", 100.0f, "Residential", "Available");

        when(service.addProperty(any())).thenReturn(property);

        // Act
        ResponseEntity<Properties> result = controller.addProperty(property);

        // Assert
        assertEquals("Property1", result.getBody().getAddressline1());

        verify(service, times(1)).addProperty(any());
        verifyNoMoreInteractions(service);
    }

    
}

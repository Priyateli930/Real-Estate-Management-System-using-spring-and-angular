package com.remsnew.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.remsnew.controller.ContactAssistanceController;
import com.remsnew.entity.ContactAssistance;
import com.remsnew.exception.UserNotFoundException;
import com.remsnew.services.ContactAssistanceService;

class ContactAssistanceControllerTest {

	 @Mock
	ContactAssistanceService service;
	
	 @InjectMocks
	ContactAssistanceController controller;
	
	 @SuppressWarnings("deprecation")
		@BeforeEach
	    public void setup() {
	        MockitoAnnotations.initMocks(this);
	    }
	 
	@Test
	public void testFindAll_V1_Positive() {
	    // Arrange
	    ContactAssistance contactAssistance = new ContactAssistance(1, null, "John Doe", "johndoe@example.com", "1234567890", "Subject", "Message");
	    List<ContactAssistance> contactAssistanceList = Arrays.asList(contactAssistance);
	    Page<ContactAssistance> contactAssistancePage = new PageImpl<>(contactAssistanceList);

	    when(service.getAllContactAssistance(any())).thenReturn(contactAssistancePage);

	    // Act
	    ResponseEntity<Page<ContactAssistance>> result = controller.getAllContactAssistance(0, 10, "date");

	    // Assert
	    assertEquals(1, result.getBody().getContent().size());
	    assertEquals("John Doe", result.getBody().getContent().get(0).getCname());

	    verify(service, times(1)).getAllContactAssistance(any());
	    verifyNoMoreInteractions(service);
	}

	@Test
	public void testUpdateContactAst_Positive() throws UserNotFoundException {
	    // Arrange
	    ContactAssistance contactAssistance = new ContactAssistance(1,null,"John Doe", "johndoe@example.com", "1234567890", "Subject", "Message");

	    when(service.updateContactAssistance(anyInt(), any())).thenReturn(contactAssistance);

	    // Act
	    ResponseEntity<ContactAssistance> result = controller.updateContactAst(1, contactAssistance);

	    // Assert
	    assertEquals("John Doe", result.getBody().getCname());

	    verify(service, times(1)).updateContactAssistance(anyInt(), any());
	    verifyNoMoreInteractions(service);
	}

	@Test
	public void testDeleteContactAssistance_Positive() {
	    // Arrange
	    when(service.deleteContactAssistance(anyInt())).thenReturn(true);

	    // Act
	    ResponseEntity<ContactAssistance> result = controller.deleteContactAssistance(1);

	    // Assert
	    assertEquals(HttpStatus.OK, result.getStatusCode());

	    verify(service, times(1)).deleteContactAssistance(anyInt());
	    verifyNoMoreInteractions(service);
	}

	@Test
	public void testAddContactAssistance_Positive() {
	    // Arrange
	    ContactAssistance contactAssistance = new ContactAssistance(1,null, "John Doe", "johndoe@example.com", "1234567890", "Subject", "Message");

	    when(service.addContactAssistance(any())).thenReturn(contactAssistance);

	    // Act
	    ResponseEntity<ContactAssistance> result = controller.addContactAssistance(contactAssistance);

	    // Assert
	    assertEquals("John Doe", result.getBody().getCname());

	    verify(service, times(1)).addContactAssistance(any());
	    verifyNoMoreInteractions(service);
	}


}

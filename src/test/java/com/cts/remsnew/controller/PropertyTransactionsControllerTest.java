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

import com.remsnew.controller.PropertyTransactionsController;
import com.remsnew.entity.PropertyTransactions;
import com.remsnew.exception.PropertyNotFoundException;
import com.remsnew.exception.UserNotFoundException;
import com.remsnew.services.PropertyTransactionsService;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class PropertyTransactionsControllerTest {

    @Mock
    private PropertyTransactionsService service;

    @InjectMocks
    private PropertyTransactionsController controller;

    @SuppressWarnings("deprecation")
	@BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }
 
    @Test
    public void testFindAll_V1_Positive() {
        // Arrange
        PropertyTransactions transaction = new PropertyTransactions(1, null, null, 100, "transaction1", 2);
        List<PropertyTransactions> transactionList = Arrays.asList(transaction);
        Page<PropertyTransactions> transactionPage = new PageImpl<>(transactionList);

        when(service.getAllPropertyTransactions(any())).thenReturn(transactionPage);

        // Act
        ResponseEntity<Page<PropertyTransactions>> result = controller.getAllPropertyTransactions(0, 10, "date");

        // Assert
        assertEquals(1, result.getBody().getContent().size());
        assertEquals("transaction1", result.getBody().getContent().get(0).getTransactiondate());

        verify(service, times(1)).getAllPropertyTransactions(any());
        verifyNoMoreInteractions(service);
    }

    @Test
    public void testFindAll_V2_Positive() throws PropertyNotFoundException {
        // Arrange
        PropertyTransactions transaction = new PropertyTransactions(1, null, null, 100, "transaction1", 2);

        when(service.getAllPropertyTransactionsByPropertyId(anyInt())).thenReturn(transaction);

        // Act
        ResponseEntity<PropertyTransactions> result = controller.getAllPropertyTransactionsByPropertyId(1);

        // Assert
        assertEquals("transaction1", result.getBody().getTransactiondate());

        verify(service, times(1)).getAllPropertyTransactionsByPropertyId(anyInt());
        verifyNoMoreInteractions(service);
    }

    @Test
    public void testFindAll_V3_Positive() throws PropertyNotFoundException {
        // Arrange
        PropertyTransactions transaction = new PropertyTransactions(1, null, null, 100, "transaction1", 2);
        List<PropertyTransactions> transactionList = Arrays.asList(transaction);

        when(service.getAllPropertyTransactionsByTransactionDate(anyString())).thenReturn(transactionList);

        // Act
        ResponseEntity<List<PropertyTransactions>> result = controller.getAllPropertyTransactionsByTransactionDate("2022-01-01");

        // Assert
        assertEquals(1, result.getBody().size());
        assertEquals("transaction1", result.getBody().get(0).getTransactiondate());

        verify(service, times(1)).getAllPropertyTransactionsByTransactionDate(anyString());
        verifyNoMoreInteractions(service);
    }

    @Test
    public void testUpdatePropertyTransactions_Positive() throws UserNotFoundException {
        // Arrange
        PropertyTransactions transaction = new PropertyTransactions(1, null, null, 100, "transaction1", 2);

        when(service.updatePropertyTransactions(anyInt(), any())).thenReturn(transaction);

        // Act
        ResponseEntity<PropertyTransactions> result = controller.updatePropertyTransactions(1, transaction);

        // Assert
        assertEquals("transaction1", result.getBody().getTransactiondate());

        verify(service, times(1)).updatePropertyTransactions(anyInt(), any());
        verifyNoMoreInteractions(service);
    }

    @Test
    public void testDeletePropertyTransactions_Positive() {
        // Arrange
        when(service.deletePropertyTransactions(anyInt())).thenReturn(true);

        // Act
        ResponseEntity<PropertyTransactions> result = controller.deletePropertyTransactions(1);

        // Assert
        assertEquals(HttpStatus.OK, result.getStatusCode());

        verify(service, times(1)).deletePropertyTransactions(anyInt());
        verifyNoMoreInteractions(service);
    }

    @Test
    public void testAddPropertyTransaction_Positive() {
        // Arrange
        PropertyTransactions transaction = new PropertyTransactions(1, null, null, 100, "transaction1", 2);

        when(service.addPropertyTransactions(any())).thenReturn(transaction);

        // Act
        ResponseEntity<PropertyTransactions> result = controller.addPropertyTransaction(transaction);

        // Assert
        assertEquals("transaction1", result.getBody().getTransactiondate());

        verify(service, times(1)).addPropertyTransactions(any());
        verifyNoMoreInteractions(service);
    }
}

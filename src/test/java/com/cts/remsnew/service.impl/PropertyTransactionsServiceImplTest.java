package com.remsnew.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.remsnew.entity.PropertyTransactions;
import com.remsnew.exception.PropertyNotFoundException;
import com.remsnew.repository.PropertyTransactionsRepository;
import com.remsnew.service.impl.PropertyTransactionsServiceImpl;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PropertyTransactionsServiceImplTest {

    @InjectMocks
    private PropertyTransactionsServiceImpl propertyTransactionsService;

    @Mock
    private PropertyTransactionsRepository propertyTransactionsDao;

    @SuppressWarnings("deprecation")
	@BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddPropertyTransactions() {
        PropertyTransactions propertyTransactions = new PropertyTransactions();
        when(propertyTransactionsDao.save(propertyTransactions)).thenReturn(propertyTransactions);
        PropertyTransactions result = propertyTransactionsService.addPropertyTransactions(propertyTransactions);
        assertEquals(propertyTransactions, result);
    }

    @Test
    public void testDeletePropertyTransactions() {
        int transactionId = 1;
        doNothing().when(propertyTransactionsDao).deleteById(transactionId);
        assertTrue(propertyTransactionsService.deletePropertyTransactions(transactionId));
    }

    @Test
    public void testUpdatePropertyTransactions() {
        int transactionId = 1;
        PropertyTransactions propertyTransactionsUpdates = new PropertyTransactions();
        PropertyTransactions propertyTransactions = new PropertyTransactions();
        when(propertyTransactionsDao.findById(transactionId)).thenReturn(Optional.of(propertyTransactions));
        when(propertyTransactionsDao.save(propertyTransactions)).thenReturn(propertyTransactions);
        PropertyTransactions result = propertyTransactionsService.updatePropertyTransactions(transactionId, propertyTransactionsUpdates);
        assertEquals(propertyTransactions, result);
    }

    @Test
    public void testUpdatePropertyTransactions_PropertyTransactionsNotFound() {
        int transactionId = 1;
        PropertyTransactions propertyTransactionsUpdates = new PropertyTransactions();
        when(propertyTransactionsDao.findById(transactionId)).thenReturn(Optional.empty());
        assertThrows(NoSuchElementException.class, () -> propertyTransactionsService.updatePropertyTransactions(transactionId, propertyTransactionsUpdates));
    }

    @Test
    public void testGetAllPropertyTransactions() {
        Pageable pageable = mock(Pageable.class);
        @SuppressWarnings("unchecked")
		Page<PropertyTransactions> page = mock(Page.class);
        when(propertyTransactionsDao.findAll(pageable)).thenReturn(page);
        assertEquals(page, propertyTransactionsService.getAllPropertyTransactions(pageable));
    }

    @Test
    public void testGetAllPropertyTransactionsByTransactionDate() throws PropertyNotFoundException {
        String transactionDate = "2022-01-01";
        List<PropertyTransactions> propertyTransactionsList = Collections.singletonList(new PropertyTransactions());
        when(propertyTransactionsDao.findByTransactiondate(transactionDate)).thenReturn(propertyTransactionsList);
        assertEquals(propertyTransactionsList, propertyTransactionsService.getAllPropertyTransactionsByTransactionDate(transactionDate));
    }

    @Test
    public void testGetAllPropertyTransactionsByTransactionDate_PropertyTransactionsNotFound() {
        String transactionDate = "2022-01-01";
        when(propertyTransactionsDao.findByTransactiondate(transactionDate)).thenReturn(Collections.emptyList());
        assertThrows(PropertyNotFoundException.class, () -> propertyTransactionsService.getAllPropertyTransactionsByTransactionDate(transactionDate));
    }

    @Test
    public void testGetAllPropertyTransactionsByPropertyId() throws PropertyNotFoundException {
        int propertyId = 1;
        PropertyTransactions propertyTransactions = new PropertyTransactions();
        when(propertyTransactionsDao.findByProperty_Propertyid(propertyId)).thenReturn(Optional.of(propertyTransactions));
        assertEquals(propertyTransactions, propertyTransactionsService.getAllPropertyTransactionsByPropertyId(propertyId));
    }

    @Test
    public void testGetAllPropertyTransactionsByPropertyId_PropertyTransactionsNotFound() {
        int propertyId = 1;
        when(propertyTransactionsDao.findByProperty_Propertyid(propertyId)).thenReturn(Optional.empty());
        assertThrows(PropertyNotFoundException.class, () -> propertyTransactionsService.getAllPropertyTransactionsByPropertyId(propertyId));
    }
}

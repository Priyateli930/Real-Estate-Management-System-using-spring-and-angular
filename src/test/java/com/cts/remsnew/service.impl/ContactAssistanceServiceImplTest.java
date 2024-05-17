package com.remsnew.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.remsnew.entity.ContactAssistance;
import com.remsnew.repository.ContactAssistanceRepository;
import com.remsnew.service.impl.ContactAssistanceServiceImpl;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ContactAssistanceServiceImplTest {

    @InjectMocks
    private ContactAssistanceServiceImpl contactAssistanceService;

    @Mock
    private ContactAssistanceRepository contactAssistanceDao;

    @SuppressWarnings("deprecation")
	@BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddContactAssistance() {
        ContactAssistance contactAssistance = new ContactAssistance();
        when(contactAssistanceDao.save(contactAssistance)).thenReturn(contactAssistance);
        ContactAssistance result = contactAssistanceService.addContactAssistance(contactAssistance);
        assertEquals(contactAssistance, result);
    }

    @Test
    public void testDeleteContactAssistance() {
        int id = 1;
        doNothing().when(contactAssistanceDao).deleteById(id);
        assertTrue(contactAssistanceService.deleteContactAssistance(id));
    }

    @Test
    public void testUpdateContactAssistance() {
        int id = 1;
        ContactAssistance contactAssistanceUpdates = new ContactAssistance();
        ContactAssistance contactAssistance = new ContactAssistance();
        when(contactAssistanceDao.findById(id)).thenReturn(Optional.of(contactAssistance));
        when(contactAssistanceDao.save(contactAssistance)).thenReturn(contactAssistance);
        ContactAssistance result = contactAssistanceService.updateContactAssistance(id, contactAssistanceUpdates);
        assertEquals(contactAssistance, result);
    }

    @Test
    public void testUpdateContactAssistance_ContactAssistanceNotFound() {
        int id = 1;
        ContactAssistance contactAssistanceUpdates = new ContactAssistance();
        when(contactAssistanceDao.findById(id)).thenReturn(Optional.empty());
        assertThrows(NoSuchElementException.class, () -> contactAssistanceService.updateContactAssistance(id, contactAssistanceUpdates));
    }

    @Test
    public void testGetAllContactAssistance() {
        Pageable pageable = mock(Pageable.class);
        @SuppressWarnings("unchecked")
		Page<ContactAssistance> page = mock(Page.class);
        when(contactAssistanceDao.findAll(pageable)).thenReturn(page);
        assertEquals(page, contactAssistanceService.getAllContactAssistance(pageable));
    }
}

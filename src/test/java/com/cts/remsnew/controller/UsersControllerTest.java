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

import com.remsnew.controller.UsersController;
import com.remsnew.entity.Users;
import com.remsnew.exception.UserNotFoundException;
import com.remsnew.services.UserService;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class UsersControllerTest {

    @Mock
    private UserService service;

    @InjectMocks
    private UsersController controller;

    @SuppressWarnings("deprecation")
	@BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindAll_V1_Positive() {
        // Arrange
        Users user = new Users(1, "John", "Doe", "john.doe@example.com", "test", "test", "test", "test", "test", "test", "test", "test", "test", null, null, null, null, null);
        List<Users> userList = Arrays.asList(user);
        Page<Users> userPage = new PageImpl<>(userList);

        when(service.getAllUsers(any())).thenReturn(userPage);

        // Act
        ResponseEntity<Page<Users>> result = controller.getAllUsers(0, 10, "date");

        // Assert
        assertEquals(1, result.getBody().getContent().size());
        assertEquals("John", result.getBody().getContent().get(0).getUsername());

        verify(service, times(1)).getAllUsers(any());
        verifyNoMoreInteractions(service);
    }



    @Test
    public void testFindAll_V2_Positive() throws UserNotFoundException {
        // Arrange
        Users user = new Users(1, "John", "Doe", "john.doe@example.com", "test", "test", "test", "test", "test", "test", "test", "test", "test", null, null, null, null, null);
        List<Users> userList = Arrays.asList(user);
        Page<Users> userPage = new PageImpl<>(userList);

        when(service.getAllUsersByCity(anyString(), any())).thenReturn(userPage);

        // Act
        ResponseEntity<Page<Users>> result = controller.getAllUsersByCity("New York", 0, 10, "date");

        // Assert
        assertEquals(1, result.getBody().getContent().size());
        assertEquals("John", result.getBody().getContent().get(0).getUsername());

        verify(service, times(1)).getAllUsersByCity(anyString(), any());
        verifyNoMoreInteractions(service);
    }

    @Test
    public void testFindAll_V3_Positive() throws UserNotFoundException {
        // Arrange
        Users user = new Users(1, "John", "Doe", "john.doe@example.com", "test", "test", "test", "test", "test", "test", "test", "test", "test", null, null, null, null, null);

        when(service.getAllUsersById(anyInt())).thenReturn(user);

        // Act
        Users result = controller.getAllUsersById(1);

        // Assert
        assertEquals("John", result.getUsername());

        verify(service, times(1)).getAllUsersById(anyInt());
        verifyNoMoreInteractions(service);
    }

    @Test
    public void testUpdateUser_Positive() throws UserNotFoundException {
        // Arrange
        Users user = new Users(1, "John", "Doe", "john.doe@example.com", "test", "test", "test", "test", "test", "test", "test", "test", "test", null, null, null, null, null);

        when(service.updateUsers(anyInt(), any())).thenReturn(user);

        // Act
        ResponseEntity<Users> result = controller.updateUser(1, user);

        // Assert
        assertEquals("John", result.getBody().getUsername());

        verify(service, times(1)).updateUsers(anyInt(), any());
        verifyNoMoreInteractions(service);
    }

    @Test
    public void testDeleteUser_Positive() throws UserNotFoundException {
        // Arrange
        when(service.deleteUsers(anyInt())).thenReturn(true);

        // Act
        ResponseEntity<Users> result = controller.deleteUser(1);

        // Assert
        assertEquals(HttpStatus.OK, result.getStatusCode());

        verify(service, times(1)).deleteUsers(anyInt());
        verifyNoMoreInteractions(service);
    }


    
}

package com.remsnew.service.impl;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.remsnew.entity.Users;
import com.remsnew.exception.UserNotFoundException;
import com.remsnew.repository.UsersRepository;
import com.remsnew.service.impl.UserServiceImpl;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UsersRepository usersDao;

    @SuppressWarnings("deprecation")
	@BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddUsers() {
        Users user = new Users();
        when(usersDao.save(user)).thenReturn(user);
        Users result = userService.addUsers(user);
        assertEquals(user, result);
    }

    @Test
    public void testDeleteUsers() {
        int userId = 1;
        doNothing().when(usersDao).deleteById(userId);
        assertTrue(userService.deleteUsers(userId));
    }

    @Test
    public void testUpdateUsers() throws UserNotFoundException {
        int userId = 1;
        Users userUpdates = new Users();
        Users user = new Users();
        when(usersDao.findById(userId)).thenReturn(Optional.of(user));
        when(usersDao.save(user)).thenReturn(user);
        Users result = userService.updateUsers(userId, userUpdates);
        assertEquals(user, result);
    }

    @Test
    public void testUpdateUsers_UserNotFound() {
        int userId = 1;
        Users userUpdates = new Users();
        when(usersDao.findById(userId)).thenReturn(Optional.empty());
        assertThrows(UserNotFoundException.class, () -> userService.updateUsers(userId, userUpdates));
    }

    @Test
    public void testGetAllUsers() {
        Pageable pageable = mock(Pageable.class);
        @SuppressWarnings("unchecked")
		Page<Users> page = mock(Page.class);
        when(usersDao.findAll(pageable)).thenReturn(page);
        assertEquals(page, userService.getAllUsers(pageable));
    }

    @Test
    public void testGetAllUsersByCity() {
        String city = "Test City";
        Pageable pageable = mock(Pageable.class);
        @SuppressWarnings("unchecked")
		Page<Users> page = mock(Page.class);
        when(usersDao.findByCity(city, pageable)).thenReturn(page);
        assertEquals(page, userService.getAllUsersByCity(city, pageable));
    }

    @Test
    public void testGetAllUsersByEmail() {
        String email = "test@test.com";
        Users user = new Users();
        when(usersDao.findByEmail(email)).thenReturn(user);
        assertEquals(user, userService.getAllUsersByEmail(email));
    }

    @Test
    public void testGetAllUsersById() throws UserNotFoundException {
        int userId = 1;
        Users user = new Users();
        when(usersDao.findByUserid(userId)).thenReturn(Optional.of(user));
        assertEquals(user, userService.getAllUsersById(userId));
    }

    @Test
    public void testGetAllUsersById_UserNotFound() {
        int userId = 1;
        when(usersDao.findByUserid(userId)).thenReturn(Optional.empty());
        assertThrows(UserNotFoundException.class, () -> userService.getAllUsersById(userId));
    }
}

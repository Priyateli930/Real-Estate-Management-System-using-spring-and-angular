package com.remsnew.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.remsnew.controller.RegisterController;
import com.remsnew.entity.Users;
import com.remsnew.repository.UsersRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class RegisterControllerTest {

    @InjectMocks
    RegisterController registerController;

    @Mock
    UsersRepository usersDao;

    @Mock
    PasswordEncoder passwordEncoder;

    private MockMvc mockMvc;

    @SuppressWarnings("deprecation")
	@BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(registerController).build();
    }

    @Test
    public void testRegisterUser() throws Exception {
        Users user = new Users(); // initialize this with your data
        user.setUsername("testUser");
        user.setAddressline1("testUser");
        user.setAddressline2("testUser");
        user.setCity("testUser");
        user.setContactno("testUser");
        user.setDateofbirth("testUser");
        user.setEmail("testUser");
        user.setUsername("testUser");
        user.setUsertype("testUser");
        user.setVerified("testUser");
        user.setPincode("testUser");
        user.setState("testUser");
        user.setPasswordhash("testUser");
        

        when(usersDao.findByEmail(anyString())).thenReturn(null);
        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");
        when(usersDao.save(any(Users.class))).thenReturn(user);

        mockMvc.perform(post("/api/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(user)))
                .andExpect(status().isOk());
    }
}

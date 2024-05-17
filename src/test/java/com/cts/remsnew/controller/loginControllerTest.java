package com.remsnew.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.remsnew.controller.loginController;
import com.remsnew.security.AuthenticationRequest;
import com.remsnew.security.JwtUtil;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class loginControllerTest {

    @InjectMocks
    loginController loginController;

    @Mock
    AuthenticationManager authenticationManager;

    @Mock
    JwtUtil jwtUtil;

    @Mock
    UserDetailsService userDetailsService;

    private MockMvc mockMvc;

    @SuppressWarnings("deprecation")
	@BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(loginController).build();
    }

    @Test
    public void testCreateAuthenticationToken() throws Exception {
        AuthenticationRequest authenticationRequest = new AuthenticationRequest();
        authenticationRequest.setUsername("testUser");
        authenticationRequest.setPassword("testPassword");

        UserDetails userDetails = mock(UserDetails.class); // mock UserDetails

        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(null);
        when(userDetailsService.loadUserByUsername(anyString())).thenReturn(userDetails);
        when(jwtUtil.generateToken(any(UserDetails.class))).thenReturn("testToken");

        mockMvc.perform(post("/api/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(authenticationRequest)))
                .andExpect(status().isOk());
    }
}


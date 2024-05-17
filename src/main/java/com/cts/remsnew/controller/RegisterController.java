package com.remsnew.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.remsnew.entity.Users;
import com.remsnew.repository.UsersRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/register")
public class RegisterController {

	
    @Autowired
    private UsersRepository usersDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping
    public ResponseEntity<?> registerUser(@RequestBody Users user) {
        if (usersDao.findByEmail(user.getEmail()) != null) {
            return ResponseEntity.badRequest().body("User already exists");
        }
        user.setPasswordhash(passwordEncoder.encode(user.getPasswordhash()));
        usersDao.save(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
}

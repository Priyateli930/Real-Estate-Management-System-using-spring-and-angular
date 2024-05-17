package com.remsnew.security;


import java.util.Collections;



import java.util.Collections;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.remsnew.entity.Users;
import com.remsnew.repository.UsersRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {

   @Autowired
   private UsersRepository usersDao;

   @Override
   public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       Users user = usersDao.findByEmail(username);
       List<SimpleGrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority(user.getUsertype()));
       return new User(user.getEmail(), user.getPasswordhash(), authorities);
   }
}

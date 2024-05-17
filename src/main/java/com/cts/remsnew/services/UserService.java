package com.remsnew.services;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.remsnew.entity.Users;
import com.remsnew.exception.UserNotFoundException;



public interface UserService {

	public Users addUsers(Users user);


	public boolean deleteUsers(int userid); 

	public Users updateUsers(int userid, Users user) throws UserNotFoundException;

	public Page<Users> getAllUsers(Pageable pageable);
	
	public Page<Users> getAllUsersByCity(String city, Pageable pageable) throws UserNotFoundException ;
	
	public Users getAllUsersByEmail(String email);
	
	public Users getAllUsersById(int userid) throws UserNotFoundException;
	
}

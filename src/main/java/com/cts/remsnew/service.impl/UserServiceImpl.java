package com.remsnew.service.impl;


import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.remsnew.config.Constants;
import com.remsnew.entity.Users;
import com.remsnew.exception.UserNotFoundException;
import com.remsnew.repository.UsersRepository;
import com.remsnew.services.UserService;

import lombok.extern.slf4j.Slf4j;
 

@Slf4j
@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UsersRepository usersDao;

	public Users addUsers(Users user)
	{
		return usersDao.save(user);
	}

	@Override
	public boolean deleteUsers(int userid) {
		// TODO Auto-generated method stub
		usersDao.deleteById(userid);
		return true;
	}
	
	@Override
	public Users updateUsers(int userid, Users userUpdates) throws UserNotFoundException{
		// TODO Auto-generated method stub
		
		
		Optional<Users> optionalUser = usersDao.findById(userid);
		if(optionalUser.isPresent()) {
			Users user = optionalUser.get();
			if(userUpdates.getUsername() != null) {
				 user.setUsername(userUpdates.getUsername());
			}
			if(userUpdates.getUsertype() != null) {
				user.setUsertype(userUpdates.getUsertype());
			}
			if(userUpdates.getContactno() != null) {
				user.setContactno(userUpdates.getContactno());
			}
			if(userUpdates.getEmail() != null) {
				user.setEmail(userUpdates.getEmail());
			}
			if(userUpdates.getAddressline1() != null) {
				user.setAddressline1(userUpdates.getAddressline1());
			}
			if(userUpdates.getAddressline2() != null) {
				user.setAddressline2(userUpdates.getAddressline2());
			}
			if(userUpdates.getCity() != null) {
				user.setCity(userUpdates.getCity());
			}
			if(userUpdates.getState() != null) {
				user.setState(userUpdates.getState());
			}
			if(userUpdates.getPincode() != null) {
				user.setPincode(userUpdates.getPincode());
			}
			if(userUpdates.getDateofbirth() != null) {
				user.setDateofbirth(userUpdates.getDateofbirth());
			}
			if(userUpdates.getVerified() != null) {
				user.setVerified(userUpdates.getVerified());
			}
			if(userUpdates.getPasswordhash() != null) {
				user.setPasswordhash(userUpdates.getPasswordhash());
			}
			
			
		
			Users updatedUser = usersDao.save(user);
			log.info("User updated successfully: {}", updatedUser);
			return updatedUser;
		}
		throw new UserNotFoundException(Constants.user_not_found);
		
//		return  usersDao.save(user);
	}

	@Override
	public Page<Users> getAllUsers(Pageable pageable) {
		// TODO Auto-generated method stub
		return usersDao.findAll(pageable);
	}
	

	@Override
	public Page<Users> getAllUsersByCity(String city, Pageable pageable){

		 return usersDao.findByCity(city, pageable);

	}

	@Override
	public Users getAllUsersByEmail(String email) {
		// TODO Auto-generated method stub
		 return usersDao.findByEmail(email);
	}

	@Override
	public Users getAllUsersById(int userid) throws UserNotFoundException{
		// TODO Auto-generated method stub
		Optional<Users> optionalUser = usersDao.findByUserid(userid);
		if(optionalUser.isPresent()) {
			return optionalUser.get();
		}
		throw new UserNotFoundException(Constants.user_not_found_id);
	}
	
 
	
}

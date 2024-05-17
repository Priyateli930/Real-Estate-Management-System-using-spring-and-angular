package com.remsnew.controller;


 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.remsnew.entity.Users;
import com.remsnew.exception.UserNotFoundException;
import com.remsnew.services.UserService;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins = "http://localhost:4200")
@Slf4j
@RestController
@RequestMapping("/api/v1/rems/services/forall")
public class UsersController {

	@Autowired
	private UserService service;
	
	
	@GetMapping("/users/{pageNo}/{pageSize}/{sortBy}")
	public ResponseEntity<Page<Users>> getAllUsers(@PathVariable Integer pageNo, 
			@PathVariable Integer pageSize,
			@PathVariable String sortBy) {
		Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

		Page<Users> page = service.getAllUsers(pageable);
	    return new ResponseEntity<>(page, HttpStatus.OK);
	}
	
	@GetMapping("/usersbycity/{city}/{pageNo}/{pageSize}/{sortBy}")
	public ResponseEntity<Page<Users>> getAllUsersByCity(@PathVariable String city,
			@PathVariable Integer pageNo, 
			@PathVariable Integer pageSize,
			@PathVariable String sortBy) throws UserNotFoundException {
		log.info("START");
		  Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		  Page<Users> page = service.getAllUsersByCity(city, pageable);

		if(page.isEmpty())
		{			
			 return ResponseEntity.notFound().build();
		}
		else
		{
			log.debug("Users By City: {} ", page);
			log.info("END");
			return new ResponseEntity<>(page, HttpStatus.OK);
		}
		
	}
	
	
	
	@GetMapping("/usersbyid/{id}")
	public Users getAllUsersById(@PathVariable int id) throws UserNotFoundException {
		log.info("START");
		Users all = service.getAllUsersById(id);
		log.debug("Users By Id: {} ", all);
		log.info("END");
		return all;
	}
	
	
	@GetMapping("/userbyemail/{email}")
	public ResponseEntity<Users> getUserByEmail(@PathVariable String email) {
	    Users user = service.getAllUsersByEmail(email);
	    if (user != null) {
	        return new ResponseEntity<>(user, HttpStatus.OK);
	    } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}

	
	
	  @PutMapping("/updateusers/{id}")
	    public ResponseEntity<Users> updateUser(@PathVariable Integer id, @RequestBody Users userDetails) throws UserNotFoundException {
		  log.info("START");  


	        Users updatedUser = service.updateUsers(id, userDetails);
	        if(updatedUser == null) {
	            return ResponseEntity.notFound().build();
	        }
	        log.debug("Update Users By Id: {} ", updatedUser);
			log.info("END");
	        return ResponseEntity.ok(updatedUser);
	    }
	  

	  
	  @DeleteMapping("/deleteusers/{id}")
	    public ResponseEntity<Users> deleteUser(@PathVariable Integer id) throws UserNotFoundException {
		  log.info("START"); 
//	        Users user = service.getAllUsersById(id);
//	        if(user == null) {
//	            return ResponseEntity.notFound().build();
//	        }

	        service.deleteUsers(id);
	        log.debug("Delete Users By Id: {} ", id);
			log.info("END");
	        return ResponseEntity.ok().build();
	    }
	  
}

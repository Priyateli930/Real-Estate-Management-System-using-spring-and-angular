package com.remsnew.controller;

import java.util.List;




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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.remsnew.entity.ContactAssistance;
import com.remsnew.entity.PropImages;
import com.remsnew.exception.UserNotFoundException;
import com.remsnew.services.ContactAssistanceService;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins = "http://localhost:4200")
@Slf4j
@RestController
@RequestMapping("/api/v1/rems/services")
public class ContactAssistanceController {

	@Autowired 
	private ContactAssistanceService service;
	
	@GetMapping({"/owner/contactassistance/{pageNo}/{pageSize}/{sortBy}", "/broker/contactassistance/{pageNo}/{pageSize}/{sortBy}"})
	public ResponseEntity<Page<ContactAssistance>> getAllContactAssistance(@PathVariable Integer pageNo, 
			@PathVariable Integer pageSize,
			@PathVariable String sortBy) {
		Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

		Page<ContactAssistance> page = service.getAllContactAssistance(pageable);
	    return new ResponseEntity<>(page, HttpStatus.OK);

	}
	
	
	 @PutMapping("/tenant/updatecontactassistance/{id}")
	    public ResponseEntity<ContactAssistance> updateContactAst(@PathVariable Integer id, @RequestBody ContactAssistance ContactAssistanceDetails) throws UserNotFoundException {
		  log.info("START");  


		  ContactAssistance updatedContactAssistance = service.updateContactAssistance(id, ContactAssistanceDetails);
	        if(updatedContactAssistance == null) {
	            return ResponseEntity.notFound().build();
	        }
	        log.debug("Updated Contact Assistance By Id: {} ", updatedContactAssistance);
			log.info("END");
	        return ResponseEntity.ok(updatedContactAssistance);
	    }
		
		 @DeleteMapping("/tenant/deletecontactassistance/{id}")
		    public ResponseEntity<ContactAssistance> deleteContactAssistance(@PathVariable int id){
			  log.info("START"); 
			    boolean b = service.deleteContactAssistance(id);
//		        Ratings rating = service.getAllRatingsByPropertyId(id);
		        if(b == false) {
		            return ResponseEntity.notFound().build();
		        }

		        
		        log.debug("Delete Contact Assistance By Id: {} ", b);
				log.info("END");
		        return ResponseEntity.ok().build();
		    }

		 @PostMapping("/tenant/addcontactassistance")
			public ResponseEntity<ContactAssistance> addContactAssistance(@RequestBody ContactAssistance contactAssistance) {
				ContactAssistance addedContactAssistance = service.addContactAssistance(contactAssistance);
				return new ResponseEntity<>(addedContactAssistance, HttpStatus.CREATED);
			}
		 
		 
}

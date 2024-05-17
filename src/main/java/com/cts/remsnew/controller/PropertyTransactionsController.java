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

import com.remsnew.entity.PropImages;
import com.remsnew.entity.PropertyTransactions;
import com.remsnew.exception.PropertyNotFoundException;
import com.remsnew.exception.UserNotFoundException;
import com.remsnew.services.PropertyTransactionsService;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins = "http://localhost:4200")
@Slf4j
@RestController
@RequestMapping("/api/v1/rems/services")
public class PropertyTransactionsController {
 
	@Autowired
	private PropertyTransactionsService service;
	
	@GetMapping({"/owner/propertytransactions/{pageNo}/{pageSize}/{sortBy}", "/broker/propertytransactions/{pageNo}/{pageSize}/{sortBy}", "/forboth/propertytransactions/{pageNo}/{pageSize}/{sortBy}"})
	public ResponseEntity<Page<PropertyTransactions>> getAllPropertyTransactions(@PathVariable Integer pageNo, 
			@PathVariable Integer pageSize,
			@PathVariable String sortBy) {
		Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

		Page<PropertyTransactions> page = service.getAllPropertyTransactions(pageable);
	    return new ResponseEntity<>(page, HttpStatus.OK);


	}
	
	@GetMapping("/forboth/propertytransactionsbypropertyid/{id}")
	public ResponseEntity<PropertyTransactions> getAllPropertyTransactionsByPropertyId(@PathVariable int id) throws PropertyNotFoundException {
		log.info("START");
		PropertyTransactions all = service.getAllPropertyTransactionsByPropertyId(id);
		log.debug("Property Transactions By Property Id: {} ", all);
		log.info("END");
		return new ResponseEntity<>(all, HttpStatus.OK);
	}
	
	@GetMapping({"/owner/transactionsbytransactiondate/{date}/{pageNo}/{pageSize}/{sortBy}", "/broker/transactionsbytransactiondate/{date}/{pageNo}/{pageSize}/{sortBy}", "/forboth/transactionsbytransactiondate/{date}/{pageNo}/{pageSize}/{sortBy}"})
	public ResponseEntity<List<PropertyTransactions>> getAllPropertyTransactionsByTransactionDate(@PathVariable String date) throws PropertyNotFoundException {
		log.info("START");
		List<PropertyTransactions> all = service.getAllPropertyTransactionsByTransactionDate(date);
		log.debug("Property Transactions By Transaction Date: {} ", all);
		log.info("END");
		return new ResponseEntity<>(all, HttpStatus.OK);
	}
	
	@PutMapping({"/owner/updatepropertytransactions/{id}", "/broker/updatepropertytransactions/{id}", "/forboth/updatepropertytransactions/{id}"})
    public ResponseEntity<PropertyTransactions> updatePropertyTransactions(@PathVariable Integer id, @RequestBody PropertyTransactions transactionDetails) throws UserNotFoundException {
	  log.info("START");  


	  PropertyTransactions updatedTransactions = service.updatePropertyTransactions(id, transactionDetails);
        if(updatedTransactions == null) {
            return ResponseEntity.notFound().build();
        }
        log.debug("Updated Transactions By Id: {} ", updatedTransactions);
		log.info("END");
        return ResponseEntity.ok(updatedTransactions);
    }
	
	 @DeleteMapping({"/owner/deletepropertytransactions/{id}", "/broker/deletepropertytransactions/{id}", "/forboth/deletepropertytransactions/{id}"})
	    public ResponseEntity<PropertyTransactions> deletePropertyTransactions(@PathVariable int id){
		  log.info("START"); 
		    boolean b = service.deletePropertyTransactions(id);
//	        Ratings rating = service.getAllRatingsByPropertyId(id);
	        if(b == false) {
	            return ResponseEntity.notFound().build();
	        }

	        
	        log.debug("Delete Property Transactions By Id: {} ", b);
			log.info("END");
	        return ResponseEntity.ok().build();
	    }
	 
	 
	 @PostMapping({"/owner/addpropertytransactions", "/broker/addpropertytransactions", "/forboth/addpropertytransactions"})
		public ResponseEntity<PropertyTransactions> addPropertyTransaction(@RequestBody PropertyTransactions propertyTransactions) {
			PropertyTransactions addedPropertyTransaction = service.addPropertyTransactions(propertyTransactions);
			return new ResponseEntity<>(addedPropertyTransaction, HttpStatus.CREATED);
		}
	 
}

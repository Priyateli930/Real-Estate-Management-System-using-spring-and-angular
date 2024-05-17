package com.remsnew.controller;



import java.util.Optional;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.remsnew.entity.Properties;
import com.remsnew.entity.Users;
import com.remsnew.exception.PropertyNotFoundException;
import com.remsnew.exception.UserNotFoundException;
import com.remsnew.services.PropertiesService;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins = "http://localhost:4200")
@Slf4j
@RestController
@RequestMapping("/api/v1/rems/services/")
public class PropertiesController {
 
	@Autowired
	private PropertiesService service;
	
//	 @GetMapping({"owner/propertiesbyownerid", "broker/proeprtiesbybrokerid"})
//	    public ResponseEntity<Page<Properties>> getAllPropertiesByOwnerOrAgent(
//	            @RequestParam(defaultValue = "0") int pageNo,
//	            @RequestParam(defaultValue = "6") int pageSize,
//	            @RequestParam(defaultValue = "price") String sortBy,
//	            @RequestParam Users userId) {
//	        Page<Properties> properties = service.getAllPropertiesByOwnerOrAgent(pageNo, pageSize, sortBy, userId);
//	        return new ResponseEntity<>(properties, HttpStatus.OK);
//	    }
	
	@GetMapping("forboth/properties/{pageNo}/{pageSize}/{sortBy}")
	public ResponseEntity<Page<Properties>> getAllProperties(@PathVariable Integer pageNo, 
			@PathVariable Integer pageSize,
			@PathVariable String sortBy) {
		Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

		Page<Properties> page = service.getAllProperties(pageable);
	    return new ResponseEntity<>(page, HttpStatus.OK);

	}
	
	@GetMapping({"owner/propertiesbycity/{city}/{pageNo}/{pageSize}/{sortBy}", "broker/propertiesbycity/{city}/{pageNo}/{pageSize}/{sortBy}", "tenant/propertiesbycity/{city}/{pageNo}/{pageSize}/{sortBy}"})
	public ResponseEntity<Page<Properties>> getAllPropertiesByCity(@PathVariable String city, @PathVariable Integer pageNo, 
			@PathVariable Integer pageSize,
			@PathVariable String sortBy) {
		log.info("START");
		Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

		Page<Properties> page = service.getAllPropertiesByCity(city, pageable);
	   
		log.debug("Properties By City Names: {} ", page);
		log.info("END");
	    return new ResponseEntity<>(page, HttpStatus.OK);
	}
	
	
	@GetMapping({"owner/properties/{id}", "broker/properties/{id}", "tenant/properties/{id}"})
	public ResponseEntity<Properties> getAllPropertiesById(@PathVariable int id) {
	    Optional<Properties> property = service.getAllPropertiesById(id);
	    if (property.isPresent()) {
	        return new ResponseEntity<>(property.get(), HttpStatus.OK);
	    } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}
	
	@GetMapping({"owner/propertiesbypricerange/{min}/{max}/{pageNo}/{pageSize}/{sortBy}", "broker/propertiesbypricerange/{min}/{max}/{pageNo}/{pageSize}/{sortBy}", "tenant/propertiesbypricerange/{min}/{max}/{pageNo}/{pageSize}/{sortBy}"})
	public ResponseEntity<Page<Properties>> getAllPropertiesByPrice(@PathVariable double min, @PathVariable double max, @PathVariable String city, @PathVariable Integer pageNo, 
			@PathVariable Integer pageSize,
			@PathVariable String sortBy) throws PropertyNotFoundException {
		log.info("START");
		Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Page<Properties> page = service.getAllPropertiesByPrice(min,max,pageable);
		log.debug("Properties By Price Range: {} ", page);
		log.info("END");
		  return new ResponseEntity<>(page, HttpStatus.OK);
	}
	
	
	 @GetMapping({"owner/propertiesbytype/{propertytype}/{pageNo}/{pageSize}/{sortBy}", "broker/propertiesbytype/{propertytype}/{pageNo}/{pageSize}/{sortBy}", "tenant/propertiesbytype/{propertytype}/{pageNo}/{pageSize}/{sortBy}"})
	    public ResponseEntity<Page<Properties>> getPropertiesByPropertytype(@PathVariable String propertytype,@PathVariable Integer pageNo, 
				@PathVariable Integer pageSize,
				@PathVariable String sortBy) throws PropertyNotFoundException {
		 log.info("START");
		    Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

	        Page<Properties> properties = service.getAllPropertiesByPropertytype(propertytype,pageable);
	        if(properties == null) {
	            return ResponseEntity.notFound().build();
	        }
	    	log.debug("Properties By Property Type: {} ", properties);
			log.info("END");
	        return new ResponseEntity<>(properties, HttpStatus.OK);
	    }
	 
	 
	 @GetMapping({"owner/propertiesbyfilters/{city}/{minPrice}/{maxPrice}/{propstatus}/{propertytype}/{pageNo}/{pageSize}/{sortBy}", "broker/propertiesbyfilters/{city}/{minPrice}/{maxPrice}/{propstatus}/{propertytype}/{pageNo}/{pageSize}/{sortBy}", "tenant/propertiesbyfilters/{city}/{minPrice}/{maxPrice}/{propstatus}/{propertytype}/{pageNo}/{pageSize}/{sortBy}"})
	    public ResponseEntity<Page<Properties>> getPropertiesByFilters(
	        @PathVariable String city, @PathVariable double minPrice, @PathVariable double maxPrice,
	        @PathVariable String propstatus, @PathVariable String propertytype,@PathVariable Integer pageNo, 
			@PathVariable Integer pageSize,
			@PathVariable String sortBy) throws PropertyNotFoundException {

		    Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

	        Page<Properties> properties = service.getAllPropertiesByFilters(
	            city, minPrice, maxPrice, propstatus, propertytype, pageable);

	        return new ResponseEntity<>(properties, HttpStatus.OK);
	    }
	 
	 
	 @PutMapping({"owner/updateproperties/{propertyid}", "broker/updateproperties/{propertyid}", "forboth/updateproperties/{propertyid}"})
	    public ResponseEntity<Properties> updateProperties(@PathVariable Integer propertyid, @RequestBody Properties propertyDetails) throws UserNotFoundException {
		  log.info("START");  


		  Properties updatedProperties = service.updateProperty(propertyid, propertyDetails);
	        if(updatedProperties == null) {
	            return ResponseEntity.notFound().build();
	        }
	        log.debug("Updated Properties By Id: {} ", updatedProperties);
			log.info("END");
	        return ResponseEntity.ok(updatedProperties);
	    }
		
		 @DeleteMapping({"owner/deleteproperties/{propertyid}", "broker/deleteproperties/{propertyid}", "forboth/deleteproperties/{propertyid}"})
		    public ResponseEntity<Properties> deleteProperties(@PathVariable int propertyid){
			  log.info("START"); 
			    boolean b = service.deleteProperty(propertyid);
//		        Ratings rating = service.getAllRatingsByPropertyId(id);
		        if(b == false) {
		            return ResponseEntity.notFound().build();
		        }

		        
		        log.debug("Delete Properties By Id: {} ", b);
				log.info("END");
		        return ResponseEntity.ok().build();
		    }
	 
		 
		 
		 @PostMapping({"owner/addproperties", "broker/addproperties", "forboth/addproperties"})
			public ResponseEntity<Properties> addProperty(@RequestBody Properties properties) {
				Properties addedProperty = service.addProperty(properties);
				return new ResponseEntity<>(addedProperty, HttpStatus.CREATED);
			}
		 
		 

}

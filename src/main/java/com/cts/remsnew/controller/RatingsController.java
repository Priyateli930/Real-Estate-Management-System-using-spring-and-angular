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

import com.remsnew.entity.Ratings;
import com.remsnew.exception.UserNotFoundException;
import com.remsnew.services.RatingsService;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins = "http://localhost:4200")
@Slf4j
@RestController
@RequestMapping("/api/v1/rems/services/tenant")
public class RatingsController {

	@Autowired
	private RatingsService service;
	
	@GetMapping("/ratings/{pageNo}/{pageSize}/{sortBy}")
	public ResponseEntity<Page<Ratings>> getAllRatings(@PathVariable Integer pageNo, 
			@PathVariable Integer pageSize,
			@PathVariable String sortBy) {
		Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

		Page<Ratings> page = service.getAllRatings(pageable);
	    return new ResponseEntity<>(page, HttpStatus.OK);
	
	}
	
	@GetMapping("/showratingsbyproperty/{id}")
	public ResponseEntity<List<Ratings>> getAllRatingsByPropertyId(@PathVariable int id) {
		log.info("START");
		List<Ratings> all = service.getAllRatingsByPropertyId(id);
		log.debug("Property Images By Property Id: {} ", all);
		log.info("END");
		return new ResponseEntity<>(all, HttpStatus.OK);
	}
	 
	
	@PutMapping("/updateratings/{id}")
    public ResponseEntity<Ratings> updateRatings(@PathVariable Integer id, @RequestBody Ratings ratingDetails) throws UserNotFoundException {
	  log.info("START");  


        Ratings updatedRatings = service.updateRatings(id, ratingDetails);
        if(updatedRatings == null) {
            return ResponseEntity.notFound().build();
        }
        log.debug("Update Ratings By Id: {} ", updatedRatings);
		log.info("END");
        return ResponseEntity.ok(updatedRatings);
    }
  
	
	  @DeleteMapping("/deleteratings/{id}")
	    public ResponseEntity<Ratings> deleteRatings(@PathVariable int id){
		  log.info("START"); 
		    boolean b = service.deleteRatings(id);
//	        Ratings rating = service.getAllRatingsByPropertyId(id);
	        if(b == false) {
	            return ResponseEntity.notFound().build();
	        }

	        
	        log.debug("Delete Ratings By Id: {} ", b);
			log.info("END");
	        return ResponseEntity.ok().build();
	    }
	  
	  
	  
	  @PostMapping("/addratings")
		public ResponseEntity<Ratings> addRatings(@RequestBody Ratings ratings) {
			Ratings addedRatings = service.addRatings(ratings);
			return new ResponseEntity<>(addedRatings, HttpStatus.CREATED);
		}

	   
}

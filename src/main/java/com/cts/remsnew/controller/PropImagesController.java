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
import com.remsnew.exception.UserNotFoundException;
import com.remsnew.services.PropImagesService;

import lombok.extern.slf4j.Slf4j;
 
@CrossOrigin(origins = "http://localhost:4200")
@Slf4j
@RestController
@RequestMapping("/api/v1/rems/services")
public class PropImagesController {

	@Autowired
	private PropImagesService service;
	
	@GetMapping("/forall/propertyimages/{pageNo}/{pageSize}/{sortBy}")
	public ResponseEntity<Page<PropImages>> getAllPropImages(@PathVariable Integer pageNo, 
			@PathVariable Integer pageSize,
			@PathVariable String sortBy) {
		Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

		Page<PropImages> page = service.getAllPropImages(pageable);
	    return new ResponseEntity<>(page, HttpStatus.OK);

	}
	
	@GetMapping("/forall/showpropertyimages/{id}")
	public ResponseEntity<List<PropImages>> getAllPropImagesByPropertyId(@PathVariable int id) {
		log.info("START");
		List<PropImages> all = service.getAllPropImagesByPropertyId(id);
		log.debug("Property Images By Property Id: {} ", all);
		log.info("END");
		return new ResponseEntity<>(all, HttpStatus.OK);
	}
	
	@PutMapping({"/owner/updatepropertyimages/{id}", "/broker/updatepropertyimages/{id}"})
    public ResponseEntity<PropImages> updatePropertyImages(@PathVariable Integer id, @RequestBody PropImages imageDetails) throws UserNotFoundException {
	  log.info("START");  


        PropImages updatedImages = service.updatePropImages(id, imageDetails);
        if(updatedImages == null) {
            return ResponseEntity.notFound().build();
        }
        log.debug("Update Property Images By Id: {} ", updatedImages);
		log.info("END");
        return ResponseEntity.ok(updatedImages);
    }
	
	 @DeleteMapping({"/owner/deletepropertyimages/{id}", "/broker/deletepropertyimages/{id}"})
	    public ResponseEntity<PropImages> deletePropertyImages(@PathVariable int id){
		  log.info("START"); 
		    boolean b = service.deletePropImages(id);
//	        Ratings rating = service.getAllRatingsByPropertyId(id);
	        if(b == false) {
	            return ResponseEntity.notFound().build();
	        }

	        
	        log.debug("Delete Property Images By Id: {} ", b);
			log.info("END");
	        return ResponseEntity.ok().build();
	    }
	  
	 
	 @PostMapping({"/owner/propimages", "/broker/propimages"})
		public ResponseEntity<PropImages> addPropImages(@RequestBody PropImages propImages) {
			PropImages addedPropImages = service.addPropImages(propImages);
			return new ResponseEntity<>(addedPropImages, HttpStatus.CREATED);
		}

	 
}

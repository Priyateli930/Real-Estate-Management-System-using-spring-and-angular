package com.remsnew.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.remsnew.entity.Ratings;


public interface RatingsService {


	public Ratings addRatings(Ratings ratings);


	public boolean deleteRatings(int ratingid);

	public Ratings updateRatings(int userid, Ratings ratings);
	
	public Page<Ratings> getAllRatings(Pageable pageable);
	
	public List<Ratings> getAllRatingsByPropertyId(int propertyid);
	
}

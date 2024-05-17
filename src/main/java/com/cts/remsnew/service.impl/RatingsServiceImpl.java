package com.remsnew.service.impl;

import java.util.List;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.remsnew.entity.Ratings;
import com.remsnew.repository.RatingsRepository;
import com.remsnew.services.RatingsService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RatingsServiceImpl implements RatingsService{
	 
	@Autowired
	private RatingsRepository ratingsDao;



	@Override
	public Ratings addRatings(Ratings ratings) {
		// TODO Auto-generated method stub
		return ratingsDao.save(ratings);
	}

	@Override
	public boolean deleteRatings(int ratingid) {
		// TODO Auto-generated method stub
//		ratingsDao.delete(ratings);
		ratingsDao.deleteById(ratingid);
		return true;
	}

	@Override
	public Ratings updateRatings(int userid, Ratings ratingUpdates) {
		// TODO Auto-generated method stub
		Optional<Ratings> optionalRatings = ratingsDao.findById(userid);
		Ratings ratings = optionalRatings.get();
		if(optionalRatings.isPresent()) {
			
			if(ratingUpdates.getProperty() != null) {
				ratings.setProperty(ratingUpdates.getProperty());
			}
			if(ratingUpdates.getUser() != null) {
				ratings.setUser(ratingUpdates.getUser());
			}
			if(ratingUpdates.getRatingvalue() != 0) {
				ratings.setRatingvalue(ratingUpdates.getRatingvalue());
			}
			if(ratingUpdates.getFeedback() != null) {
				ratings.setFeedback(ratingUpdates.getFeedback());
			}
			if(ratingUpdates.getDateoffeedback() != null) {
				ratings.setDateoffeedback(ratingUpdates.getDateoffeedback());
			}
			
		
		}
		Ratings updatedRatings =  ratingsDao.save(ratings);
		log.info("Ratings updated successfully: {}", updatedRatings);
	
		return updatedRatings;
	}

	
	@Override
	public Page<Ratings> getAllRatings(Pageable pageable) {
		// TODO Auto-generated method stub
		return ratingsDao.findAll(pageable);
	}

	@Override
	public List<Ratings> getAllRatingsByPropertyId(int propertyid) {
		// TODO Auto-generated method stub
		return ratingsDao.findByProperty_Propertyid(propertyid);
	}

}

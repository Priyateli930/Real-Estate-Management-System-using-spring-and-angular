package com.remsnew.repository;


import java.util.List;




import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.remsnew.entity.Ratings;


@Repository
public interface RatingsRepository extends JpaRepository<Ratings, Integer>{
	public List<Ratings> findByProperty_Propertyid(int propertyid);
}

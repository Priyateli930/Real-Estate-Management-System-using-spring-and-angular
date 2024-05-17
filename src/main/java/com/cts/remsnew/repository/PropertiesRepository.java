package com.remsnew.repository;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.remsnew.entity.Properties;
import com.remsnew.entity.Users;


@Repository
public interface PropertiesRepository extends JpaRepository<Properties, Integer>{
	
	public Page<Properties> findByCity(String city, Pageable pageable);
	public Page<Properties> findByPriceBetween(double minPrice, double maxPrice, Pageable pageable);
	public Page<Properties> findByPropertytype(String propertytype, Pageable pageable);
	public Page<Properties> findByCityAndPriceBetweenAndPropstatusAndPropertytype(
	String city, double minPrice, double maxPrice, String propstatus, String propertytype, Pageable pageable);
	public Page<Properties> findByOwnerOrAgent(Users ownerId, Users agentId, Pageable pageable);
}

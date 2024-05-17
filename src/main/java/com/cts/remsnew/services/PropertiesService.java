package com.remsnew.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.remsnew.entity.Properties;
import com.remsnew.entity.Users;
import com.remsnew.exception.PropertyNotFoundException;



public interface PropertiesService {

	public Properties addProperty(Properties properties);


	public boolean deleteProperty(int propertyid);

	public Properties updateProperty(int propertyid, Properties properties);

	public Page<Properties> getAllProperties(Pageable pageable);
	
	public Page<Properties> getAllPropertiesByCity(String city, Pageable pageable);
	
	public Page<Properties> getAllPropertiesByPrice(double minPrice, double maxPrice, Pageable pageable) throws PropertyNotFoundException;
	
	public Page<Properties> getAllPropertiesByPropertytype(String propertytype, Pageable pageable) throws PropertyNotFoundException;
	
	public Page<Properties> getAllPropertiesByFilters(
	String city, double minPrice, double maxPrice, String propstatus, String propertytype, Pageable pageable) throws PropertyNotFoundException;


	public Optional<Properties> getAllPropertiesById(int propertyid);
	
	public Page<Properties> getAllPropertiesByOwnerOrAgent(int pageNo, int pageSize, String sortBy, Users userId);
	
}

package com.remsnew.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.remsnew.config.Constants;
import com.remsnew.entity.Properties;
import com.remsnew.entity.Users;
import com.remsnew.exception.PropertyNotFoundException;
import com.remsnew.repository.PropertiesRepository;
import com.remsnew.services.PropertiesService;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
public class PropertiesServiceImpl implements PropertiesService{

	@Autowired
	private PropertiesRepository propertiesDao;
	
	
	@Override
	public Page<Properties> getAllPropertiesByCity(String city, Pageable pageable) {

		Page<Properties> allProperties = propertiesDao.findByCity(city, pageable);
		
		return allProperties;
	}

	@Override
	public Properties addProperty(Properties properties) {
		// TODO Auto-generated method stub
		return propertiesDao.save(properties);
	}

	@Override
	public boolean deleteProperty(int propertyid) {
		// TODO Auto-generated method stub
		propertiesDao.deleteById(propertyid);
		return true;
	}

	@Override
	public Properties updateProperty(int propertyid, Properties propertyUpdates) {
		// TODO Auto-generated method stub
		Optional<Properties> optionalProperties = propertiesDao.findById(propertyid);
		Properties properties = optionalProperties.get();
		if(optionalProperties.isPresent()) {
			
			if(propertyUpdates.getOwner() != null) {
				properties.setOwner(propertyUpdates.getOwner());
			}
			if(propertyUpdates.getAgent() != null) {
				properties.setAgent(propertyUpdates.getAgent());
			}
			if(propertyUpdates.getAddressline1() != null) {
				properties.setAddressline1(propertyUpdates.getAddressline1());
			}
			if(propertyUpdates.getAddressline2() != null) {
				properties.setAddressline2(propertyUpdates.getAddressline2());
			}
			if(propertyUpdates.getCity() != null) {
				properties.setCity(propertyUpdates.getCity());
			}
			if(propertyUpdates.getState() != null) {
				properties.setState(propertyUpdates.getState());
			}
			if(propertyUpdates.getPincode() != null) {
				properties.setPincode(propertyUpdates.getPincode());
			}
			if(propertyUpdates.getSize() != null) {
				properties.setSize(propertyUpdates.getSize());
			}
			if(propertyUpdates.getPrice() != 0) {
				properties.setPrice(propertyUpdates.getPrice());
			}
			if(propertyUpdates.getPropertytype() != null) {
				properties.setPropertytype(propertyUpdates.getPropertytype());
			}
			if(propertyUpdates.getPropstatus() != null) {
				properties.setPropstatus(propertyUpdates.getPropstatus());
			}
		
		
		}
		Properties updatedproperties =  propertiesDao.save(properties);
		log.info("Properties updated successfully: {}", updatedproperties);
	
		return updatedproperties;
//		return propertiesDao.save(properties);
	}

	@Override
	public Page<Properties> getAllProperties(Pageable pageable) {
		// TODO Auto-generated method stub
		return propertiesDao.findAll(pageable);
	}

	@Override
	public Optional<Properties> getAllPropertiesById(int propertyid)
	{
		return propertiesDao.findById(propertyid);
	}
	
	@Override
	public Page<Properties> getAllPropertiesByPrice(double minPrice, double maxPrice, Pageable pageable) throws PropertyNotFoundException{
		// TODO Auto-generated method stub
    Page<Properties> allProperties = propertiesDao.findByPriceBetween(minPrice, maxPrice, pageable);
    
    if(allProperties == null || allProperties.isEmpty())
    {
        throw new PropertyNotFoundException(Constants.property_price_range);
    }
		
		return allProperties;
	}

	 @Override
	    public Page<Properties> getAllPropertiesByPropertytype(String propertytype, Pageable pageable) throws PropertyNotFoundException{
		 Page<Properties> allProperties =  propertiesDao.findByPropertytype(propertytype, pageable);
		 if(allProperties == null || allProperties.isEmpty())
		 {
		     throw new PropertyNotFoundException(Constants.property_price_range);
		 }
		 return allProperties;
	    }

	 
	 @Override
	    public Page<Properties> getAllPropertiesByFilters(
	        String city, double minPrice, double maxPrice, String propstatus, String propertytype, Pageable pageable) throws PropertyNotFoundException{

		 Page<Properties> allProperties =  propertiesDao.findByCityAndPriceBetweenAndPropstatusAndPropertytype(
	            city, minPrice, maxPrice, propstatus, propertytype, pageable);
		 if(allProperties == null || allProperties.isEmpty())
		 {
		     throw new PropertyNotFoundException(Constants.property_price_range);
		 }
		 return allProperties;
		 
	    }
	 
	 public Page<Properties> getAllPropertiesByOwnerOrAgent(int pageNo, int pageSize, String sortBy, Users userId) {
	        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
	        return propertiesDao.findByOwnerOrAgent(userId, userId, pageable);
	    }
	 
}

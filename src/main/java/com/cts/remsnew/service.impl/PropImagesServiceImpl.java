package com.remsnew.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.remsnew.entity.PropImages;
import com.remsnew.repository.PropImagesRepository;
import com.remsnew.services.PropImagesService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PropImagesServiceImpl implements PropImagesService{
 
	@Autowired
	private PropImagesRepository propImagesDao;

	
	

	@Override
	public PropImages addPropImages(PropImages propImages) {
		// TODO Auto-generated method stub
		return propImagesDao.save(propImages);
	}

	@Override
	public boolean deletePropImages(int propertyid) {
		// TODO Auto-generated method stub
		 propImagesDao.deleteById(propertyid);
		 return true;
	}

	@Override
	public PropImages updatePropImages(int imageid, PropImages propImageUpdates) {
		// TODO Auto-generated method stub
		Optional<PropImages> optionalImages = propImagesDao.findById(imageid);
		PropImages propImg = optionalImages.get();
		if(optionalImages.isPresent()) {
			
			if(propImageUpdates.getPropertyid() != 0) {
				propImg.setPropertyid(propImageUpdates.getPropertyid());
			}
			if(propImageUpdates.getPropImages() != null) {
				propImg.setPropImages(propImageUpdates.getPropImages());
			}
			
			
		}
		PropImages updatedPropImage = propImagesDao.save(propImg);
		log.info("User updated successfully: {}", updatedPropImage);
		return updatedPropImage;

	} 

	@Override
	public Page<PropImages> getAllPropImages(Pageable pageable) {
		// TODO Auto-generated method stub
		return propImagesDao.findAll(pageable);
	}

	@Override
	public List<PropImages> getAllPropImagesByPropertyId(int propertyid) {
		// TODO Auto-generated method stub
		return propImagesDao.findByPropertyid(propertyid);
	}
}

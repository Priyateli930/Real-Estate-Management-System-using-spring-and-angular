package com.remsnew.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.remsnew.entity.PropImages;


public interface PropImagesService {

	public PropImages addPropImages(PropImages propImages);

	public boolean deletePropImages(int propertyid);
	
	public PropImages updatePropImages(int imageid, PropImages propImages);

	public Page<PropImages> getAllPropImages(Pageable pageable);
	
	public List<PropImages> getAllPropImagesByPropertyId(int propertyid);
	
}

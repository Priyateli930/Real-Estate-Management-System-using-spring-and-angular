package com.remsnew.repository;

import java.util.List;




import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.remsnew.entity.PropImages;



@Repository
public interface PropImagesRepository extends JpaRepository<PropImages, Integer>{
	 public List<PropImages> findByPropertyid(int propertyid);
}

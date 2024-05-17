package com.remsnew.entity;


import jakarta.persistence.Column;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

 
@Entity
@Table(name = "propimages")
@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class PropImages {

	@Column(name = "propimages")
	@NonNull
	private String propImages;
	
	@Column(name = "pr_id")
	private int propertyid;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "imageid")
	private int imageid;
}

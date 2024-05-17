package com.remsnew.entity;

import jakarta.persistence.Column;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity 
@Table(name = "ratings")
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
public class Ratings {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@GenericGenerator(name = "native")
	@Column(name = "ratingid")
	private int ratingid;
	
	@ManyToOne
    @JoinColumn(name = "propertyid", referencedColumnName = "propertyid")
    private Properties property;
	

	
    @ManyToOne
    @JoinColumn(name = "userid", referencedColumnName = "userid")
    private Users user;
	
	@Column(name = "ratingvalue")
	private int ratingvalue;
	
	@Column(name = "feedback")
	@NonNull
	private String feedback;
	
	@Column(name = "dateoffeedback")
	@NonNull
	private String dateoffeedback;
}

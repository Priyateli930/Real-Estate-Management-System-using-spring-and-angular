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
@Table(name = "contactassistance")
@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class ContactAssistance {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	
	@ManyToOne
    @JoinColumn(name = "userid", referencedColumnName = "userid")
    private Users user;
	 
	@Column(name = "cname")
	@NonNull
	private String cname;
	
	@Column(name = "cemail")
	@NonNull
	private String cemail;
	
	@Column(name = "phone")
	@NonNull
	private String phone;
	
	@Column(name = "subject")
	@NonNull
	private String subject;
	
	@Column(name = "message")
	@NonNull
	private String message;
	
}

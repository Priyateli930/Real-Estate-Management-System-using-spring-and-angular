package com.remsnew.entity;



import java.util.Set;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;


 
@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class Users {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "userid")
	private int userid;
	@Column(name = "username")
	@NonNull
	private String username;
	@Column(name = "usertype")
	@NonNull
	private String usertype;
	@Column(name = "contactno")
	@NonNull
	private String contactno;
	@Column(name = "email")
	@NonNull
	private String email;
	@Column(name = "addressline1")
	@NonNull
	private String addressline1;
	@Column(name = "addressline2")
	@NonNull
	private String addressline2;
	@Column(name = "city")
	@NonNull
	private String city;
	@Column(name = "state")
	@NonNull
	private String state;
	@Column(name = "pincode")
	@NonNull
	private String pincode;
	@Column(name = "dateofbirth")
	@NonNull
	private String dateofbirth;
	@Column(name = "verified")
	@NonNull
	private String verified;
	@Column(name = "passwordhash")
	@NonNull
	private String passwordhash;
	
	
	
	
    
  
    @OneToMany(mappedBy = "owner")
    private Set<Properties> ownedProperties;

   
    @OneToMany(mappedBy = "agent")
    private Set<Properties> managedProperties;

    
    @OneToMany(mappedBy = "user")
    private Set<Ratings> ratings;

 
    @OneToMany(mappedBy = "buyertenant")
    private Set<PropertyTransactions> transactions;


    @OneToMany(mappedBy = "user")
    private Set<ContactAssistance> contactAssistances;
    
    
}

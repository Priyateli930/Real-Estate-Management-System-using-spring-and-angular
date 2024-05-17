package com.remsnew.entity;


 
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;


@Entity
@Table(name = "properties")
@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class Properties {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "propertyid", nullable=false)
	private int propertyid;
	
	@ManyToOne
    @JoinColumn(name = "ownerid", referencedColumnName = "userid")
    private Users owner;

    @ManyToOne
    @JoinColumn(name = "agentid", referencedColumnName = "userid")
    private Users agent;
	
	@Column(name = "addressline1")
	@NonNull
	private String addressline1;
	
	@Column(name = "addressline2")
	@NonNull
	private String addressline2;
	
	@Column(name = "state")
	@NonNull
	private String state;
	
	@Column(name = "city")
	@NonNull
	private String city;
	
	@Column(name = "pincode")
	@NonNull
	private String pincode;
	
	@Column(name = "size")
	@NonNull
	private Float size;
	
	@Column(name = "price")
	private double price;
	
	@Column(name = "propertytype")
	@NonNull
	private String propertytype;
	
	@Column(name = "propstatus")
	@NonNull
	private String propstatus;
	
	
	 @OneToMany(mappedBy = "property")
	 private Set<Ratings> ratings;
	 
	 @OneToMany(mappedBy = "property")
	 private Set<PropertyTransactions> transactions;
	
}

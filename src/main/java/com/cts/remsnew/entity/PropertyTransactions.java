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
@Table(name = "propertytransactions")
@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class PropertyTransactions {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "transactionid")
	private int transactionid;


	@ManyToOne
    @JoinColumn(name = "propertyid", referencedColumnName = "propertyid")
    private Properties property;

	
    @ManyToOne
    @JoinColumn(name = "buyertenantid", referencedColumnName = "userid")
    private Users buyertenant;

	private double salerentprice;
	@Column(name = "transactiondate")
	@NonNull
	private String transactiondate;
	@Column(name = "sellerid")

	private int sellerid;
}

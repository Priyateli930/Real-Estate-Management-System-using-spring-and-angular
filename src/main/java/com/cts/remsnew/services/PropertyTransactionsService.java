package com.remsnew.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.remsnew.entity.PropertyTransactions;
import com.remsnew.exception.PropertyNotFoundException;


public interface PropertyTransactionsService {

	public PropertyTransactions addPropertyTransactions(PropertyTransactions propertyTransactions);

	public boolean deletePropertyTransactions(int transactionid);

	public PropertyTransactions updatePropertyTransactions(int transactionid, PropertyTransactions propertyTransactions);

	public Page<PropertyTransactions> getAllPropertyTransactions(Pageable pageable);
	
	public List<PropertyTransactions> getAllPropertyTransactionsByTransactionDate(String transactiondate) throws PropertyNotFoundException ;
	
	public PropertyTransactions getAllPropertyTransactionsByPropertyId(int propertyid) throws PropertyNotFoundException;
	
}

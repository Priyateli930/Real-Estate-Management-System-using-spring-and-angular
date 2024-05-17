package com.remsnew.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.remsnew.config.Constants;
import com.remsnew.entity.PropertyTransactions;
import com.remsnew.exception.PropertyNotFoundException;
import com.remsnew.repository.PropertyTransactionsRepository;
import com.remsnew.services.PropertyTransactionsService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PropertyTransactionsServiceImpl implements PropertyTransactionsService{
 
	@Autowired
	private PropertyTransactionsRepository propertyTransactionsDao;
	
	@Override
	public PropertyTransactions addPropertyTransactions(PropertyTransactions propertyTransactions) {
		// TODO Auto-generated method stub
		return propertyTransactionsDao.save(propertyTransactions);
	}

	@Override
	public boolean deletePropertyTransactions(int transactionid) {
		// TODO Auto-generated method stub
		 propertyTransactionsDao.deleteById(transactionid);
		 return true;
	}

	@Override
	public PropertyTransactions updatePropertyTransactions(int transactionid, PropertyTransactions propertyTransactionsUpdates) {
		// TODO Auto-generated method stub
		Optional<PropertyTransactions> optionalPropertyTransactions = propertyTransactionsDao.findById(transactionid);
		PropertyTransactions ptransactions = optionalPropertyTransactions.get();
		if(optionalPropertyTransactions.isPresent()) {
			
			if(propertyTransactionsUpdates.getProperty() != null) {
				ptransactions.setProperty(propertyTransactionsUpdates.getProperty());
			}
			if(propertyTransactionsUpdates.getBuyertenant() != null) {
				ptransactions.setBuyertenant(propertyTransactionsUpdates.getBuyertenant());
			}
			if(propertyTransactionsUpdates.getSalerentprice() != 0) {
				ptransactions.setSalerentprice(propertyTransactionsUpdates.getSalerentprice());
			}
			if(propertyTransactionsUpdates.getTransactiondate() != null) {
				ptransactions.setTransactiondate(propertyTransactionsUpdates.getTransactiondate());
			}
			if(propertyTransactionsUpdates.getSellerid() != 0) {
				ptransactions.setSellerid(propertyTransactionsUpdates.getSellerid());
			}
			
		
		}
		PropertyTransactions updatedTransactions =  propertyTransactionsDao.save(ptransactions);
		log.info("Property Transactions updated successfully: {}", updatedTransactions);
	
		return updatedTransactions;
//		return propertyTransactionsDao.save(propertyTransactions);
	}

	@Override
	public Page<PropertyTransactions> getAllPropertyTransactions(Pageable pageable) {
		// TODO Auto-generated method stub
		return propertyTransactionsDao.findAll(pageable);
	}

	
	@Override
	public List<PropertyTransactions> getAllPropertyTransactionsByTransactionDate(String transactiondate) throws PropertyNotFoundException {
		// TODO Auto-generated method stub
		List<PropertyTransactions> propertyTransactionsList = propertyTransactionsDao.findByTransactiondate(transactiondate);
		if(!propertyTransactionsList.isEmpty()) {
			return propertyTransactionsList;
		}
		throw new PropertyNotFoundException(Constants.transaction_not_found_date);
	}

	@Override
	public PropertyTransactions getAllPropertyTransactionsByPropertyId(int propertyid) throws PropertyNotFoundException{
		// TODO Auto-generated method stub
		Optional<PropertyTransactions> optionalPropertyTransactions = propertyTransactionsDao.findByProperty_Propertyid(propertyid);
		if(optionalPropertyTransactions.isPresent()) {
			return optionalPropertyTransactions.get();
		}
		throw new PropertyNotFoundException(Constants.property_not_found);

	}

}

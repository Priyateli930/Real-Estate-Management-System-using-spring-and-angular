package com.remsnew.repository;

import java.util.List;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.remsnew.entity.PropertyTransactions;


@Repository
public interface PropertyTransactionsRepository extends JpaRepository<PropertyTransactions, Integer>{
	public List<PropertyTransactions> findByTransactiondate(String transactiondate);
	public Optional<PropertyTransactions> findByProperty_Propertyid(int propertyid);
}

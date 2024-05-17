package com.remsnew.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.remsnew.entity.ContactAssistance;


@Repository
public interface ContactAssistanceRepository extends JpaRepository<ContactAssistance, Integer>{

}

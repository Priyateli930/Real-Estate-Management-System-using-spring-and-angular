package com.remsnew.service.impl;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.remsnew.entity.ContactAssistance;
import com.remsnew.repository.ContactAssistanceRepository;
import com.remsnew.services.ContactAssistanceService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ContactAssistanceServiceImpl implements ContactAssistanceService{

	@Autowired
	private ContactAssistanceRepository contactAssistanceDao;
	
	@Override
	public ContactAssistance addContactAssistance(ContactAssistance contactAssistance) {
		// TODO Auto-generated method stub
		return contactAssistanceDao.save(contactAssistance);
	}

	@Override
	public boolean deleteContactAssistance(int id) {
		contactAssistanceDao.deleteById(id);
		return true;
	}

	@Override
	public ContactAssistance updateContactAssistance(int id, ContactAssistance contactAssistanceUpdates) {
		// TODO Auto-generated method stub
		Optional<ContactAssistance> optionalContactAssistance = contactAssistanceDao.findById(id);
		ContactAssistance contactAssistance = optionalContactAssistance.get();
		if(optionalContactAssistance.isPresent()) {
			
			if(contactAssistanceUpdates.getCname() != null) {
				contactAssistance.setCname(contactAssistanceUpdates.getCname());
			}
			if(contactAssistanceUpdates.getCemail() != null) {
				contactAssistance.setCemail(contactAssistanceUpdates.getCemail());
			}
			if(contactAssistanceUpdates.getPhone() != null) {
				contactAssistance.setPhone(contactAssistanceUpdates.getPhone());
			}
			if(contactAssistanceUpdates.getSubject() != null) {
				contactAssistance.setSubject(contactAssistanceUpdates.getSubject());
			}
			if(contactAssistanceUpdates.getMessage() != null) {
				contactAssistance.setMessage(contactAssistanceUpdates.getMessage());
			}
			if(contactAssistanceUpdates.getUser() != null) {
				contactAssistance.setUser(contactAssistanceUpdates.getUser());
			}
		
		}
		ContactAssistance updatedcontactAssistance =  contactAssistanceDao.save(contactAssistance);
		log.info("Contact Assistance updated successfully: {}", updatedcontactAssistance);
	
		return updatedcontactAssistance;
//		return contactAssistanceDao.save(contactAssistance);
	}

	@Override
	public Page<ContactAssistance> getAllContactAssistance(Pageable pageable) {
		// TODO Auto-generated method stub
		return contactAssistanceDao.findAll(pageable);
	}

	
}

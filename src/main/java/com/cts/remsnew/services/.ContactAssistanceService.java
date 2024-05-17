package com.remsnew.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.remsnew.entity.ContactAssistance;

 
public interface ContactAssistanceService {
	public ContactAssistance addContactAssistance(ContactAssistance contactAssistance);


	public boolean deleteContactAssistance(int id);

	public ContactAssistance updateContactAssistance(int id, ContactAssistance contactAssistance);

	public Page<ContactAssistance> getAllContactAssistance(Pageable pageable);
}

package com.prodigyInfotech.service;

import java.util.Objects;

import com.prodigyInfotech.dao.ContactsDaoImpl;
import com.prodigyInfotech.entity.ContactsEntity;

public class ContactService {
	
	ContactsDaoImpl contactsDaoImpl = new ContactsDaoImpl();
	
	public boolean validateDetails(String name,long number,String email) {
		boolean isNameValid = name != null && !name.isEmpty() && !name.isBlank();
		boolean isNumberValid = number > 0;
		boolean isEmailValid = email != null && !email.isEmpty() && !email.isBlank();
		
		boolean valid = isNameValid && isNumberValid && isEmailValid;
		
		if(valid) {
			ContactsEntity entity = new ContactsEntity(name, number, email);
			boolean result = contactsDaoImpl.saveContacts(name, number, email);
			return result;
		}
		
		return false;
	}
	
	public boolean validateName(String name) {
		return name != null && !name.isEmpty() && !name.isBlank();
	}
	
	public ContactsEntity getContactsByName(String name) {
		 ContactsEntity entity = contactsDaoImpl.getContactsByName(name);
		 return entity;
	}
	
	public boolean validateNumber(long number) {
		return number > 0  && String.valueOf(number).length()==10 ? true : false;
	}
	
	public ContactsEntity getContactsByNumber(long number) {
		return contactsDaoImpl.getContactsByNumber(number);
	}
	
	public boolean validateEditName(long number, String newName) {
	    return number > 0 && newName != null && newName.length() > 0 && String.valueOf(number).length() == 10;
	}

	
	public boolean editName(long number,String name) {
		return contactsDaoImpl.editName(number,name);
	
	}
	
	public boolean validateeditNumber(String newName,long number) {
		 return number > 0 && newName != null && newName.length() > 0 && String.valueOf(number).length() == 10;
	}
	
	public boolean editNumber(String name,long number) {
		return contactsDaoImpl.editNumber(name,number);
	
	}
	
	public boolean validatEeditEmail(String name,String email) {
		boolean isValidName = name != null && !name.isEmpty() && !name.isBlank();
		boolean isValidMail = email != null && !email.isEmpty() && !email.isBlank();
		
		boolean isValid =isValidName && isValidMail;
		if(isValid) {
			boolean result = contactsDaoImpl.editMail(name, email);
			return result;
		}
		return false;
	}
	
	public boolean validateEditContact(String oldName, String newName, long newNumber, String newEmail) {
		boolean isOldName = oldName != null && !oldName.isEmpty() && !oldName.isBlank();
		boolean isNewName = newName != null && !newName.isEmpty() && !newName.isBlank();
		boolean isNewNumber = newNumber > 0 && String.valueOf(newNumber).length() == 10;
		boolean isNewEmail = newEmail != null && !newEmail.isEmpty() && !newEmail.isBlank();
		
		boolean isContactValid = isOldName && isNewName && isNewNumber && isNewEmail;
		return isContactValid;
		
	}
	
	public boolean editContact(String oldName, String newName, long newNumber, String newEmail) {
		return contactsDaoImpl.editContact(oldName, newName, newNumber, newEmail);
	}
	
}

package com.prodigyInfotech.dao;

import java.util.List;

import com.prodigyInfotech.entity.ContactsEntity;

public interface ContactsDao {
	boolean saveContacts(String name,long number,String email);
	ContactsEntity getContactsByName(String name);
	ContactsEntity getContactsByNumber(long number);
	boolean editName(long number,String name);
	boolean editNumber(String name,long number);
	boolean editContact(String oldName,String newName,long newNumber,String newEmail);
	boolean editMail(String name,String mail);
	void deleteContactByNumber(long number);
	boolean deleteContactByName(String name);
	List<ContactsEntity> getAllContacts();
}

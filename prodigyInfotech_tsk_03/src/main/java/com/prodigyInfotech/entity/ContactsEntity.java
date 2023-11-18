package com.prodigyInfotech.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "contacts_table")
public class ContactsEntity implements Serializable {
	
	@Column(name = "name")
	private String contactName;
	
	@Column(name = "mobile_number")
	@Id
	private Long contactNumber;
	
	@Column(name = "email")
	private String contactEmail;
	
	public ContactsEntity() {
    }
	
	public ContactsEntity(String contactName, Long contactNumber, String contactEmail) {
		this.contactName = contactName;
		this.contactNumber = contactNumber;
		this.contactEmail = contactEmail;
	}
	public String getContactName() {
		return contactName;
	}
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	public Long getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(Long contactNumber) {
		this.contactNumber = contactNumber;
	}
	public String getContactEmail() {
		return contactEmail;
	}
	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}
	
	@Override
	public String toString() {
		return getContactName()+" "+getContactNumber()+" "+getContactEmail();
	}
	
}

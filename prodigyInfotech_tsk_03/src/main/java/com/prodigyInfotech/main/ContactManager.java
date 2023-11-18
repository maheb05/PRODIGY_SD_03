package com.prodigyInfotech.main;

import java.util.List;
import java.util.Scanner;

import com.prodigyInfotech.dao.ContactsDaoImpl;
import com.prodigyInfotech.entity.ContactsEntity;
import com.prodigyInfotech.service.ContactService;

public class ContactManager {

	public static void main(String[] args) {
		ContactService contactsService = new ContactService();
		ContactsDaoImpl impl = new ContactsDaoImpl();
		Scanner scanner = new Scanner(System.in);

		System.out.println("READ INSTRUCTIONS:");
		System.out.println("press 1 to add new contact");
		System.out.println("press 2 to display all contact details");
		System.out.println("press 3 to display the particular contact details by entering name");
		System.out.println("press 4 to display the particular contact details by entering mobile number");
		System.out.println("press 5 to to edit the contact");
		System.out.println("press 6 to to edit the only name of the contact");
		System.out.println("press 7 to to edit the only mobile number of the contact");
		System.out.println("press 8 to to edit the only email of the contact");
		System.out.println("press 9 to to delete the particular contact by entering name");
		System.out.println("press 10 to to delete the particular contact by entering moile number");
		System.out.println("press 0 to exit");
		System.out.println("Choose");
		int input = scanner.nextInt();
		scanner.nextLine();
		while (true) {

			if (input == 0) {
				System.out.println("Thank you");
				break;
			}

			// ADD NEW CONTACT
			if (input == 1) {
				System.out.println("Enter Name");
				String contactName = scanner.nextLine();
				System.out.println("Enter Mobile Number");
				long contactNumber = scanner.nextLong();
				scanner.nextLine();
				System.out.println("Enter Email");
				String contactMail = scanner.next();

				boolean validateDetails = contactsService.validateDetails(contactName, contactNumber, contactMail);
				if (validateDetails) {
					System.out.println("Contact Saved Successfully");
				} else {
					System.out
							.println("Something Went Wrong Please check the name,mobile number,email.....try again !");
				}
				break;
			}

			// DISPLAY ALL THE CONTACTS
			if (input == 2) {
				List<ContactsEntity> allContacts = impl.getAllContacts();
				if (allContacts != null && !allContacts.isEmpty()) {
					System.out.println("All Contacts ");
					System.out.printf("%-20s%-20s%-15s\n", "Name", "Mobile Number", "Email");
					System.out.println();
					for (ContactsEntity contact : allContacts) {
						System.out.printf("%-20s%-20s%-15s\n", contact.getContactName(), contact.getContactNumber(),
								contact.getContactEmail());
					}
				} else {
					System.out.println("Something Went Wrong Please Try Again ");
				}
				break;
			}

			// DISPLAY PARTICULAR CONTACT BY ENTERING NAME
			if (input == 3) {
				System.out.println("Enter Name");
				String contactName = scanner.nextLine();
				boolean validateName = contactsService.validateName(contactName);

				if (validateName) {
					ContactsEntity contactsByName = contactsService.getContactsByName(contactName);

					if (contactsByName != null) {
						System.out.println("Contact Details");
						System.out.println("NAME   : " + contactsByName.getContactName());
						System.out.println("Mobile : " + contactsByName.getContactNumber());
						System.out.println("Email  : " + contactsByName.getContactEmail());
					} else {
						System.out.println("No contacts found with this name " + "'" + contactName + "'");
					}
				} else {
					System.out.println("Name Should Not be NUll");
				}
				break;
			}

			// DISPLAY PARTICULAR CONTACT BY ENTERING NUMBER
			if (input == 4) {
				System.out.println("Enter mobile number");
				long contactNumber = scanner.nextLong();
				boolean validateNumber = contactsService.validateNumber(contactNumber);
				if (validateNumber) {
					ContactsEntity contactsByNumber = contactsService.getContactsByNumber(contactNumber);

					if (contactsByNumber != null) {
						System.out.println("Contact Detais");
						System.out.println("Name   : " + contactsByNumber.getContactName());
						System.out.println("Mobile : " + contactsByNumber.getContactNumber());
						System.out.println("Email  : " + contactsByNumber.getContactEmail());
					} else {
						System.out.println("No contacts found with this mobile number " + "'" + contactNumber + "'");
					}
				} else {
					System.out.println("Number Should not be Null");
				}
				break;
			}

			if (input == 5) {
				System.out.println("Enter Name");
				String contactName = scanner.nextLine();
				ContactsEntity entity = impl.getContactsByName(contactName);
				System.out.println(entity + " entity");
				if (entity != null) {
					System.out.println("Current Contact Details");
					System.out.println("Name   : " + entity.getContactName());
					System.out.println("Mobile : " + entity.getContactNumber());
					System.out.println("Email  : " + entity.getContactEmail());

					System.out.println("Edit Name");
					String newName = scanner.nextLine();
					System.out.println("Edit Number");
					long newNumber = scanner.nextLong();
					scanner.nextLine();
					System.out.println("Edit Email");
					String newEmail = scanner.next();

					boolean contact = contactsService.validateEditContact(contactName, newName, newNumber, newEmail);
					if (contact) {
						boolean contactEdited = contactsService.editContact(contactName, newName, newNumber, newEmail);
						if (contactEdited) {
							System.out.println("Contact Edited Successfully");
							System.out.println("Updated Contact Details");
						} else {
							System.out.println("Something Went Wrong");
						}
					} else {
						System.out.println("Check Name, Number, Email... should not be null");
					}
				} else {
					System.out.println("No contact found with the name: " + contactName);
				}
				break;
			}

			// EDIT ONLY NAME OF THE PARTICULAR CONTACT
			if (input == 6) {
				System.out.println("Enter mobile number");
				long contactNumber = scanner.nextLong();
				scanner.nextLine();
				ContactsEntity entity = contactsService.getContactsByNumber(contactNumber);
				System.out.println("Current Name " + entity.getContactName());
				System.out.println("Edit Name");
				String newName = scanner.nextLine();
				boolean validateEditName = contactsService.validateEditName(contactNumber, newName);

				if (validateEditName) {
					boolean editName = contactsService.editName(contactNumber, newName);

					if (editName) {
						ContactsEntity entity2 = contactsService.getContactsByNumber(contactNumber);
						System.out.println("Edit Succesfull");
						System.out.println("Contact Details ");
						System.out.println("Name   : " + entity2.getContactName());
						System.out.println("Mobile : " + entity2.getContactNumber());
						System.out.println("Email  : " + entity2.getContactEmail());
					} else {
						System.out.println("No contacts found with this " + "'" + contactNumber + "'" + " number");
					}
				} else {
					System.out.println("Number Should contains 10 degits & Name Should not be null ");
				}
				break;
			}

			// EDIT ONLY MOBILE NUMBER OF THE PARTICULAR CONTACT
			if (input == 7) {
				System.out.println("Enter Name");
				String contactName = scanner.nextLine();
				ContactsEntity contactsByName = contactsService.getContactsByName(contactName);
				System.out.println("Current Mobile Number:" + contactsByName.getContactNumber());
				System.out.println("Edit Number ");
				long newNumber = scanner.nextLong();
				scanner.nextLine();
				boolean validNumber = contactsService.validateeditNumber(contactName, newNumber);
				if (validNumber) {
					boolean editNumber = contactsService.editNumber(contactName, newNumber);
					if (editNumber) {
						ContactsEntity entity = contactsService.getContactsByName(contactName);
						System.out.println("Edit SuccessFull");
						System.out.println("Name   : " + entity.getContactName());
						System.out.println("Mobile : " + entity.getContactNumber());
						System.out.println("Email  : " + entity.getContactEmail());
					} else {
						System.out.println("No contacts found with this " + "'" + contactName + "'" + " name");
					}
				} else {
					System.out.println("Name Should not be null ");
				}
				break;
			}

			// EDIT ONLY Email OF THE PARTICULAR CONTACT
			if (input == 8) {
				System.out.println("Enter Name");
				String contactName = scanner.nextLine();
				ContactsEntity contactsByName = contactsService.getContactsByName(contactName);
				System.out.println("Current Email " + contactsByName.getContactEmail());

				System.out.println("Edit Email ");
				String newEmail = scanner.next();

				boolean validatEeditEmail = contactsService.validatEeditEmail(contactName, newEmail);
				if (validatEeditEmail) {
					ContactsEntity entity = contactsService.getContactsByName(contactName);
					System.out.println("Edit Successfull");
					System.out.println("Name   : " + entity.getContactName());
					System.out.println("Mobile : " + entity.getContactNumber());
					System.out.println("Email  : " + entity.getContactEmail());
				} else {
					System.out.println("No contacts found with this " + "'" + contactName + "'" + " name");
				}
				break;
			}

			// DELETE PARTICULAR CONTACTS BY ENTERING NAME
			if (input == 9) {
				System.out.println("Enter Name");
				String contactName = scanner.nextLine();
				boolean validateName = contactsService.validateName(contactName);
				if (validateName) {
					ContactsEntity entity = contactsService.getContactsByName(contactName);
					if (entity != null) {
						System.out.println("Contact Details");
						System.out.println("Name   : " + entity.getContactName());
						System.out.println("Mobile : " + entity.getContactNumber());
						System.out.println("Email  : " + entity.getContactEmail());
						boolean deleteContactByName = impl.deleteContactByName(contactName);
						if (deleteContactByName) {
							System.out.println("SuccessFully Deleted The Contact ");
						}
					} else {
						System.out.println("No Contact Found ");
					}
				} else {
					System.out.println("Name Should not be null");
				}
				break;
			}

			// DELETE PARTICULAR CONTACTS BY ENTERING MOBILE NUMBER
			if (input == 10) {
				System.out.println("Enter Mobile Number");
				long contactNumber = scanner.nextLong();
				boolean validateNumber = contactsService.validateNumber(contactNumber);
				if (validateNumber) {
					ContactsEntity entity = impl.getContactsByNumber(contactNumber);
					if (entity != null) {
						System.out.println("Contact Details");
						System.out.println("Name   : " + entity.getContactName());
						System.out.println("Mobile : " + entity.getContactNumber());
						System.out.println("Email  : " + entity.getContactEmail());
						impl.deleteContactByNumber(contactNumber);
						System.out.println("SuccessFully Deleted The Contact ");
					} else {
						System.out.println("No Contacts");
					}
				} else {
					System.out.println("Contact must contains 10 digits");
				}
				break;
			}

		}

//		

	}
}

package com.prodigyInfotech.dao;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.prodigyInfotech.entity.ContactsEntity;

public class ContactsDaoImpl implements ContactsDao {

	Scanner scan = new Scanner(System.in);

	public boolean saveContacts(String name, long number, String email) {

		Session session = null;
		boolean isValid = false;
		try {
			SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
					.addAnnotatedClass(ContactsEntity.class).buildSessionFactory();
			session = sessionFactory.openSession();
			Transaction transaction = session.beginTransaction();

			ContactsEntity entity = new ContactsEntity(name, number, email);
			Serializable save = session.save(entity);
			transaction.commit();

			isValid = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return isValid;
	}

	public ContactsEntity getContactsByName(String name) {
		Session session = null;
		ContactsEntity entity = null;
		try {
			SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
					.addAnnotatedClass(ContactsEntity.class).buildSessionFactory();
			session = sessionFactory.openSession();

			String hql = "from ContactsEntity cm where cm.contactName = :name";
			Query query = session.createQuery(hql);
			query.setParameter("name", name);

			entity = (ContactsEntity) query.uniqueResult();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return entity;
	}

	public ContactsEntity getContactsByNumber(long number) {

		Session session = null;
		ContactsEntity entity = null;
		try {
			SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
					.addAnnotatedClass(ContactsEntity.class).buildSessionFactory();
			session = sessionFactory.openSession();
			entity = session.get(ContactsEntity.class, number);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}

		return entity;
	}

	public boolean editName(long number, String newName) {
		Session session = null;
		boolean isValid = false;
		try {
			SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
					.addAnnotatedClass(ContactsEntity.class).buildSessionFactory();
			session = sessionFactory.openSession();
			Transaction transaction = session.beginTransaction();
			ContactsEntity entity = session.get(ContactsEntity.class, number);

			if (entity != null) {
				entity.setContactName(newName);
				transaction.commit();
				isValid = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}

		return isValid;
	}

	public boolean editNumber(String name, long number) {

		Session session = null;
		boolean isValid = false;
		try {

			SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
					.addAnnotatedClass(ContactsEntity.class).buildSessionFactory();
			session = sessionFactory.openSession();
			Transaction transaction = session.beginTransaction();
			String hql = "update ContactsEntity ce set ce.contactNumber=:number where ce.contactName=:name";
			Query query = session.createQuery(hql);
			query.setParameter("number", number);
			query.setParameter("name", name);
			int i = query.executeUpdate();
			transaction.commit();
			if (i > 0) {
				isValid = true;
			} else {
				isValid = false;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
		return isValid;
	}

	public boolean editMail(String name, String mail) {
		Session session = null;
		boolean isValid = false;
		try {
			SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
					.addAnnotatedClass(ContactsEntity.class).buildSessionFactory();
			session = sessionFactory.openSession();
			Transaction transaction = session.beginTransaction();
			String UPDATE_QUERY = "update ContactsEntity ce set ce.contactEmail=:mail where ce.contactName=:name";
			Query query = session.createQuery(UPDATE_QUERY);
			query.setParameter("mail", mail);
			query.setParameter("name", name);
			int i = query.executeUpdate();
			transaction.commit();

			if (i > 0) {
				isValid = true;
			} else {
				isValid = false;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}

		return isValid;
	}

	public List<ContactsEntity> getAllContacts() {

		Session session = null;
		LinkedList<ContactsEntity> contactList = new LinkedList<ContactsEntity>();
		try {
			SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
					.addAnnotatedClass(ContactsEntity.class).buildSessionFactory();
			session = sessionFactory.openSession();
			String SELECT_QUERY = "from ContactsEntity";
			Query query = session.createQuery(SELECT_QUERY);
			List<ContactsEntity> contacts = query.getResultList();

			for (ContactsEntity contact : contacts) {
				contactList.add(contact);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
		return contactList;
	}

	public void deleteContactByNumber(long number) {

		Session session = null;
		try {
			SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
					.addAnnotatedClass(ContactsEntity.class).buildSessionFactory();
			session = sessionFactory.openSession();
			Transaction transaction = session.beginTransaction();
			ContactsEntity entity = session.get(ContactsEntity.class, number);
			session.delete(entity);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	public boolean deleteContactByName(String name) {
		boolean isValid = false;
		Session session = null;

		try {

			SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
					.addAnnotatedClass(ContactsEntity.class).buildSessionFactory();
			session = sessionFactory.openSession();
			Transaction transaction = session.beginTransaction();
			String DELETE_QUERY = "delete from ContactsEntity ce where ce.contactName=:name";
			Query query = session.createQuery(DELETE_QUERY);
			query.setParameter("name", name);
			int row = query.executeUpdate();
			transaction.commit();
			if (row > 0) {
				isValid = true;
			} else {
				isValid = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			if(session != null) {
				session.close();
			}
		}
		return isValid;
	}

	public boolean editContact(String oldName, String newName, long newNumber, String newEmail) {
		Session session = null;
		boolean isValid = false;
		try {
			SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(ContactsEntity.class).buildSessionFactory();
			session = sessionFactory.openSession();
			Transaction transaction = session.beginTransaction();
			String UPDATE_QUERY = "update ContactsEntity ce set ce.contactName=:newName , ce.contactNumber=:newNumber,ce.contactEmail=:newEmail where ce.contactName=:oldName";
			Query query = session.createQuery(UPDATE_QUERY);
			query.setParameter("newName", newName);
			query.setParameter("newNumber", newNumber);
			query.setParameter("newEmail", newEmail);
			query.setParameter("oldName", oldName);
			int rows = query.executeUpdate();
			transaction.commit();
			isValid = rows > 0;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if(session != null) {
				session.close();
			}
		}
		
		return isValid;
	}

}

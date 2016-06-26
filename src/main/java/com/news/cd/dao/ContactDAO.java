package com.news.cd.dao;

import java.util.List;

import com.news.cd.entities.Contact;

public interface ContactDAO {
	boolean addContact(Contact contact);

	boolean deleteContact(int id);

	boolean changeContactStatus(int contactId, int statusId);

	Contact getContactById(int id);

	List<Contact> getContactByUser(String username);

	List<Contact> getAll();

	List<Contact> getInboxMails(int indexMin, int numbOfResult);

	List<Contact> getSentMails(int indexMin, int numbOfResult);

	List<Contact> getDraftMails(int indexMin, int numbOfResult);

	List<Contact> getTrashMails(int indexMin, int numbOfResult);
	
	int countAll(int... sttIds);


}

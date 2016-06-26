package com.news.cd.services;

import java.util.List;
import java.util.Map;

import com.news.cd.entities.Contact;

public interface ContactService {
	boolean addContact(Contact contact);

	boolean deleteContact(int id);

	boolean deleteContacts(int[] ids);

	boolean trashContact(int id);

	boolean draftContact(Contact contact);

	boolean sendContact(Contact contact);

	boolean readContact(int contactId);

	Contact getContactById(int id);

	List<Contact> getContactByUser(String username);

	int countAll(String repo);
	
	Map<String, Object> getMailsForPagination(String repo, int currentPage, int numbOfRecordsPerPage, int numbOfPageShow);

}

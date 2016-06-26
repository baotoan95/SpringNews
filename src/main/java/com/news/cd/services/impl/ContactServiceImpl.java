package com.news.cd.services.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.news.cd.constants.ContactStatus;
import com.news.cd.dao.ContactDAO;
import com.news.cd.dao.StatusDAO;
import com.news.cd.entities.Contact;
import com.news.cd.helper.PaginationHelper;
import com.news.cd.services.ContactService;

@Service("contactService")
@Transactional
public class ContactServiceImpl implements ContactService {
	@Autowired
	private ContactDAO contactDAO;
	@Autowired
	private StatusDAO statusDAO;

	@Override
	public boolean addContact(Contact contact) {
		return contactDAO.addContact(contact);
	}

	@Override
	public boolean deleteContact(int id) {
		int statusId = getContactById(id).getStatus().getSttId();
		if (ContactStatus.TRASH_READ == statusId
				|| ContactStatus.TRASH_UNREAD == statusId) {
			return contactDAO.deleteContact(id);
		} else {
			return trashContact(id);
		}
	}

	@Override
	public boolean trashContact(int contactId) {
		int statusId = contactDAO.getContactById(contactId).getStatus()
				.getSttId();
		if (ContactStatus.WAIT_TO_READ == statusId) {
			return contactDAO.changeContactStatus(contactId,
					ContactStatus.TRASH_UNREAD);
		} else {
			return contactDAO.changeContactStatus(contactId,
					ContactStatus.TRASH_READ);
		}
	}

	@Override
	public boolean draftContact(Contact contact) {
		contact.setSubmitDate(new Date());
		contact.setStatus(statusDAO.getStatus(ContactStatus.DRAFT));
		return contactDAO.addContact(contact);
	}

	@Override
	public boolean sendContact(Contact contact) {
		contact.setSubmitDate(new Date());
		contact.setStatus(statusDAO.getStatus(ContactStatus.SENT));
		return contactDAO.addContact(contact);
	}

	@Override
	public boolean readContact(int contactId) {
		try {
			int statusId = getContactById(contactId).getStatus().getSttId();
			if (ContactStatus.WAIT_TO_READ == statusId) {
				contactDAO.changeContactStatus(contactId,
						ContactStatus.SEEN_AND_NO_ANSWER);
			} else if (ContactStatus.TRASH_UNREAD == statusId) {
				contactDAO.changeContactStatus(contactId,
						ContactStatus.TRASH_READ);
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean deleteContacts(int[] ids) {
		int statusId;
		for (int i : ids) {
			statusId = getContactById(i).getStatus().getSttId();
			if (ContactStatus.TRASH_READ == statusId
					|| ContactStatus.TRASH_UNREAD == statusId) {
				if (deleteContact(i) == false) {
					return false;
				}
			} else {
				if (trashContact(i) == false) {
					return false;
				}
			}
		}

		return true;
	}

	@Override
	public Contact getContactById(int id) {
		return contactDAO.getContactById(id);
	}

	@Override
	public List<Contact> getContactByUser(String username) {
		return contactDAO.getContactByUser(username);
	}

	@Override
	public Map<String, Object> getMailsForPagination(String repo, int currentPage, 
			int numbOfRecordsPerPage, int numbOfPageShow) {
		Map<String, Object> result = new HashMap<String, Object>();
		PaginationHelper paginationHelper = new PaginationHelper(countAll(repo), currentPage, 
				numbOfRecordsPerPage, numbOfPageShow);
		List<Contact> contacts = new ArrayList<Contact>();
		String title = "";
		if (ContactStatus.REPO_INBOX.equals(repo)) {
			contacts = contactDAO.getInboxMails(paginationHelper.getStartIndex(), paginationHelper.getMaxResult());
			title = "Hộp thư đến";
		} else if (ContactStatus.REPO_SENT.equals(repo)) {
			contacts = contactDAO.getSentMails(paginationHelper.getStartIndex(), paginationHelper.getMaxResult());
			title = "Thư đã gửi";
		} else if (ContactStatus.REPO_DRAFT.equals(repo)) {
			contacts = contactDAO.getDraftMails(paginationHelper.getStartIndex(), paginationHelper.getMaxResult());
			title = "Hộp thư nháp";
		} else {
			contacts = contactDAO.getTrashMails(paginationHelper.getStartIndex(), paginationHelper.getMaxResult());
			title = "Thư đã xóa";
		}

		java.util.Collections.sort(contacts);
		java.util.Collections.reverse(contacts);
		
		result.put("data", contacts);
		result.put("title", title);
		result.put("html", paginationHelper.getHTML().replaceAll("link",
				"contact.html?repo=" + repo + "&p=").replace("classContainer",
				"class='pagination pagination-sm no-margin pull-right'"));

		return result;
	}

	@Override
	public int countAll(String repo) {
		if (ContactStatus.REPO_INBOX.equals(repo)) {
			return contactDAO.countAll(ContactStatus.WAIT_TO_READ, ContactStatus.SEEN_AND_ANSWER, ContactStatus.SEEN_AND_NO_ANSWER);
		} else if (ContactStatus.REPO_SENT.equals(repo)) {
			return contactDAO.countAll(ContactStatus.SENT);
		} else if (ContactStatus.REPO_DRAFT.equals(repo)) {
			return contactDAO.countAll(ContactStatus.DRAFT);
		} else if(ContactStatus.REPO_TRASH.equals(repo)){
			return contactDAO.countAll(ContactStatus.TRASH_READ, ContactStatus.TRASH_UNREAD);
		} else {
			return contactDAO.countAll(ContactStatus.WAIT_TO_READ);
		}
	}

}

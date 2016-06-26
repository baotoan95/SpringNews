package com.news.cd.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.news.cd.dao.AbstractDAO;
import com.news.cd.dao.ContactDAO;
import com.news.cd.dao.StatusDAO;
import com.news.cd.entities.Contact;

@Repository("contactDAO")
public class ContactDAOImpl extends AbstractDAO<Integer, Contact> implements
		ContactDAO {
	@Autowired
	private StatusDAO statusDAO;

	@Override
	public boolean addContact(Contact contact) {
		return persist(contact);
	}

	@Override
	public boolean deleteContact(int id) {
		return delete(getContactById(id));
	}

	@Override
	public boolean changeContactStatus(int contactId, int statusId) {
		try {
			Contact contact = getContactById(contactId);
			contact.setStatus(statusDAO.getStatus(statusId));
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	@Override
	public Contact getContactById(int id) {
		return getByKey(id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Contact> getContactByUser(String username) {
		return createEntityCriteria().add(Restrictions.eq("user", username))
				.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Contact> getAll() {
		return createEntityCriteria().list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Contact> getInboxMails(int index, int numbOfResult) {
		Criteria criteria = createEntityCriteria();
		criteria.setFirstResult(index);
		criteria.setMaxResults(numbOfResult);
		criteria.createAlias("status", "status", JoinType.INNER_JOIN);
		Criterion s1 = Restrictions.eq("status.sttId", 1);
		Criterion s2 = Restrictions.eq("status.sttId", 2);
		Criterion s3 = Restrictions.eq("status.sttId", 3);

		LogicalExpression orExp = Restrictions.or(Restrictions.or(s1, s2), s3);
		criteria.add(orExp);

		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Contact> getSentMails(int index, int numbOfResult) {
		Criteria criteria = createEntityCriteria();
		criteria.setFirstResult(index);
		criteria.setMaxResults(numbOfResult);
		criteria.createAlias("status", "status", JoinType.INNER_JOIN);
		criteria.add(Restrictions.eq("status.sttId", 12));

		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Contact> getDraftMails(int index, int numbOfResult) {
		Criteria criteria = createEntityCriteria();
		criteria.setFirstResult(index);
		criteria.setMaxResults(numbOfResult);
		criteria.createAlias("status", "status", JoinType.INNER_JOIN);
		criteria.add(Restrictions.eq("status.sttId", 13));

		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Contact> getTrashMails(int index, int numbOfResult) {
		Criteria criteria = createEntityCriteria();
		criteria.setFirstResult(index);
		criteria.setMaxResults(numbOfResult);
		criteria.createAlias("status", "status", JoinType.INNER_JOIN);
		Criterion s1 = Restrictions.eq("status.sttId", 10);
		Criterion s2 = Restrictions.eq("status.sttId", 11);
		LogicalExpression orExp = Restrictions.or(s1, s2);
		criteria.add(orExp);

		return criteria.list();
	}
	
	@Override
	public int countAll(int... sttIds) {
		Criteria criteria = createEntityCriteria();
		Disjunction or = Restrictions.disjunction();
		for(int sttId : sttIds) {
			or.add(Restrictions.eq("status.sttId", sttId));
		}
		criteria.add(or);
		return ((Number) criteria.setProjection(
				Projections.rowCount()).uniqueResult()).intValue();
	}

}

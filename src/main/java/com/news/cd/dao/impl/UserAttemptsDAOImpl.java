package com.news.cd.dao.impl;

import java.util.Date;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.news.cd.dao.AbstractDAO;
import com.news.cd.dao.UserAttemptsDAO;
import com.news.cd.entities.UserAttempts;

@Repository("userAttemptsDAO")
@Transactional
public class UserAttemptsDAOImpl extends AbstractDAO<Integer, UserAttempts> implements UserAttemptsDAO {

	@Override
	public UserAttempts updateFailAttempts(String email) {
		UserAttempts userAttempts = getUserAttempts(email);
		if(null == userAttempts) {
			if(persist(userAttempts = new UserAttempts(email, 1, new Date()))) {
				return userAttempts;
			}
			return null;
		}
		
		userAttempts.setAttempts(userAttempts.getAttempts() + 1);
		userAttempts.setLastModified(new Date());
		return userAttempts;
	}

	@Override
	public UserAttempts getUserAttempts(String email) {
		try {
			Criteria criteria = createEntityCriteria();
			criteria.add(Restrictions.eq("username", email));
			criteria.setMaxResults(1);
			return (UserAttempts) criteria.uniqueResult();
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public boolean resetUserAttemps(String email) {
		return delete(getUserAttempts(email));
	}

}

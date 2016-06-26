package com.news.cd.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.news.cd.dao.AbstractDAO;
import com.news.cd.dao.UserDAO;
import com.news.cd.dao.UserRoleDAO;
import com.news.cd.entities.User;
import com.news.cd.utils.EncodeUtils;

@Repository("userDAO")
public class UserDAOImpl extends AbstractDAO<String, User> implements UserDAO {
	@Autowired
	private UserRoleDAO userRoleDAO;
	
	@Override
	public User findUserByEmail(String email) {
		return getByKey(email);
	}

	@Override
	public User findUserByEmailWithRoles(String email) {
		User user = findUserByEmail(email);
		try {
			Hibernate.initialize(user.getRoles());
		} catch (Exception e) {
			//e.printStackTrace();
		}
		return user;
	}

	@Override
	public boolean addUser(User user) {
		user.setPassword(EncodeUtils.md5(user.getPassword()));
		return persist(user);
	}

	@Override
	public boolean updateUser(User user) {
		try {
			User userOld = getByKey(user.getEmail());
			userOld.setAddress(user.getAddress());
			userOld.setAvatarUrl(user.getAvatarUrl());
			userOld.setBirthDay(user.getBirthDay());
			userOld.setNonLocked(user.isNonLocked());
			userOld.setCode(user.getCode());
			userOld.setEnabled(user.isEnabled());
			userOld.setFirstName(user.getFirstName());
			userOld.setLastName(user.getLastName());
			userOld.setGender(user.getGender());
			userOld.setPassword(user.getPassword());
			userOld.setRoles(user.getRoles());
			userOld.setDesc(user.getDesc());
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean deleteUser(String email) {
		return delete(getByKey(email));
	}
	
	//chauphi90
	@Override
	public int countUserByRole(int role) {
		Criteria criteria = createCriteriaByRole(role);
		// SELECT count(*) from ...
		Projection projection = Projections.rowCount();
		criteria.setProjection(projection);

		return ((Number) criteria.uniqueResult()).intValue();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getUsersLimitResultByRole(int startIndex,
			int numbOfResult, int role) {
		Criteria criteria = createCriteriaByRole(role);

		criteria.setFirstResult(startIndex);
		criteria.setMaxResults(numbOfResult);
		return criteria.list();
	}
	
	private Criteria createCriteriaByRole(int role) {
		Criteria criteria = createEntityCriteria();
		
		criteria.createAlias("roles", "userRole", JoinType.INNER_JOIN);
		criteria.createAlias("userRole.role", "role", JoinType.INNER_JOIN);
		criteria.add(Restrictions.eq("role.roleId", role));

		return criteria;
	}

}

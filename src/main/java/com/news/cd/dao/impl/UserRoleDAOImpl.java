package com.news.cd.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.news.cd.dao.AbstractDAO;
import com.news.cd.dao.UserDAO;
import com.news.cd.dao.UserRoleDAO;
import com.news.cd.entities.UserRole;

@Repository("userRoleDAO")
public class UserRoleDAOImpl extends AbstractDAO<Integer, UserRole> implements
		UserRoleDAO {
	@Autowired
	private UserDAO userDAO;

	@Override
	public boolean addUserRole(UserRole userRole) {
		return persist(userRole);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserRole> getUserRolesByUsername(String username) {
		return createEntityCriteria().add(Restrictions.eq("user", username)).list();
	}

	@Override
	public boolean deleteUserRole(int id) {
		return delete(getByKey(id));
	}

	@Override
	public boolean deleteUserRoleByUsername(String username) {
		try {
			Query query = getSession().createQuery("delete UserRole where user.email = :username");
			query.setParameter("username", username).executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserRole> getUserRolesById(int id) {
		return createEntityCriteria().add(Restrictions.eq("roleId", id)).list();
	}

}

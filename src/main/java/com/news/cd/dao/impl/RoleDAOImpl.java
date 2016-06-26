package com.news.cd.dao.impl;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.news.cd.dao.AbstractDAO;
import com.news.cd.dao.RoleDAO;
import com.news.cd.entities.Role;

@Repository("roleDAO")
public class RoleDAOImpl extends AbstractDAO<Integer, Role> implements RoleDAO {

	@Override
	public boolean addRole(Role role) {
		return persist(role);
	}

	@Override
	public boolean deleteRole(int id) {
		return delete(getByKey(id));
	}

	@Override
	public Role getRoleByName(String name) {
		return (Role) createEntityCriteria().add(Restrictions.eq("name", name)).list().get(0);
	}

	// chauphi90
	@SuppressWarnings("unchecked")
	@Override
	public List<Role> getRoles() {
		
		return createEntityCriteria().list();
	}

}

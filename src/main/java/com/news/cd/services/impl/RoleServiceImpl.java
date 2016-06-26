package com.news.cd.services.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.news.cd.dao.RoleDAO;
import com.news.cd.entities.Role;
import com.news.cd.services.RoleService;

@Service("roleService")
@Transactional
public class RoleServiceImpl implements RoleService {
	@Autowired
	private RoleDAO roleDAO;

	@Override
	public boolean addRole(Role role) {
		return roleDAO.addRole(role);
	}

	@Override
	public boolean deleteRole(int id) {
		return roleDAO.deleteRole(id);
	}

	@Override
	public Role getRoleByName(String name) {
		return roleDAO.getRoleByName(name);
	}

	// chauphi90
	@Override
	public List<Role> getRoles() {
		return roleDAO.getRoles();
	}

}

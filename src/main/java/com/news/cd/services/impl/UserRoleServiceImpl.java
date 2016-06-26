package com.news.cd.services.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.news.cd.dao.UserRoleDAO;
import com.news.cd.entities.UserRole;
import com.news.cd.services.UserRoleService;

@Service("userRoleService")
@Transactional
public class UserRoleServiceImpl implements UserRoleService {
	@Autowired
	private UserRoleDAO userRoleDAO;

	@Override
	public boolean deleteUserRole(int id) {
		return userRoleDAO.deleteUserRole(id);
	}

	@Override
	public boolean deleteUserRoleByUsername(String username) {
		return userRoleDAO.deleteUserRoleByUsername(username);
	}

	@Override
	public boolean addUserRole(UserRole userRole) {
		return userRoleDAO.addUserRole(userRole);
	}

	@Override
	public List<UserRole> getUserRolesByUsername(String username) {
		return userRoleDAO.getUserRolesByUsername(username);
	}

	@Override
	public List<UserRole> getUserRolesById(int id) {
		return userRoleDAO.getUserRolesById(id);
	}

}

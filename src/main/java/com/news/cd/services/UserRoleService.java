package com.news.cd.services;

import java.util.List;

import com.news.cd.entities.UserRole;

public interface UserRoleService {

	boolean deleteUserRole(int id);

	boolean deleteUserRoleByUsername(String username);

	boolean addUserRole(UserRole userRole);

	List<UserRole> getUserRolesByUsername(String username);
	
	// for initBinder UserHandler
	List<UserRole> getUserRolesById(int id);
}

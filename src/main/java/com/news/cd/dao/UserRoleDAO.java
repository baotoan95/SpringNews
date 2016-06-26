package com.news.cd.dao;

import java.util.List;

import com.news.cd.entities.UserRole;

public interface UserRoleDAO {
//	boolean updateUserRole(UserRole userRole);

	boolean deleteUserRole(int id);

	boolean deleteUserRoleByUsername(String username);

	boolean addUserRole(UserRole userRole);

	List<UserRole> getUserRolesByUsername(String username);

	List<UserRole> getUserRolesById(int id);

}

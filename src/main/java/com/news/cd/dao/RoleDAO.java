package com.news.cd.dao;

import java.util.List;

import com.news.cd.entities.Role;

public interface RoleDAO {
	boolean addRole(Role role);
	boolean deleteRole(int id);
	Role getRoleByName(String name);
	
	//chauphi90
	List<Role> getRoles();
}

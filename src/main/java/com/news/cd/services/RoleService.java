package com.news.cd.services;

import java.util.List;

import com.news.cd.entities.Role;

public interface RoleService {
	boolean addRole(Role role);
	boolean deleteRole(int id);
	Role getRoleByName(String name);
	
	//chauphi90
	List<Role> getRoles();
}

package com.news.cd.dao;

import java.util.List;

import com.news.cd.entities.User;

public interface UserDAO {
	User findUserByEmail(String email);

	User findUserByEmailWithRoles(String email);

	boolean addUser(User user);

	boolean updateUser(User user);

	boolean deleteUser(String email);

	// chauphi90
	int countUserByRole(int role);

	List<User> getUsersLimitResultByRole(int startIndex, int numbOfResult, int role);
}

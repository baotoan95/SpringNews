package com.news.cd.services;

import java.util.Map;
import java.util.Set;

import org.springframework.web.multipart.MultipartFile;

import com.news.cd.entities.User;
import com.news.cd.entities.UserRole;

public interface UserService {
	User findUserByEmail(String email);

	User findUserByEmailWithRoles(String email);

	boolean addUser(User user);
	
	boolean addUser(User user, MultipartFile file, int[] roles);

	boolean updateUser(User user);

	boolean updateUser(User user,MultipartFile file,int[] roles);

	boolean deleteUser(String email);

	boolean activeAccount(String username, String code);

	String checkCodeError(String email, String code);

	String checkUserError(String email);

	String rememberPass(String email);

	String changePassword(String email, String code, String newPassword);
	
	// chauphi90
	Map<String, Object> getForPaginationByRole(int currentPage, int numbOfRecordsPerPage, int numbOfPageShow, int role);

	Map<String, String> listGender();

	Set<UserRole> listSelectedUserRole(User user, int[] roles);

	
}

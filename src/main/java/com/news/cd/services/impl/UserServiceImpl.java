package com.news.cd.services.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.news.cd.constants.RoleCode;
import com.news.cd.dao.RoleDAO;
import com.news.cd.dao.UserDAO;
import com.news.cd.dao.UserRoleDAO;
import com.news.cd.entities.Role;
import com.news.cd.entities.User;
import com.news.cd.entities.UserRole;
import com.news.cd.helper.FileUpload;
import com.news.cd.helper.PaginationHelper;
import com.news.cd.services.UserService;
import com.news.cd.utils.EncodeUtils;
import com.news.cd.utils.GenerationCommon;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private MailService mailService;
	@Autowired
	private RoleDAO roleDAO;
	@Autowired
	private UserRoleDAO userRoleDAO;

	@Override
	public User findUserByEmail(String email) {
		return userDAO.findUserByEmail(email);
	}

	@Override
	public User findUserByEmailWithRoles(String email) {
		return userDAO.findUserByEmailWithRoles(email);
	}

	@Override
	public boolean addUser(User user) {
		return userDAO.addUser(user);
	}
	
	@Override
	public boolean addUser(User user, MultipartFile file, int[] roles) {
		if (!file.isEmpty()) {
			FileUpload fileUpload = new FileUpload();
			String destination = "\\resources\\upload\\images\\";
			user.setAvatarUrl(fileUpload.process(file, destination));
		}

		user.setRoles(listSelectedUserRole(user, roles));
		user.setNonLocked(true);
		user.setEnabled(true);
		
		return addUser(user);
	}

	@Override
	public boolean updateUser(User user) {
		return userDAO.updateUser(user);
	}

	@Override
	public boolean updateUser(User user, MultipartFile file, int[] roles) {
		User oldUser = findUserByEmailWithRoles(user.getEmail());
		String oldPass = oldUser.getPassword();
		if (!user.getPassword().equals(oldPass)) {
			user.setPassword(EncodeUtils.md5(user.getPassword()));
		}

		// Upload image file
		if (null != file && !file.isEmpty()) {
			FileUpload fileUpload = new FileUpload();
			String destination = "/resources/upload/images/";
			user.setAvatarUrl(fileUpload.process(file, destination));
		}

		// Update roles for this user
		if (null != roles) {
			userRoleDAO.deleteUserRoleByUsername(user.getEmail());
			user.setRoles(listSelectedUserRole(user, roles));
		} else {
			user.setRoles(oldUser.getRoles());
		}

		return updateUser(user);
	}

	@Override
	public boolean deleteUser(String email) {
		return userDAO.deleteUser(email);
	}

	@Override
	public boolean activeAccount(String username, String code) {
		User user = findUserByEmail(username);
		if (user.getCode().equals(code)) {
			user.setEnabled(true);
			if (updateUser(user)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public String checkCodeError(String email, String code) {
		String userError = checkUserError(email);
		if (null == userError) {
			if (!findUserByEmail(email).getCode().equals(code)) {
				return "Mã đã hết hiệu lực";
			}
		}
		return userError;
	}

	@Override
	public String checkUserError(String email) {
		User user = findUserByEmail(email);
		if (null != user) {
			if (!user.isNonLocked()) {
				return "Tài khoản đã bị khóa";
			}
		} else {
			return "Tài khoản không tồn tại trong hệ thống";
		}
		return null;
	}

	@Override
	public String rememberPass(String email) {
		String error = checkUserError(email);
		if (null == error) {
			User user = findUserByEmail(email);
			user.setCode(GenerationCommon.generateCode(20));
			if (updateUser(user)) {
				StringBuilder mailBody = new StringBuilder();
				mailBody.append("Xin chào bạn, chúng tôi nhận được yêu cầu nhắc mật khẩu của bạn, vui lòng truy cập link sau để đổi mật khẩu: <br/>");
				mailBody.append("http://localhost:8080/SpringNews/changePass?uid=");
				mailBody.append(user.getEmail());
				mailBody.append("&k=");
				mailBody.append(user.getCode());
				mailService.sender(new String[] { email },
						"SpringNews - Remember password", mailBody.toString());
				return "Yêu cầu đã được gửi, vui lòng kiểm tra email của bạn";
			}
		}
		return error;
	}

	@Override
	public String changePassword(String email, String code, String newPassword) {
		String error = checkCodeError(email, code);
		if (null == error) {
			User user = findUserByEmail(email);
			user.setPassword(EncodeUtils.md5(newPassword));
			user.setCode(GenerationCommon.generateCode(20));
			user.setRoles(user.getRoles());
			if (updateUser(user)) {
				StringBuilder mailBody = new StringBuilder();
				mailBody.append("Chào bạn, mật khẩu của bạn đã được thay đổi thành công");
				mailService.sender(new String[] { email },
						"SpringNews - Remember password", mailBody.toString());
				return "Mật khẩu đã được thay đổi";
			}
		}
		return error;
	}

	// chauphi90
	@Override
	public Map<String, Object> getForPaginationByRole(int currentPage,
			int numbOfRecordsPerPage, int numbOfPageShow, int role) {
		Map<String, Object> rs = new HashMap<>();
		PaginationHelper paginationHelper = new PaginationHelper(
				userDAO.countUserByRole(role), currentPage,
				numbOfRecordsPerPage, numbOfPageShow);
		rs.put("html", paginationHelper.getHTML());
		rs.put("data",
				userDAO.getUsersLimitResultByRole(
						paginationHelper.getStartIndex(),
						paginationHelper.getMaxResult(), role));
		return rs;
	}

	// Tạo list gender cho trang add và edit user
	@Override
	public Map<String, String> listGender() {
		Map<String, String> gendersMap = new HashMap<String, String>();
		gendersMap.put("M", "Nam");
		gendersMap.put("F", "Nữ");
		gendersMap.put("O", "Khác");

		return gendersMap;
	}

	@Override
	public Set<UserRole> listSelectedUserRole(User user, int[] roles) {
		Set<UserRole> urs = new HashSet<UserRole>();
		Role role;
		for (int i : roles) {
			switch (i) {
			case RoleCode.ADMIN:
				role = roleDAO.getRoleByName("ROLE_ADMIN");
				break;
			case RoleCode.WRITER:
				role = roleDAO.getRoleByName("ROLE_WRITER");
				break;
			default:
				role = roleDAO.getRoleByName("ROLE_AUDIENT");
				break;
			}
			urs.add(new UserRole(user, role));
		}

		return urs;
	}

}

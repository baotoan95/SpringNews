package com.news.cd.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import com.news.cd.dao.UserAttemptsDAO;
import com.news.cd.dao.UserDAO;
import com.news.cd.entities.User;
import com.news.cd.entities.UserAttempts;
import com.news.cd.utils.DateUtils;
import com.news.cd.utils.EncodeUtils;

@Component("authenticationProvider")
public class CustomAuthenticationProvider implements AuthenticationProvider {
	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private UserAttemptsDAO userAttemptsDAO;
	@Autowired
	private UserDAO userDAO;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		// Get info from login form
		String username = authentication.getName();
		String password = (String) authentication.getCredentials();
		// Process info to get UserDetails
//		UserDetails user = userDetailsService.loadUserByUsername(username);
		User user = (User)userDetailsService.loadUserByUsername(username);
		
		// User not found
		if (null == user) {
			throw new BadCredentialsException("Tài khoản không tồn tại trong hệ thống");
		}
		
		if(password.length() == 0) {
			throw new BadCredentialsException("Vui lòng nhập mật khẩu");
		}
		
		// Password mismatch
		if(!user.getPassword().equalsIgnoreCase(EncodeUtils.md5(password))) {
			UserAttempts userAttempts = userAttemptsDAO.updateFailAttempts(username);
			if(userAttempts.getAttempts() > 2) { // Locked user
				// Update status user to "blocked" in database
				User userNew = userDAO.findUserByEmail(user.getUsername());
				userNew.setNonLocked(false);
				userDAO.updateUser(userNew);
				// Update UserDetails
				user = (User)userDetailsService.loadUserByUsername(user.getUsername());
			} else {
				throw new BadCredentialsException("Sai mật khẩu lần " + userAttempts.getAttempts() + " lúc " + DateUtils.dateToString(userAttempts.getLastModified(), "HH:mm:ss - dd/MM/yy"));
			}
		}
		
		// Account not active
		if(!user.isEnabled()) {
			throw new DisabledException("Tài khoản chưa được kích hoạt");
		}
		
		// Account locked
		if(!user.isAccountNonLocked()) {
			throw new LockedException("Tài khoản đã bị khóa");
		}
		
		// Login success and have login fail at some time then reset 
		if(null != userAttemptsDAO.getUserAttempts(user.getUsername())) {
			userAttemptsDAO.resetUserAttemps(user.getUsername());
		}
		return new UsernamePasswordAuthenticationToken(user, password, user.getAuthorities());
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return true;
	}

}

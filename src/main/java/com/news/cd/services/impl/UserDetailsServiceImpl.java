package com.news.cd.services.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.news.cd.dao.UserDAO;
import com.news.cd.entities.User;

@Service("userDetailsService")
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {
	private UserDAO userDAO;
	
	@Autowired
	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userDAO.findUserByEmailWithRoles(email);
		if(null == user) {
			return null;
		}
//		return buildUserDetailsForAuthentication(user, buildAuthorities(user.getRoles()));
		return user;
	}
	
//	private UserDetails buildUserDetailsForAuthentication(User user, List<GrantedAuthority> grantedAuthorities) {
//		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), user.isEnabled(), true, true, user.isNonLocked(), grantedAuthorities);
//	}
//	
//	private List<GrantedAuthority> buildAuthorities(Set<UserRole> userRoles) {
//		List<GrantedAuthority> authorities = new ArrayList<>();
//		for(UserRole userRole : userRoles) {
//			authorities.add(new SimpleGrantedAuthority(userRole.getRole().getName()));
//		}
//		return authorities;
//	}

}

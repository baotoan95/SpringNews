package com.news.cd.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@SuppressWarnings("serial")
@Entity
@Table(name = "users_roles", uniqueConstraints = @UniqueConstraint(columnNames = {"ur_role", "ur_user" }))
public class UserRole implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ur_id")
	private int userRoleId;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ur_user")
	private User user;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ur_role")
	private Role role;
	
	public UserRole(User user, int roleId) {
		this.role = new Role(roleId);
		this.user = user;
	}

	public UserRole() {
		// TODO Auto-generated constructor stub
	}
	
	public UserRole(Role role) {
		this.role = role;
	}

	public UserRole(User user, Role role) {
		this.user = user;
		this.role = role;
	}

	public int getuserRoleId() {
		return userRoleId;
	}

	public void setuserRoleId(int userRoleId) {
		this.userRoleId = userRoleId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "UserRole [userRoleId=" + userRoleId + ", role="
				+ role + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof UserRole) {
			UserRole other = (UserRole) obj;
			return other.role.getRoleId() == this.role.getRoleId() 
					&& this.user.getEmail().equals(other.user.getEmail());
		}
		return false;
	}
}

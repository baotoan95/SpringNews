package com.news.cd.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "user_attempts")
public class UserAttempts implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_attemp_id")
	private int id;
	@Column(name = "username")
	private String username;
	@Column(name = "attemps")
	private int attempts;
	@Column(name = "lastModified")
	private Date lastModified;

	public UserAttempts() {
		// TODO Auto-generated constructor stub
	}

	public UserAttempts(String username, int attempts, Date lastModified) {
		this.username = username;
		this.attempts = attempts;
		this.lastModified = lastModified;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getAttempts() {
		return attempts;
	}

	public void setAttempts(int attempts) {
		this.attempts = attempts;
	}

	public Date getLastModified() {
		return lastModified;
	}

	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}

	@Override
	public String toString() {
		return "UserAttempts [id=" + id + ", username=" + username
				+ ", attempts=" + attempts + ", lastModified=" + lastModified
				+ "]";
	}

}

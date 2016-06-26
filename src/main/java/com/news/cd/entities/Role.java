package com.news.cd.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "roles")
public class Role implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "r_id")
	private int roleId;
	@Column(name = "r_name")
	private String name;
	@Column(name = "r_desc")
	private String desc;
	
	public Role(int roleId) {
		this.roleId = roleId;
	}

	public Role() {
		// TODO Auto-generated constructor stub
	}

	public Role(String name, String desc) {
		this.name = name;
		this.desc = desc;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	@Override
	public String toString() {
		return "Role [roleId=" + roleId + ", name=" + name + ", desc=" + desc
				+ "]";
	}

}

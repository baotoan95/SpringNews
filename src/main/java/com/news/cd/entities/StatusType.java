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
@Table(name = "status_types")
public class StatusType implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sttt_id")
	private int sttTypeId;
	@Column(name = "sttt_name")
	private String name;

	public StatusType() {
		// TODO Auto-generated constructor stub
	}

	public StatusType(String name) {
		this.name = name;
	}

	public int getSttTypeId() {
		return sttTypeId;
	}

	public void setSttTypeId(int sttTypeId) {
		this.sttTypeId = sttTypeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "StatusType [sttTypeId=" + sttTypeId + ", name=" + name + "]";
	}

}

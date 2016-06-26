package com.news.cd.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "status")
public class Status implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "stt_id")
	private int sttId;
	@Column(name = "stt_name")
	private String name;
	@Column(name = "stt_desc")
	private String desc;
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "stt_type")
	private StatusType type;

	public Status() {
		// TODO Auto-generated constructor stub
	}

	public Status(String name, String desc, StatusType type) {
		this.name = name;
		this.desc = desc;
		this.type = type;
	}

	public int getSttId() {
		return sttId;
	}

	public void setSttId(int sttId) {
		this.sttId = sttId;
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

	public StatusType getType() {
		return type;
	}

	public void setType(StatusType type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Status [sttId=" + sttId + ", name=" + name + ", desc=" + desc
				+ ", type=" + type + "]";
	}

}

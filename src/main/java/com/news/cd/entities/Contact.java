package com.news.cd.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

@SuppressWarnings("serial")
@Entity
@Table(name = "contacts")
public class Contact implements Serializable, Comparable<Contact> {
	@Id
	@Column(name = "ct_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int contactId;
	@Column(name = "ct_submit_date")
	private Date submitDate;
	@Column(name = "ct_name")
	@NotEmpty(message = "Vui lòng nhập tên của bạn")
	private String name;
	@Column(name = "ct_email")
	@NotEmpty(message = "Vui lòng nhập email của bạn")
	@Email(message = "Định dạng email chưa đúng")
	private String email;
	@Column(name = "ct_content")
	@NotEmpty(message = "Vui lòng nhập nội dung")
	private String content;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ct_user")
	private User user;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ct_status")
	private Status status;

	public Contact() {
		// TODO Auto-generated constructor stub
	}

	public Contact(Date submitDate, String name, String email, String content,
			User user, Status status) {
		this.submitDate = submitDate;
		this.name = name;
		this.email = email;
		this.content = content;
		this.user = user;
		this.status = status;
	}

	public int getContactId() {
		return contactId;
	}

	public void setContactId(int contactId) {
		this.contactId = contactId;
	}

	public Date getSubmitDate() {
		return submitDate;
	}

	public void setSubmitDate(Date submitDate) {
		this.submitDate = submitDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Contact [contactId=" + contactId + ", submitDate=" + submitDate
				+ ", name=" + name + ", email=" + email + ", content="
				+ content + ", user=" + user + ", status=" + status + "]";
	}

	@Override
	public int compareTo(Contact other) {
		return this.submitDate.compareTo(other.submitDate);
	}

}

package com.news.cd.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@SuppressWarnings("serial")
@XmlRootElement(name = "item")
public class PostDTO implements Serializable {
	private String title;
	private String desc;
	private String publishDate;
	private String author;
	private String avatarUrl;
	private int views;
	private String link;

	public PostDTO() {
		// TODO Auto-generated constructor stub
	}

	public PostDTO(String title, String desc, String publishDate,
			String author, String avatarUrl, int views, String link) {
		super();
		this.title = title;
		this.desc = desc;
		this.publishDate = publishDate;
		this.author = author;
		this.avatarUrl = avatarUrl;
		this.views = views;
		this.link = link;
	}

	@XmlAttribute(name = "title")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@XmlElement(name = "description")
	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	@XmlElement(name = "publishedDate")
	public String getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(String publishDate) {
		this.publishDate = publishDate;
	}

	@XmlElement(name = "author")
	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	@XmlElement(name = "avatar")
	public String getAvatarUrl() {
		return avatarUrl;
	}

	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}

	@XmlElement(name = "views")
	public int getViews() {
		return views;
	}

	public void setViews(int views) {
		this.views = views;
	}

	@XmlElement(name = "link")
	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

}

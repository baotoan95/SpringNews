package com.news.cd.dto;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.news.cd.constants.ApplicationConstant;

@XmlRootElement(name = "latest")
public class LatestList {
	private String title = "Latest Post - SpringNews";
	private String desc = "SpringNews";
	private String generator = "SpringNews";
	private String link = ApplicationConstant.ROOT_PATH_DOMAIN + "/" ;
	private List<PostDTO> item = new ArrayList<PostDTO>();
	
	public LatestList() {
		// TODO Auto-generated constructor stub
	}

	public LatestList(String title, String desc, String generator, String link,
			List<PostDTO> item) {
		super();
		this.title = title;
		this.desc = desc;
		this.generator = generator;
		this.link = link;
		this.item = item;
	}

	@XmlElement(name = "title")
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

	@XmlElement(name = "generator")
	public String getGenerator() {
		return generator;
	}

	public void setGenerator(String generator) {
		this.generator = generator;
	}

	@XmlElement(name = "link")
	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public List<PostDTO> getitem() {
		return item;
	}

	public void setitem(List<PostDTO> item) {
		this.item = item;
	}
	
	public void addPost(PostDTO post) {
		this.item.add(post);
	}
}

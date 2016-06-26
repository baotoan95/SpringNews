package com.news.cd.dto;

import java.util.List;

import com.news.cd.entities.Post;

public class CategoryShowOn {
	private String name;
	private List<Post> listPost;

	public CategoryShowOn() {
		// TODO Auto-generated constructor stub
	}

	public CategoryShowOn(String name, List<Post> listPost) {
		this.name = name;
		this.listPost = listPost;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Post> getListPost() {
		return listPost;
	}

	public void setListPost(List<Post> listPost) {
		this.listPost = listPost;
	}

}

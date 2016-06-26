package com.news.cd.dto;

import java.util.List;

import com.news.cd.entities.Post;

public class IndexShowOn {
	private List<Post> listPopular;
	private List<Post> listLatest;
	private List<CategoryShowOn> listShow;

	public IndexShowOn() {
		// TODO Auto-generated constructor stub
	}

	public IndexShowOn(List<Post> listPopular, List<Post> listLatest, List<CategoryShowOn> listShow) {
		this.listPopular = listPopular;
		this.listLatest = listLatest;
		this.listShow = listShow;
	}

	public List<Post> getListPopular() {
		return listPopular;
	}

	public void setListPopular(List<Post> listPopular) {
		this.listPopular = listPopular;
	}

	public List<Post> getListLatest() {
		return listLatest;
	}

	public void setListLatest(List<Post> listLatest) {
		this.listLatest = listLatest;
	}

	public List<CategoryShowOn> getListShow() {
		return listShow;
	}

	public void setListShow(List<CategoryShowOn> listShow) {
		this.listShow = listShow;
	}

}

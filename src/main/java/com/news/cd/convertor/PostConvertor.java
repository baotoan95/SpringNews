package com.news.cd.convertor;

import com.news.cd.constants.ApplicationConstant;
import com.news.cd.dto.PostDTO;
import com.news.cd.entities.Post;

public class PostConvertor {
	public static PostDTO convert(Post post) {
		PostDTO postDTO = new PostDTO();
		postDTO.setTitle(post.getTitle());
		String author = post.getAuthor().getFirstName() + " " + post.getAuthor().getLastName();
		postDTO.setAuthor(author);
		postDTO.setDesc(post.getDesc());
		postDTO.setPublishDate(post.getLastUpdate().toString());
		postDTO.setViews(post.getViews());
		postDTO.setAvatarUrl(ApplicationConstant.ROOT_PATH_DOMAIN + post.getAvatarUrl());
		postDTO.setLink(ApplicationConstant.ROOT_PATH_DOMAIN + "/" + post.getlink());
		return postDTO;
	}
}

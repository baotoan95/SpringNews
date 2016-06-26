package com.news.cd.services;

import java.util.List;
import java.util.Map;

import com.news.cd.dto.IndexShowOn;
import com.news.cd.entities.Post;

public interface PostService {
	boolean addPost(Post post);
	boolean deletePost(int id);
	boolean updatePost(Post post);
	Post findPostById(int id);
	Post findPostByUrl(String url);
	List<Post> getPostsPopular(int size);
	List<Post> getPostsLatest(int size);
	List<Post> getPostsLatestOwn(int size);
	IndexShowOn getIndexShowOn();
	Map<String, Object> getPostsLimitResultByStatus(int currentPage, int numbOfRecordsPerPage, int numbOfPageShow, int statusId);
	Map<String, Object> getPostsLimitResultByCategory(int currentPage, int numbOfRecordsPerPage, int numbOfPageShow, int cateId);
	Map<String, Object> getPostsLimitResultByCategory(int currentPage, int numbOfRecordsPerPage, int numbOfPageShow, String cateLink);
	int countResultByCategory(int cateId);
	List<Post> getPostsByAuthor(String author);
}

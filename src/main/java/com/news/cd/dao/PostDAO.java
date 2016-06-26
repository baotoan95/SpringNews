package com.news.cd.dao;

import java.util.List;

import com.news.cd.entities.Post;
import com.news.cd.entities.RSSChannel;

public interface PostDAO {
	boolean addPost(Post post);
	boolean deletePost(int id);
	boolean deletePostsByCate(int cateId);
	boolean updatePost(Post post);
	Post findPostById(int id);
	Post findPostByUrl(String url);
	List<Post> getPostsPopular(int size);
	List<Post> getPostsLatest(int size);
	List<Post> getPostsLatestOwn(int size);
	List<Post> getPostsLimitResultByStatus(int indexMin, int numbOfResult, int statusId);
	List<Post> getPostsLimitResultByCategory(int indexMin, int numbOfResult, int cateId);
	List<Post> getPostsLimitResultByCategory(int indexMin, int numbOfResult, String cateLink);
	List<Post> getPostsByAuthor(String author);
	boolean isExist(String title);
	void deletePostByRSS(int rssId);
	void updateCateForPostByRSS(RSSChannel rssChannel);
	int countResultByStatus(int status);
	int countResultByCategory(int cateId);
	int countResultByCategory(String cateLink);
}

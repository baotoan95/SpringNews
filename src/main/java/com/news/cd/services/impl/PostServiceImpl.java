package com.news.cd.services.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.news.cd.dao.CommonDAO;
import com.news.cd.dao.PostDAO;
import com.news.cd.dto.IndexShowOn;
import com.news.cd.entities.Post;
import com.news.cd.helper.PaginationHelper;
import com.news.cd.services.CategoryService;
import com.news.cd.services.PostService;

@Service("postService")
@Transactional
public class PostServiceImpl implements PostService {
	@Autowired
	private PostDAO postDAO;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private CommonDAO commonDAO;

	@Override
	public boolean addPost(Post post) {
		return postDAO.addPost(post);
	}

	@Override
	public boolean deletePost(int id) {
		return postDAO.deletePost(id);
	}

	@Override
	public boolean updatePost(Post post) {
		return postDAO.updatePost(post);
	}

	@Override
	public Post findPostById(int id) {
		return postDAO.findPostById(id);
	}

	@Override
	public List<Post> getPostsPopular(int size) {
		return postDAO.getPostsPopular(size);
	}

	@Override
	public List<Post> getPostsLatest(int size) {
		return postDAO.getPostsLatest(size);
	}

	@Override
	public IndexShowOn getIndexShowOn() {
		IndexShowOn indexShowOn = new IndexShowOn();
		indexShowOn.setListPopular(postDAO.getPostsPopular(40));
		indexShowOn.setListLatest(postDAO.getPostsLatest(6));
		indexShowOn.setListShow(categoryService.getListCatetoryShowOn(10));
		return indexShowOn;
	}

	@Override
	public Map<String, Object> getPostsLimitResultByStatus(int currentPage,
			int numbOfRecordsPerPage, int numbOfPageShow, int statusId) {
		Map<String, Object> result = new HashMap<>();
		PaginationHelper paginationHelper = new PaginationHelper(
				postDAO.countResultByStatus(statusId), currentPage,
				numbOfRecordsPerPage, numbOfPageShow);
		result.put("data", postDAO.getPostsLimitResultByStatus(
						paginationHelper.getStartIndex(),
						paginationHelper.getMaxResult(), statusId));
		result.put("html", paginationHelper.getHTML());
		return result;
	}

	@Override
	public Map<String, Object> getPostsLimitResultByCategory(int currentPage,
			int numbOfRecordsPerPage, int numbOfPageShow, int cateId) {
		Map<String, Object> result = new HashMap<>();
		PaginationHelper paginationHelper = new PaginationHelper(
				postDAO.countResultByCategory(cateId), currentPage,
				numbOfRecordsPerPage, numbOfPageShow);
		result.put("data", postDAO.getPostsLimitResultByCategory(
						paginationHelper.getStartIndex(),
						paginationHelper.getMaxResult(), cateId));
		result.put("html", paginationHelper.getHTML());
		return result;
	}

    @Override
    public Map<String, Object> getPostsLimitResultByCategory(int currentPage,
            int numbOfRecordsPerPage, int numbOfPageShow, String cateLink) {
        Map<String, Object> result = new HashMap<>();
        PaginationHelper paginationHelper = new PaginationHelper(
                postDAO.countResultByCategory(cateLink), currentPage, numbOfRecordsPerPage, numbOfPageShow);
        result.put("data", postDAO.getPostsLimitResultByCategory(
                        paginationHelper.getStartIndex(),
                        paginationHelper.getMaxResult(), cateLink));
        result.put("html", paginationHelper.getHTML());
        return result;
    }

    @Override
    public int countResultByCategory(int cateId) {
        return postDAO.countResultByCategory(cateId);
    }

    @Override
    public Post findPostByUrl(String url) {
        return postDAO.findPostByUrl(url);
    }

	@Override
	public List<Post> getPostsLatestOwn(int size) {
		return postDAO.getPostsLatestOwn(size);
	}

	@Override
	public List<Post> getPostsByAuthor(String author) {
		return postDAO.getPostsByAuthor(author);
	}

}

package com.news.cd.services.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.news.cd.dao.CategoryDAO;
import com.news.cd.dao.PostDAO;
import com.news.cd.dao.RSSChannelDAO;
import com.news.cd.dto.CategoryShowOn;
import com.news.cd.entities.Category;
import com.news.cd.entities.Post;
import com.news.cd.entities.RSSChannel;
import com.news.cd.helper.PaginationHelper;
import com.news.cd.services.CategoryService;
import com.news.cd.utils.RSSReader;

@Service("categoryService")
@Transactional
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	private CategoryDAO categoryDAO;
	@Autowired
	private RSSChannelDAO rssChannelDAO;
	@Autowired
	private PostDAO postDAO;

	@Override
	public Category getCateById(int id) {
		return categoryDAO.getCateById(id);
	}
	
	@Override
    public Category getCateByLink(String link) {
        return categoryDAO.getCateByLink(link);
    }

	@Override
	public List<Category> getSubMenu(int parentId) {
		return categoryDAO.getSubMenu(parentId);
	}

	@Override
	public boolean addCate(Category cate) {
		return categoryDAO.addCate(cate);
	}

	@Override
	public boolean delCate(int id) {
		return categoryDAO.delCate(id);
	}

	@Override
	public boolean updateCate(Category cate) {
		return categoryDAO.updateCate(cate);
	}

	@Override
	public List<Category> getCatesShowIndex() {
		return categoryDAO.getCatesShowIndex();
	}

	@Override
	public List<CategoryShowOn> getListCatetoryShowOn(int numbOfRecordPerCate) {
		List<CategoryShowOn> cateShowOns = new ArrayList<CategoryShowOn>();
		// Lấy danh sách thể loại sẽ hiển thị trên index page (config trong csdl)
		List<Category> listCate = categoryDAO.getCatesShowIndex();
		
		for(Category cate : listCate) {
			// Get danh sách rssChannel đã đăng kí thuộc thể loại cate
			List<RSSChannel> rssChannels = rssChannelDAO.getRssChannelsByCateId(cate.getCateId());
			
			CategoryShowOn cateShowOn = new CategoryShowOn();
			cateShowOn.setName(cate.getName());
			List<Post> listPost = new ArrayList<>();
			int rssChannelSize = rssChannels.size();
			
			// Giới hạn số article lấy ra mỗi rsschannel dựa vào numbOfRecordPerCate
			try {
				int limit = numbOfRecordPerCate / rssChannelSize;
				for (int i = 0; i < rssChannelSize; i++) {
					RSSChannel rssChannel = rssChannels.get(i);
					// Đọc rssChannel để lấy danh sách article trong rss channel
					List<Post> posts = new RSSReader().setRssUrl(rssChannel.getLink()).read().getRssChannel().getItems();
					Iterator<Post> postsIter = posts.iterator();
					if(i == 0) { // Số dư sẽ được cộng lấy ra từ rssChannel đầu tiên
						limit += numbOfRecordPerCate % rssChannelSize;
					}
					// Thêm bài viết vào danh sách
					int count = 0;
					while(postsIter.hasNext() && (count++ < limit)) {
						listPost.add(postsIter.next());
					}
				}
				cateShowOn.setListPost(listPost);
				cateShowOns.add(cateShowOn);
			} catch (Exception e) {
				System.err.println("Thể loại không có rss");
			}
		}
		return cateShowOns;
	}

	@Override
	public Map<Integer, Category> getAll() {
		return categoryDAO.getAll();
	}

	@Override
	public Map<String, Object> getForPagination(int currentPage, int numbOfRecordsPerPage, int numbOfPageShow) {
		Map<String, Object> rs = new HashMap<>();
		PaginationHelper paginationHelper = new PaginationHelper(categoryDAO.countAll(), currentPage, numbOfRecordsPerPage, numbOfPageShow);
		rs.put("html", paginationHelper.getHTML().replaceAll("link",
				"categories.html?page=").replace("classContainer",
				"class='pagination pagination-sm no-margin pull-right'"));
		rs.put("data", categoryDAO.getCatesLimitResult(paginationHelper.getStartIndex(), paginationHelper.getMaxResult()));
		return rs;
	}

}

package com.news.cd.services.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.news.cd.constants.PostStatus;
import com.news.cd.constants.PostType;
import com.news.cd.dao.CategoryDAO;
import com.news.cd.dao.CommonDAO;
import com.news.cd.dao.PostDAO;
import com.news.cd.dao.RSSChannelDAO;
import com.news.cd.entities.Category;
import com.news.cd.entities.Post;
import com.news.cd.entities.RSSChannel;
import com.news.cd.services.RSSChannelService;

@Service("rssChannelService")
@Transactional
public class RSSChannelServiceImpl implements RSSChannelService {
	@Autowired
	private RSSChannelDAO rssChannelDAO;
	@Autowired
	private CategoryDAO categoryDAO;
	@Autowired
	private PostDAO postDAO;
	@Autowired
	private CommonDAO commonDAO;

	@Override
	public RSSChannel findRSSChannelById(int rssChannelId) {
		return rssChannelDAO.findRSSChannelById(rssChannelId);
	}

	@Override
	public boolean addRSSChannel(RSSChannel rssChannel) {
		return rssChannelDAO.addRSSChannel(rssChannel);
	}

	@Override
	public boolean deleteRSSChannel(int rssId) {
		return rssChannelDAO.deleteRSSChannel(findRSSChannelById(rssId));
	}

	@Override
	public List<RSSChannel> getRssChannelsByCateId(int cateId) {
		return rssChannelDAO.getRssChannelsByCateId(cateId);
	}
	
	private RSSChannel processListPostForRSSChannel(RSSChannel rssChannel, int cateId, List<Integer> listItemSelected) {
		Category category = categoryDAO.getCateById(cateId);
		rssChannel.setCategory(category);
		List<Post> listItemsSelected = new ArrayList<>();
		for (Integer index : listItemSelected) {
			Post post = rssChannel.getItems().get(index);
			if(!postDAO.isExist(post.getlink()) && post.getTitle().length() > 0) {
				post.setCategory(category);
				post.setContent("N/a");
				post.setLastUpdate(new Date());
				post.setRssChannel(rssChannel);
				post.setStatus(commonDAO.getStatusById(PostStatus.APPROVED));
				post.setType(commonDAO.getPostTypeById(PostType.RSS));
				listItemsSelected.add(post);
			}
		}
		rssChannel.setItems(listItemsSelected);
		return rssChannel;
	}
	
	@Override
    public boolean updateRSSChannel(RSSChannel rssChannel, int cateId, List<Integer> listItemSelected) {
        rssChannel = processListPostForRSSChannel(rssChannel, cateId, listItemSelected);
        RSSChannel oldRSSChannel = rssChannelDAO.findRSSChannelById(rssChannel.getRssChannelId());
        // Nếu thể loại của rssChannel cũ khác rssChannel mới sửa thì cập nhật thể loại
        if(oldRSSChannel.getCategory().getCateId() != rssChannel.getCategory().getCateId()) {
            postDAO.updateCateForPostByRSS(rssChannel);
        }
        // Ngược lại thì chỉ cập nhật thông tin bình thường
        return rssChannelDAO.updateRSSChannel(rssChannel);
    }

	@Override
	public String subscribe(RSSChannel rssChannel, int cateId, List<Integer> listItemSelected) {
		rssChannel = processListPostForRSSChannel(rssChannel, cateId, listItemSelected);
		rssChannel.setLastBuildDate(new Date());
		if (rssChannelDAO.addRSSChannel(rssChannel)) {
			return "Đã đăng ký kênh " + rssChannel.getTitle();
		} else {
			return "Lỗi hệ thống...";
		}
	}

	@Override
	public boolean isSubscribed(String link) {
		return rssChannelDAO.findRSSChannelByRSSURL(link) != null ? true : false;
	}

	@Override
	public RSSChannel findRSSChannelByRSSURL(String url) {
		RSSChannel rssChannel = rssChannelDAO.findRSSChannelByRSSURL(url);
		Hibernate.initialize(rssChannel.getItems());
		return rssChannel;
	}

	@Override
	public List<RSSChannel> getAll() {
		return rssChannelDAO.getAll();
	}

    @Override
    public List<Post> getListPostForRSSChannel(RSSChannel rssChannel) {
        List<Post> listPost = rssChannel.getItems();
        List<Post> listItems = new ArrayList<>();
        for (Post post : listPost) {
            if(!postDAO.isExist(post.getlink())) {
                listItems.add(post);
            }
        }
        return listItems;
    }

}

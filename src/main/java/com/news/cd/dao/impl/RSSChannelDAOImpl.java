package com.news.cd.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.news.cd.dao.AbstractDAO;
import com.news.cd.dao.PostDAO;
import com.news.cd.dao.RSSChannelDAO;
import com.news.cd.entities.RSSChannel;

@Repository("rssChannelDAO")
public class RSSChannelDAOImpl extends AbstractDAO<Integer, RSSChannel> implements RSSChannelDAO {
	@Autowired
	private PostDAO postDAO;

	@Override
	public RSSChannel findRSSChannelById(int rssChannelId) {
		return getByKey(rssChannelId);
	}

	@Override
	public boolean addRSSChannel(RSSChannel rssChannel) {
		return persist(rssChannel);
	}

	@Override
	public boolean deleteRSSChannel(RSSChannel rssChannel) {
		return delete(rssChannel);
	}

	@Override
	public boolean updateRSSChannel(RSSChannel rssChannel) {
		try {
			RSSChannel oldRSSChannel = findRSSChannelById(rssChannel.getRssChannelId());
			oldRSSChannel.setCategory(rssChannel.getCategory());
			oldRSSChannel.setDesc(rssChannel.getDesc());
			oldRSSChannel.setDocs(rssChannel.getDocs());
			oldRSSChannel.setGenerator(rssChannel.getGenerator());
			oldRSSChannel.setLang(rssChannel.getLang());
			oldRSSChannel.setLastBuildDate(new Date());
			oldRSSChannel.setManagingEditor(rssChannel.getManagingEditor());
			oldRSSChannel.setPubDate(rssChannel.getPubDate());
			oldRSSChannel.setTitle(rssChannel.getTitle());
			oldRSSChannel.setWebMaster(rssChannel.getWebMaster());
			oldRSSChannel.setItems(rssChannel.getItems());
			getSession().update(oldRSSChannel);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RSSChannel> getRssChannelsByCateId(int id) {
		Query query = getSession().createQuery("from RSSChannel where category.cateId = :cateId");
		query.setParameter("cateId", id);
		return query.list();
	}

	@Override
	public RSSChannel findRSSChannelByRSSURL(String url) {
		try {
			Query query = getSession().createQuery("from RSSChannel where link = :url");
			query.setParameter("url", url);
			return (RSSChannel) query.uniqueResult();
		} catch (Exception e) { // If not found any result
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RSSChannel> getAll() {
		Criteria criteria = createEntityCriteria();
		return criteria.list();
	}

	@Override
	public boolean deleteRSSChannelByCateId(int cateId) {
		Query query = getSession().createQuery("delete RSSChannel rss where rss.category.cateId = :cateId");
		query.setParameter("cateId", cateId);
		return query.executeUpdate() > 0;
	}

}

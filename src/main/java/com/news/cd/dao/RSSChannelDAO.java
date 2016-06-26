package com.news.cd.dao;

import java.util.List;

import com.news.cd.entities.RSSChannel;

public interface RSSChannelDAO {
	RSSChannel findRSSChannelById(int rssChannelId);
	RSSChannel findRSSChannelByRSSURL(String url);
	boolean addRSSChannel(RSSChannel rssChannel);
	boolean deleteRSSChannel(RSSChannel rssChannel);
	boolean deleteRSSChannelByCateId(int cateId);
	boolean updateRSSChannel(RSSChannel rssChannel);
	List<RSSChannel> getRssChannelsByCateId(int cateId);
	List<RSSChannel> getAll();
}

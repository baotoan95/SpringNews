package com.news.cd.services;

import java.util.List;

import com.news.cd.entities.Post;
import com.news.cd.entities.RSSChannel;

public interface RSSChannelService {
	RSSChannel findRSSChannelById(int rssChannelId);
	RSSChannel findRSSChannelByRSSURL(String url);
	boolean addRSSChannel(RSSChannel rssChannel);
	boolean deleteRSSChannel(int rssId);
	List<RSSChannel> getRssChannelsByCateId(int cateId);
	boolean updateRSSChannel(RSSChannel rssChannel, int cateId, List<Integer> listItemSelected);
	String subscribe(RSSChannel rssChannel, int cateId, List<Integer> listItemSelected);
	boolean isSubscribed(String link);
	List<RSSChannel> getAll();
	List<Post> getListPostForRSSChannel(RSSChannel rssChannel);
}

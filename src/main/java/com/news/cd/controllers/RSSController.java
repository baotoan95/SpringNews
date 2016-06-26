package com.news.cd.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.news.cd.entities.Category;
import com.news.cd.entities.RSSChannel;
import com.news.cd.services.CategoryService;
import com.news.cd.services.RSSChannelService;
import com.news.cd.utils.RSSReader;

@Controller
@RequestMapping("/")
public class RSSController {
	@Autowired
	private RSSReader rssReader;
	@Autowired
	private RSSChannelService rssChannelService;
	@Autowired
	private CategoryService categoryService;
	
	@RequestMapping(value = "/admin/rss.html", method = RequestMethod.GET)
	public String adminListRSS(ModelMap model) {
		List<RSSChannel> listRSS = rssChannelService.getAll();
		model.addAttribute("listRSS", listRSS);
		return "allRSS";
	}

	@RequestMapping(value = "/admin/addRss.html", method = RequestMethod.GET)
	public String adminAddRSSChannel(ModelMap model, HttpSession session) {
		if (session.getAttribute("rssChannel") != null) {
			session.removeAttribute("rssChannel");
		}
		model.addAttribute("action", "getrss");
		return "rsschannel";
	}

	@RequestMapping(value = "/admin/getrss", method = RequestMethod.POST)
	public String adminGetRSSChannelByURL(@RequestParam("link") String link, ModelMap model, HttpSession session) {
		// Connect to url
		RSSReader rssAvaliable = rssReader.setRssUrl(link.trim()).read();
		if (null != rssAvaliable) {
	        if(!rssChannelService.isSubscribed(link)) {
	            // check if url was not Subcribed => redirect to subscribe page
	            session.setAttribute("rssChannel", rssAvaliable.getRssChannel());
	            model.addAttribute("action", "subscribe");
	        } else {
	            // If subscribed then update
	            RSSChannel rssChannel = rssChannelService.findRSSChannelByRSSURL(link);
	            // Cập nhật post mới
	            rssChannel.setItems(rssChannelService.getListPostForRSSChannel(rssReader.setRssUrl(rssChannel.getLink()).read().getRssChannel())); 
	            session.setAttribute("rssChannel", rssChannel);
	            model.addAttribute("message", "Địa chỉ RSS đã được đăng ký");
	            model.addAttribute("action", "updateRSS");
	        }
        } else {
            model.addAttribute("message", "Địa chỉ RSS không tồn tại hoặc đã bị đổi");
            model.addAttribute("action", "getrss");
        }
		model.addAttribute("categories", new ArrayList<Category>(categoryService.getAll().values()));
		return "rsschannel";
	}
	
	@RequestMapping(value = "/admin/updateRSS", method = RequestMethod.POST)
	public String updateRSSChannel(@RequestParam("cate") int cate, @RequestParam("generator") String generator,
			@RequestParam(value = "listItems", required = false) List<Integer> listItems, ModelMap model,
			HttpSession session) {
		if(listItems == null) {
			listItems = new ArrayList<>();
		}
		RSSChannel rssChannel = (RSSChannel) session.getAttribute("rssChannel");
		rssChannel.setGenerator(generator);
		if(rssChannelService.updateRSSChannel(rssChannel, cate, listItems)) {
			model.addAttribute("message", "Cập nhật thành công");
			model.addAttribute("rssChannel", rssChannel);
			model.addAttribute("action", "getrss");
		} else {
		    model.addAttribute("action", "updateRSS");
			model.addAttribute("message", "Lỗi hệ thống...");
		}
		session.removeAttribute("rssChannel");
		model.addAttribute("categories", new ArrayList<Category>(categoryService.getAll().values()));
		return "rsschannel";
	}

	@RequestMapping(value = "/admin/subscribe", method = RequestMethod.POST)
	public String rssSubscribe(@RequestParam("cate") int cate, @RequestParam("generator") String generator,
			@RequestParam(value = "listItems", required = false) List<Integer> listItems, ModelMap model,
			HttpSession session) {
		if(listItems == null) {
			listItems = new ArrayList<>();
		}
		RSSChannel rssChannel = (RSSChannel) session.getAttribute("rssChannel");
		rssChannel.setGenerator(generator);
		model.addAttribute("message", rssChannelService.subscribe(rssChannel, cate, listItems));
		session.removeAttribute("rssChannel");
		model.addAttribute("categories", new ArrayList<Category>(categoryService.getAll().values()));
		model.addAttribute("action", "getrss");
		return "rsschannel";
	}
	
	@RequestMapping(value = "/admin/deleteRSS", method = RequestMethod.GET)
	@ResponseBody
	public boolean deleteRSSChannel(@RequestParam("rssId") int rssId) {
		return rssChannelService.deleteRSSChannel(rssId);
	}

}

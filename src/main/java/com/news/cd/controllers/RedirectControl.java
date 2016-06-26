package com.news.cd.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.news.cd.dao.UserDAO;
import com.news.cd.dto.IndexShowOn;
import com.news.cd.dto.StatisticDTO;
import com.news.cd.services.ContactService;
import com.news.cd.services.PostService;
import com.news.cd.services.StatisticService;
import com.news.cd.utils.DateUtils;

@Controller
public class RedirectControl {
	@Autowired
	private PostService postService;
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private StatisticService statisticService;
	@Autowired
	private ContactService contactService;
	
	@RequestMapping(value = {"/index.html", "/home.html", "/"}, method = RequestMethod.GET)
	public String indexPage(ModelMap model, HttpServletRequest request) {
	    IndexShowOn indexShowOn = postService.getIndexShowOn();
		model.addAttribute("indexShowOn", indexShowOn);
		model.addAttribute("listLatest", postService.getPostsLatest(40));
		model.addAttribute("listPopular", indexShowOn.getListPopular());
		return "index";
	}
	
	@RequestMapping(value = {"/admin/index.html","/admin"}, method = RequestMethod.GET)
	public String adminPage(ModelMap model, HttpSession session) {
		session.setAttribute("totalInbox", contactService.countAll("wait_to_read"));
		model.addAttribute("maxValue", statisticService.getMaxValue());
		model.addAttribute("data", statisticService.getDataByMonth(DateUtils.currentMonth(), DateUtils.currentYear()));
		model.addAttribute("totalVisits", statisticService.getTotalStatistic());
		return "admin";
	}
	
	@RequestMapping(value = {"/admin/statistic"}, method = RequestMethod.GET)
	@ResponseBody
	public StatisticDTO adminPage(@RequestParam("month") int month, @RequestParam("year") int year) {
		return new StatisticDTO(statisticService.getMaxValue(), statisticService.getDataByMonth(month, year), statisticService.getTotalStatistic());
	}
	
	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public String accessDenied() {
		return "403";
	}
}

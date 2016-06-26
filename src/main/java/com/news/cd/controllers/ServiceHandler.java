package com.news.cd.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.news.cd.convertor.PostConvertor;
import com.news.cd.dto.LatestList;
import com.news.cd.entities.Post;
import com.news.cd.services.PostService;

@RestController("/")
public class ServiceHandler {
	@Autowired
	private PostService postService;
	
	@RequestMapping(value = "latest/json/", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
	public @ResponseBody LatestList getPostsLatestJson() {
		return createLatestList();
	}
	
	@RequestMapping(value = "latest/xml/", method = RequestMethod.GET, produces = {MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<LatestList> getPostsLatestXML() {
		return new ResponseEntity<LatestList>(createLatestList(), HttpStatus.OK);
	}
	
	private LatestList createLatestList() {
		LatestList latestList = new LatestList();
		for(Post post : postService.getPostsLatestOwn(20)) {
			latestList.addPost(PostConvertor.convert(post));
		}
		return latestList;
	}
}

package com.news.cd.constants;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class ApplicationConstant {
	private static final String DOMAIN = "http://localhost:8080";
	
	
	/*
	 * Contain util avaliables and methods use in application
	 * */
	
	public static HttpServletRequest getCurrentRequest() {
		HttpServletRequest currentRequest = ((ServletRequestAttributes) 
				RequestContextHolder.currentRequestAttributes()).getRequest();
		return currentRequest;
	}
	
	public static String ROOT_PATH_DOMAIN = DOMAIN + getCurrentRequest().getContextPath();
	public static String ROOT_PATH = getCurrentRequest().getContextPath();
	public static String REAL_PATH = getCurrentRequest().getServletContext().getRealPath("");
}

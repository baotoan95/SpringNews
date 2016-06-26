package com.news.cd.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.news.cd.services.StatisticService;

/*
 * @author BaoToan
 * @version 1.0
 * @date: May 26, 2016
 */

public class CountFilter implements HandlerInterceptor {
    @Autowired
    private StatisticService statisticService;

    @Override
    public void afterCompletion(HttpServletRequest request,
            HttpServletResponse response, Object obj, Exception e)
            throws Exception {
        
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object obj, ModelAndView mav) throws Exception {
        
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
            Object obj) throws Exception {
        HttpSession session = request.getSession();
        if(null == session.getAttribute("STATISTIC_YESTERDAY") || null == session.getAttribute("STATISTIC_TOTAL")) {
            session.setAttribute("STATISTIC_YESTERDAY", statisticService.getTotalStatisticByYesterday());
            session.setAttribute("STATISTIC_TOTAL", statisticService.getTotalStatistic());
        }
        return true;
    }

}

package com.news.cd.listeners;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.news.cd.services.StatisticService;

/*
 * @author BaoToan
 * @version 1.0
 * @date: May 25, 2016
 */

public class SessionCounter implements HttpSessionListener {
    private Set<String> sessions = new HashSet<String>();
    private final String COUNTER = "USER_ACTIVE";
    private StatisticService statisticService;

    public int getActiveSessionNumber() {
        return sessions.size();
    }

    @Override
    public void sessionCreated(HttpSessionEvent arg0) {
        HttpSession session = arg0.getSession();
        sessions.add(session.getId());
        ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(session.getServletContext());
        statisticService = (StatisticService) ctx.getBean("statisticService");
        statisticService.updateStatistic();
        session.setAttribute(COUNTER, getActiveSessionNumber());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent arg0) {
        HttpSession session = arg0.getSession();
        sessions.remove(session.getId());
        session.setAttribute(COUNTER, getActiveSessionNumber());
    }

}

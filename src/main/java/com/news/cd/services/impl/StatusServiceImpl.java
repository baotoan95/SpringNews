package com.news.cd.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.news.cd.dao.StatusDAO;
import com.news.cd.entities.Status;
import com.news.cd.services.StatusService;

/*
 *@Author BaoToan
 *@Version 1.0 May 9, 2016
 */

@Service("statusService")
public class StatusServiceImpl implements StatusService {
    @Autowired
    private StatusDAO statusDAO;

    @Override
    public Status getStatus(int id) {
        return statusDAO.getStatus(id);
    }
    
}

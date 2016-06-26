package com.news.cd.dao.impl;

import org.springframework.stereotype.Repository;

import com.news.cd.dao.AbstractDAO;
import com.news.cd.dao.StatusDAO;
import com.news.cd.entities.Status;

/*
 *@Author BaoToan
 *@Version 1.0 May 9, 2016
 */

@Repository("statusDAO")
public class StatusDAOImpl extends AbstractDAO<Integer, Status> implements StatusDAO {

    @Override
    public Status getStatus(int id) {
        return getByKey(id);
    }

}

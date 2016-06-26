package com.news.cd.services.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.news.cd.dao.CommonDAO;
import com.news.cd.entities.PostType;
import com.news.cd.entities.Status;
import com.news.cd.services.CommonService;

/*
 * @author BaoToan
 * @version 1.0
 * @date: May 18, 2016
 */

@Service("commonService")
@Transactional
public class CommonServiceImpl implements CommonService {
    @Autowired
    private CommonDAO commonDAO;

    @Override
    public List<PostType> getListPostType() {
        return commonDAO.getListPostType();
    }

    @Override
    public PostType getPostTypeById(Integer id) {
        return commonDAO.getPostTypeById(id);
    }

    @Override
    public List<Status> getStatusByStatusType(Integer id) {
        return commonDAO.getStatusByStatusType(id);
    }

    @Override
    public Status getStatusById(Integer id) {
        return commonDAO.getStatusById(id);
    }

}

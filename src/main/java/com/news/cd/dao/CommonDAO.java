package com.news.cd.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.news.cd.entities.PostType;
import com.news.cd.entities.Status;

/*
 * @author BaoToan
 * @version 1.0
 * @date: May 18, 2016
 */

public abstract class CommonDAO {
    @Autowired
    private SessionFactory sessionFactory;
    
    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }
    
    @SuppressWarnings("rawtypes")
    public Criteria createCriteria(Class clazz) {
        return getSession().createCriteria(clazz);
    }
    
    @SuppressWarnings("rawtypes")
    public Object getByKey(Class clazz, Serializable key) {
        try {
            return getSession().get(clazz, key);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    // Post type
    public abstract List<PostType> getListPostType();
    public abstract PostType getPostTypeById(Integer id);
    // Status
    public abstract List<Status> getStatusByStatusType(Integer id);
    public abstract Status getStatusById(Integer id);
    
}

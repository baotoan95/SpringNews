package com.news.cd.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.news.cd.dao.CommonDAO;
import com.news.cd.entities.PostType;
import com.news.cd.entities.Status;

/*
 * @author BaoToan
 * @version 1.0
 * @date: May 18, 2016
 */

@Repository("commonDAO")
public class CommonDAOImpl extends CommonDAO {
    @Override
    public PostType getPostTypeById(Integer id) {
        return (PostType) getByKey(PostType.class, id);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<PostType> getListPostType() {
        Criteria criteria = createCriteria(PostType.class);
        return criteria.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Status> getStatusByStatusType(Integer id) {
        Criteria criteria = createCriteria(Status.class);
        criteria.add(Restrictions.eq("type", id));
        return criteria.list();
    }

    @Override
    public Status getStatusById(Integer id) {
        return (Status) getByKey(Status.class, id);
    }

}

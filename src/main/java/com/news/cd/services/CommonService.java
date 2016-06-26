package com.news.cd.services;

import java.util.List;

import com.news.cd.entities.PostType;
import com.news.cd.entities.Status;

/*
 * @author BaoToan
 * @version 1.0
 * @date: May 18, 2016
 */

public interface CommonService {
    List<PostType> getListPostType();
    PostType getPostTypeById(Integer id);
    List<Status> getStatusByStatusType(Integer id);
    Status getStatusById(Integer id);
}

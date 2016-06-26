package com.news.cd.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.news.cd.dao.AbstractDAO;
import com.news.cd.dao.CategoryDAO;
import com.news.cd.dao.PostDAO;
import com.news.cd.dao.RSSChannelDAO;
import com.news.cd.entities.Category;

@Repository("categoryDAO")
public class CategoryDAOImpl extends AbstractDAO<Integer, Category> implements CategoryDAO {
	@Autowired
	private PostDAO postDAO;
	@Autowired
	private RSSChannelDAO rssChannelDAO;
	
	@Override
	public Category getCateById(int id) {
		return getByKey(id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Category> getSubMenu(int parentId) {
		Criteria criteria = createEntityCriteria();
		return criteria.add(Restrictions.eq("parent", parentId)).list();
	}

	@Override
	public boolean addCate(Category cate) {
		return persist(cate);
	}

	@Override
	public boolean delCate(int id) {
		rssChannelDAO.deleteRSSChannelByCateId(id);
		postDAO.deletePostsByCate(id);
		return delete(getByKey(id));
	}

	@Override
	public boolean updateCate(Category cate) {
		try {
			System.out.println(cate.getCateId());
			Category oldCate = getByKey(cate.getCateId());
			// Neu khong xac dinh duoc url toi image thi khong cap nhat
			if(cate.getAvatarUrl().length() > 1) {
				oldCate.setAvatarUrl(cate.getAvatarUrl());
			}
			System.out.println(oldCate);
			oldCate.setDesc(cate.getDesc());
			oldCate.setMenu(cate.isMenu());
			oldCate.setName(cate.getName());
			oldCate.setParent(cate.getParent());
			oldCate.setShow(cate.isShow());
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Category> getCatesShowIndex() {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("show", true));
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<Integer, Category> getAll() {
		Map<Integer, Category> rs = new HashMap<>();
		List<Category> cates = createEntityCriteria().list();
		for (Category cate : cates) {
			rs.put(cate.getCateId(), cate);
		}
		return rs;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Category> getCatesLimitResult(int indexMin, int numbOfResult) {
		Criteria creteria = createEntityCriteria();
		creteria.setFirstResult(indexMin);
		creteria.setMaxResults(numbOfResult);
		return creteria.list();
	}

	@Override
	public int countAll() {
		return ((Number) createEntityCriteria().setProjection(
				Projections.rowCount()).uniqueResult()).intValue();
	}

    @Override
    public Category getCateByLink(String link) {
        try {
            Query query = getSession().createQuery("from Category c where c.url = :link");
            query.setParameter("link", link);
            return (Category)query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}

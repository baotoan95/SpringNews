package com.news.cd.dao;

import java.util.List;
import java.util.Map;

import com.news.cd.entities.Category;

public interface CategoryDAO {
	Category getCateById(int id);
	Category getCateByLink(String link);
	List<Category> getSubMenu(int parentId);
	boolean addCate(Category cate);
	boolean delCate(int id);
	boolean updateCate(Category cate);
	List<Category> getCatesShowIndex();
	Map<Integer, Category> getAll();
	List<Category> getCatesLimitResult(int indexMin, int numbOfResult);
	int countAll();
}

package com.news.cd.services;

import java.util.List;
import java.util.Map;

import com.news.cd.dto.CategoryShowOn;
import com.news.cd.entities.Category;

public interface CategoryService {
	Category getCateById(int id);
	Category getCateByLink(String link);
	List<Category> getSubMenu(int parentId);
	boolean addCate(Category cate);
	boolean delCate(int id);
	boolean updateCate(Category cate);
	List<Category> getCatesShowIndex();
	List<CategoryShowOn> getListCatetoryShowOn(int numbOfRecordPerCate);
	Map<Integer, Category> getAll();
	Map<String, Object> getForPagination(int currentPage, int numbOfRecordsPerPage, int numbOfPageShow);
}

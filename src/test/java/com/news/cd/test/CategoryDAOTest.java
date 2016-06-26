package com.news.cd.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.news.cd.dao.CategoryDAO;
import com.news.cd.entities.Category;

/*
 *@Author BaoToan
 *@Version 1.0 May 11, 2016
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:dispatcher-servlet.xml"})
public class CategoryDAOTest {
    @Autowired
    private CategoryDAO categoryDAO;
    
    @Test
    public void testGetCategory() {
        Category category = categoryDAO.getCateById(2);
        Category cateExpect = new Category();
        cateExpect.setCateId(2);
        Assert.assertEquals(cateExpect, category);
    }
}

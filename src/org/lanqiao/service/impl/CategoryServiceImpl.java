package org.lanqiao.service.impl;

import java.util.List;

import org.lanqiao.dao.CategoryDao;
import org.lanqiao.dao.impl.CategoryDaoImpl;
import org.lanqiao.entity.Category;
import org.lanqiao.service.CategoryService;

public class CategoryServiceImpl implements CategoryService{
	CategoryDao categoryDao =null;
	public CategoryServiceImpl() {
		categoryDao = new CategoryDaoImpl();
	}
	@Override
	public List<Category> categoryList() {
		// TODO Auto-generated method stub
		
		return categoryDao.list();
	}

}

package org.lanqiao.service.impl;

import java.util.List;

import org.lanqiao.dao.NewDao;
import org.lanqiao.dao.impl.NewsDaoImpl;
import org.lanqiao.entity.News;
import org.lanqiao.service.NewsService;

public class NewsServiceImpl implements NewsService{
	 NewDao dao = null;
	 public NewsServiceImpl() {
		dao = new NewsDaoImpl();
	}
	@Override
	public List<News> newsList() {
		// TODO Auto-generated method stub
		
		return dao.list();
	}
	@Override
	public News getNewsById(String id) {
		// TODO Auto-generated method stub
		return dao.get(id);
	}

}

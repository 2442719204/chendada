package org.lanqiao.dao;

import java.util.List;

import org.lanqiao.entity.News;

public interface NewDao {
	public List<News> list();
	public News get(String id);
}

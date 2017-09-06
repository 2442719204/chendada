package org.lanqiao.dao;

import java.util.List;

import org.lanqiao.entity.Category;
import org.lanqiao.entity.Goods;
import org.lanqiao.entity.PageInfo;
import org.lanqiao.entity.Publisher;

public interface GoodsDao {
	public PageInfo<Goods> list(String cid,int pagesize,int pageindex);
	public int getTotalNumber(String cid);
	public Goods get(String gid);
	public void insertGoods(Goods goods);
	public Category getCategory(String cid);
	public Publisher getPublisher(String pid);
	public Category getCategoryBycname(String cname);
	public Publisher getPublisherByPname(String pname);
	public void updateGoods(Goods goods);
	public void removeGoods(String gid);

}

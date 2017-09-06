package org.lanqiao.service;

import org.lanqiao.entity.Goods;
import org.lanqiao.entity.PageInfo;

public interface GoodsService {
	public PageInfo<Goods> goodsList(String cid,int pageSize,int pageIndex); 
	public Goods getGoodsBygid(String gid);
	public void insertGoods(Goods goods);
	public void updateGoods(Goods goods) ;
	public void removeGoods(String id);
}

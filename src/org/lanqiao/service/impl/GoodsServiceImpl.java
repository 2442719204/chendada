package org.lanqiao.service.impl;

import org.lanqiao.dao.GoodsDao;
import org.lanqiao.dao.impl.GoodsDaoImpl;
import org.lanqiao.entity.Goods;
import org.lanqiao.entity.PageInfo;
import org.lanqiao.service.GoodsService;

public class GoodsServiceImpl implements GoodsService{
	GoodsDao goodsDao = null;
	public GoodsServiceImpl() {
		goodsDao = new GoodsDaoImpl();
	}
	@Override
	public PageInfo<Goods> goodsList(String cid, int pageSize, int pageIndex) {
		// TODO Auto-generated method stub
		return goodsDao.list(cid, pageSize, pageIndex);
	}
	@Override
	public Goods getGoodsBygid(String gid) {
		
		return goodsDao.get(gid);
	}
	@Override
	public void insertGoods(Goods goods) {
		goodsDao.insertGoods(goods);
		
	}
	@Override
	public void updateGoods(Goods goods) {
		goodsDao.updateGoods(goods);
	}
	@Override
	public void removeGoods(String id) {
		goodsDao.removeGoods(id);
	}

}

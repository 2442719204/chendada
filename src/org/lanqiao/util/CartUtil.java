package org.lanqiao.util;

import java.util.ArrayList;
import java.util.List;

import org.lanqiao.entity.Cart;
import org.lanqiao.entity.CookieItem;
import org.lanqiao.entity.Goods;
import org.lanqiao.service.GoodsService;
import org.lanqiao.service.impl.GoodsServiceImpl;

public class CartUtil {
	public static List<Cart> convertCookieItemListToCartList(List<CookieItem> list){
		if(list==null) return null;
		List<Cart> cart = new ArrayList<Cart>();
		Cart c = null;
		GoodsService gs = new GoodsServiceImpl();
		for(int i=0;i<list.size();i++){
			CookieItem item = list.get(i);
			Goods goods = gs.getGoodsBygid(item.getGid());
			c = new Cart(item.getGid(),goods.getGtitle(),goods.getGsaleprice(),goods.getGinprice(),item.getAmount());
			cart.add(c);
		}
	
		return cart;
		
	}
}

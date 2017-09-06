package org.lanqiao.test;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.junit.Test;
import org.lanqiao.dao.GoodsDao;
import org.lanqiao.dao.OrderDao;
import org.lanqiao.dao.UserDao;
import org.lanqiao.dao.impl.GoodsDaoImpl;
import org.lanqiao.dao.impl.OrderDaoImpl;
import org.lanqiao.dao.impl.UserDaoImpl;
import org.lanqiao.entity.Category;
import org.lanqiao.entity.Goods;
import org.lanqiao.entity.Order;
import org.lanqiao.entity.Publisher;
import org.lanqiao.entity.User;
import org.lanqiao.service.UserService;
import org.lanqiao.service.impl.UserserviceImpl;
import org.lanqiao.util.DBUtil;

public class UserserviceImplTest {

	@Test
	public void testGetUserList() {
		GoodsDao gd = new GoodsDaoImpl();
		Category cate = new Category("1","et");
		Publisher pub = new Publisher("1", "gr");
		String name  = UUID.randomUUID().toString();
		Goods goods = new Goods(name, "ww", "ee", 34, 52, "grhth", "grhth", 5, cate, pub);
		gd.insertGoods(goods);
	}
	@Test
	public void testGetUserList2() {
		OrderDao od = new OrderDaoImpl();
		 List<Order> list = od.getAllOrder();
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
		
	}
	@Test
	public void testGetUserList3() {
		OrderDao od = new OrderDaoImpl();
		List<Order> list = od.getAllOrder();
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).toString());
		}
	}
	@Test
	public void testUpdate(){
		Connection conn = null;
		//，所有sql语句运行前，必然有个解析过程，
		PreparedStatement ps = null;
		//获取链接
		String pid = null;
		Publisher publisher = null;
		conn = DBUtil.getConnection();
		String sql = "update t_goods set gtitle=?,gauthor=?,gimg=?,cid=? where gid =?";
		try {
			ps = conn.prepareStatement(sql);
			for(int i=600;i<700;i++){
				ps.setString(1, "大勇和小花的欧洲日记 - 经管- 40");
				ps.setString(2, "大勇");
				ps.setString(3, "105");
				ps.setString(4, "5");
				ps.setString(5, String.valueOf(i));
				ps.executeUpdate();
			}
					
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			
			try {
				ps.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
}

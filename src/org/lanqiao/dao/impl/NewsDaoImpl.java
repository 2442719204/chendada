package org.lanqiao.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.lanqiao.dao.NewDao;
import org.lanqiao.entity.News;
import org.lanqiao.util.DBUtil;

public class NewsDaoImpl implements NewDao{

	@Override
	public List<News> list() {
		// TODO Auto-generated method stub
		List<News> list = new ArrayList<News>();
		Connection conn = null;
		//，所有sql语句运行前，必然有个解析过程，
		PreparedStatement ps = null;
		ResultSet rs = null;
		//获取链接
		conn = DBUtil.getConnection();
		String sql = "select * from t_news";
		try {
			ps = conn.prepareStatement(sql);
			rs= ps.executeQuery();
			News news = null;
			while(rs.next()){
				
				news = new News(rs.getString("tid"),rs.getString("title"),rs.getString("tcontent"),rs.getDate("tpubdate"));
				list.add(news);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			
			try {
				rs.close();
				ps.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
		return list;
	}

	@Override
	public News get(String id) {
		News news = null;
		Connection conn = null;
		//，所有sql语句运行前，必然有个解析过程，
		PreparedStatement ps = null;
		ResultSet rs = null;
		//获取链接
		conn = DBUtil.getConnection();
		String sql = "select * from t_news where tid=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			rs= ps.executeQuery();
			while(rs.next()){
				
				news = new News(rs.getString("tid"),rs.getString("title"),rs.getString("tcontent"),rs.getDate("tpubdate"));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			
			try {
				rs.close();
				ps.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		return news;
	}
	
}

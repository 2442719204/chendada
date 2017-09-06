package org.lanqiao.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.lanqiao.dao.CategoryDao;
import org.lanqiao.entity.Category;
import org.lanqiao.entity.News;
import org.lanqiao.util.DBUtil;

public class CategoryDaoImpl implements CategoryDao{

	@Override
	public List<Category> list() {
		// TODO Auto-generated method stub
		List<Category> list = new ArrayList<Category>();
		Connection conn = null;
		//，所有sql语句运行前，必然有个解析过程，
		PreparedStatement ps = null;
		ResultSet rs = null;
		//获取链接
		conn = DBUtil.getConnection();
		String sql = "select * from t_category order by ordervalue";
		try {
			ps = conn.prepareStatement(sql);
			rs= ps.executeQuery();
			Category category = null;
			while(rs.next()){
				
				category = new Category(rs.getString("cid"),rs.getString("cname"));
				list.add(category);
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

}

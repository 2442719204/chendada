package org.lanqiao.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.lanqiao.dao.GoodsDao;
import org.lanqiao.entity.Category;
import org.lanqiao.entity.Goods;
import org.lanqiao.entity.News;
import org.lanqiao.entity.PageInfo;
import org.lanqiao.entity.Publisher;
import org.lanqiao.util.DBUtil;

public class GoodsDaoImpl implements GoodsDao{

	@Override
	public PageInfo<Goods> list(String cid,int pagesize, int pageindex) {
		PageInfo<Goods> pageInfos = new PageInfo<Goods>();
		Connection conn = null;
		PreparedStatement preparedStatement  = null;
		ResultSet rs = null;
		int startPage = (pageindex-1)*pagesize+1;
		int endPage = pageindex*pagesize;
		conn = DBUtil.getConnection();
		Category cate = getCategory(cid);
		Publisher publisher = null;
		String sql = "select t2.* from(select t1.*,rownum rn from(select * from t_goods where cid=?) t1)t2 where rn>=? and rn<=?";
		Goods good = null;
		try {
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, cid);
			preparedStatement.setInt(2, startPage);
			preparedStatement.setInt(3, endPage);
			rs = preparedStatement.executeQuery();
			while(rs.next()){
	
				publisher = getPublisher(rs.getString("pid"));
				good = new Goods(rs.getString("gid"),rs.getString("gtitle") ,rs.getString("gauthor") ,rs.getDouble("gsaleprice") , rs.getDouble("ginprice"), rs.getString("gdesc"),rs.getString("gimg") , rs.getInt("gclicks"), cate,publisher );
				pageInfos.getData().add(good);
			
			}
			int totalnumber = getTotalNumber(cid);
			int totalpage = totalnumber%pagesize==0?totalnumber/pagesize:totalnumber/pagesize+1;
			
			pageInfos.setIsfirstpage(pageindex==1);
			pageInfos.setIslastpage(pageindex==totalpage);
			pageInfos.setPageindex(pageindex);
			pageInfos.setPagesize(pagesize);
			pageInfos.setTotalnumber(totalnumber);
			pageInfos.setTotalpage(totalpage);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return pageInfos;
	}
	
	public Publisher getPublisher(String pid) {
		Connection conn = null;
		//，所有sql语句运行前，必然有个解析过程，
		PreparedStatement ps = null;
		ResultSet rs = null;
		//获取链接
		String name = null;
		Publisher publisher = null;
		conn = DBUtil.getConnection();
		String sql = "select * from t_publisher where pid=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, pid);
			rs= ps.executeQuery();
			if(rs.next()){
				name = rs.getString("pname");
			}
			publisher = new Publisher(pid, name);
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
		return publisher;
	}

	public Category getCategory(String cid) {
		Connection conn = null;
		//，所有sql语句运行前，必然有个解析过程，
		PreparedStatement ps = null;
		ResultSet rs = null;
		//获取链接
		String name = null;
		Category category = null;
		conn = DBUtil.getConnection();
		String sql = "select * from t_category where cid=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, cid);
			rs= ps.executeQuery();
			if(rs.next()){
				name = rs.getString("cname");
			}
			category = new Category(cid, name);
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
		return category;
	}

	@Override
	public int getTotalNumber(String cid) {
	
		Connection conn = null;
		//，所有sql语句运行前，必然有个解析过程，
		PreparedStatement ps = null;
		ResultSet rs = null;
		//获取链接
		int total = 0;
		conn = DBUtil.getConnection();
		String sql = "select count(*) from t_goods where cid=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, cid);
			rs= ps.executeQuery();
			if(rs.next()){
				total = rs.getInt(1);
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
		
		return total;
	}

	@Override
	public Goods get(String gid) {
		Connection conn = null;
		PreparedStatement pre = null;
		ResultSet rs = null;
		conn = DBUtil.getConnection();
		Goods goods = null;
		String sql = "select * from t_goods where gid=?";
		try {
			pre = conn.prepareStatement(sql);
			pre.setString(1, gid);
			rs = pre.executeQuery();
			while(rs.next()){
				String cid = rs.getString("cid");
				String pid = rs.getString("pid");
				Category cate = getCategory(cid);
				Publisher publisher = getPublisher(pid);
				goods = new Goods(rs.getString("gid"),rs.getString("gtitle") ,rs.getString("gauthor") ,rs.getDouble("gsaleprice") , rs.getDouble("ginprice"), rs.getString("gdesc"),rs.getString("gimg") , rs.getInt("gclicks"), cate,publisher );
				}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return goods;
	}

	@Override
	public void insertGoods(Goods goods) {
		Connection conn = null;
		PreparedStatement pre = null;
		ResultSet rs = null;
		conn = DBUtil.getConnection();
		String sql = "insert into t_goods values(?,?,?,?,?,?,?,?,?,?)";
		try {
			pre = conn.prepareStatement(sql);
			pre.setString(1, goods.getGid());
			pre.setString(2, goods.getGtitle());
			pre.setString(3, goods.getGauthor());
			pre.setDouble(4, goods.getGsaleprice());
			pre.setDouble(5, goods.getGinprice());
			pre.setString(6, goods.getGdesc());
			pre.setString(7, goods.getGimg());
			pre.setInt(8, goods.getGclicks());
			pre.setString(9, goods.getCategory().getCid());
			pre.setString(10, goods.getPublisher().getPid());
			pre.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	@Override
	public Category getCategoryBycname(String cname) {
		Connection conn = null;
		//，所有sql语句运行前，必然有个解析过程，
		PreparedStatement ps = null;
		ResultSet rs = null;
		//获取链接
		String cid = null;
		Category category = null;
		conn = DBUtil.getConnection();
		String sql = "select * from t_category where cname=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, cname);
			rs= ps.executeQuery();
			if(rs.next()){
				cid = rs.getString("cid");
			}
			System.out.println("eeeeeeeeeee"+cid);
			category = new Category(cid, cname);
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
		return category;
	}

	@Override
	public Publisher getPublisherByPname(String pname) {
		Connection conn = null;
		//，所有sql语句运行前，必然有个解析过程，
		PreparedStatement ps = null;
		ResultSet rs = null;
		//获取链接
		String pid = null;
		Publisher publisher = null;
		conn = DBUtil.getConnection();
		String sql = "select * from t_publisher where pname=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, pname);
			rs= ps.executeQuery();
			if(rs.next()){
				pid = rs.getString("pid");
			}
			publisher = new Publisher(pid, pname);
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
		return publisher;
	}

	@Override
	public void updateGoods(Goods goods) {	
		Connection conn = null;
		//，所有sql语句运行前，必然有个解析过程，
		PreparedStatement ps = null;
		//获取链接
		String pid = null;
		Publisher publisher = null;
		conn = DBUtil.getConnection();
		String sql = "update t_goods set gtitle=?,gauthor=?,gsaleprice=?,ginprice=?,gdesc=?,gimg=?,gclicks=?,cid=?,pid=? where gid =?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, goods.getGtitle());
			ps.setString(2, goods.getGauthor());
			ps.setDouble(3, goods.getGsaleprice());
			ps.setDouble(4, goods.getGinprice());
			ps.setString(5, goods.getGdesc());
			ps.setString(6, goods.getGimg());
			ps.setInt(7, goods.getGclicks());
			ps.setString(8, goods.getCategory().getCid());
			ps.setString(9, goods.getPublisher().getPid());
			ps.setString(10, goods.getGid());
			ps.executeUpdate();
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

	@Override
	public void removeGoods(String gid) {
		Connection conn = null;
		//，所有sql语句运行前，必然有个解析过程，
		PreparedStatement ps = null;
		//获取链接
		Publisher publisher = null;
		conn = DBUtil.getConnection();
		String sql = "delete from t_goods where gid=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, gid);
			ps.executeUpdate();
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

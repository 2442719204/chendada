package org.lanqiao.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.lanqiao.dao.UserDao;
import org.lanqiao.entity.PasswordAnswer;
import org.lanqiao.entity.Publisher;
import org.lanqiao.entity.User;
import org.lanqiao.util.DBUtil;

public class UserDaoImpl implements UserDao{

	@Override
	public void insert(User user) {
		Connection conn = null;
		//，所有sql语句运行前，必然有个解析过程，
		PreparedStatement ps = null;
		conn = DBUtil.getConnection();
		String sql = "insert into t_user values(?,?,?,?,?,?,?,?,?)";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, user.getUserid());
			ps.setString(2, user.getUemail());
			ps.setString(3, user.getUloginid());
			ps.setString(4, user.getUpassword());
			ps.setString(5, user.getUsex());
			ps.setString(6, user.getUaddress());
			ps.setString(7, user.getUtel());
			ps.setString(8, user.getUstateid());
			ps.setString(9, user.getUroleid());
			ps.execute();
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
	public User getUserByLoginId(String loginid) {
		Connection conn = null;
		//，所有sql语句运行前，必然有个解析过程，
		PreparedStatement ps = null;
		ResultSet rs = null;
		User user = null;
		conn = DBUtil.getConnection();
		String sql = "select * from t_user where uloginid=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, loginid);
			rs =  ps.executeQuery();
			if(rs.next()){
				user = new User(rs.getString("userid"), rs.getString("uemail"), rs.getString("uloginid"), rs.getString("upassword"), rs.getString("usex"), rs.getString("uaddress"), rs.getString("utel"), rs.getString("ustateid"), rs.getString("uroleid"));
			
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
		return user;
	}

	@Override
	public void updateUser(User user) {
		Connection conn = null;
		//，所有sql语句运行前，必然有个解析过程，
		PreparedStatement ps = null;
		conn = DBUtil.getConnection();
		String sql = "update t_user set uemail=?,uloginid=?,upassword=?,usex=?,uaddress=?,utel=?,ustateid=?,uroleid=? where userid=?";
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, user.getUemail());
			ps.setString(2, user.getUloginid());
			ps.setString(3, user.getUpassword());
			ps.setString(4, user.getUsex());
			ps.setString(5, user.getUaddress());
			ps.setString(6, user.getUtel());
			ps.setString(7, user.getUstateid());
			ps.setString(8, user.getUroleid());
			ps.setString(9, user.getUserid());
			ps.execute();
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
	public List<User> userlist() {
		List<User> list = new ArrayList<User>();
		Connection conn = null;
		//，所有sql语句运行前，必然有个解析过程，
		PreparedStatement ps = null;
		conn = DBUtil.getConnection();
		ResultSet rs = null;
		User user = null;
		String sql = "select * from t_user";
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				user = new User(rs.getString("userid"), rs.getString("uemail"), rs.getString("uloginid"), rs.getString("upassword"), rs.getString("usex"), rs.getString("uaddress"), rs.getString("utel"), rs.getString("ustateid"), rs.getString("uroleid"));
				list.add(user);
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
		
		return list;
	}

	@Override
	public void removeUser(String uid) {
		
		Connection conn = null;
		//，所有sql语句运行前，必然有个解析过程，
		PreparedStatement ps = null;
		conn = DBUtil.getConnection();
		String sql = "DELETE FROM t_user WHERE userid=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, uid);
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
	public List<User> getUserBySearch(String loginid) {
		// TODO Auto-generated method stub
		Connection conn = null;
		//，所有sql语句运行前，必然有个解析过程，
		List<User> list = new ArrayList<User>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		User user = null;
		conn = DBUtil.getConnection();
		String sql = "select * from t_user where uloginid like ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1,"%"+ loginid+"%");
			rs =  ps.executeQuery();
			while(rs.next()){
				user = new User(rs.getString("userid"), rs.getString("uemail"), rs.getString("uloginid"), rs.getString("upassword"), rs.getString("usex"), rs.getString("uaddress"), rs.getString("utel"), rs.getString("ustateid"), rs.getString("uroleid"));
				list.add(user);
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
	public User getUserByUserId(String userid) {
		Connection conn = null;
		//，所有sql语句运行前，必然有个解析过程，
		PreparedStatement ps = null;
		ResultSet rs = null;
		User user = null;
		conn = DBUtil.getConnection();
		String sql = "select * from t_user where userid=? ";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1,userid);
			rs =  ps.executeQuery();
			if(rs.next()){
				user = new User(rs.getString("userid"), rs.getString("uemail"), rs.getString("uloginid"), rs.getString("upassword"), rs.getString("usex"), rs.getString("uaddress"), rs.getString("utel"), rs.getString("ustateid"), rs.getString("uroleid"));
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
		return user;
	}

}

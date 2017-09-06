package org.lanqiao.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.lanqiao.dao.PasswordAnswerDao;
import org.lanqiao.entity.PasswordAnswer;
import org.lanqiao.util.DBUtil;

public class PasswordAnswerImpl implements PasswordAnswerDao{

	@Override
	public void insertPasswordAnswer(PasswordAnswer passwordAnswer) {
		
		Connection conn = null;
		//，所有sql语句运行前，必然有个解析过程，
		PreparedStatement ps = null;
		conn = DBUtil.getConnection();
		String sql = "insert into t_passwordanswer values(?,?,?,?,?)";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, passwordAnswer.getAnswerid());
			ps.setString(2, passwordAnswer.getAquestion());
			ps.setString(3, passwordAnswer.getAnswer());
			ps.setString(4, passwordAnswer.getEmail());
			ps.setString(5, passwordAnswer.getUserid());
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
	public void updatePasswordAnswer(PasswordAnswer passwordAnswer) {
		Connection conn = null;
		//，所有sql语句运行前，必然有个解析过程，
		PreparedStatement ps = null;
		conn = DBUtil.getConnection();
		String sql = "update t_passwordanswer set aquestion=?,answer=?,email=? where answerid=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, passwordAnswer.getAquestion());
			ps.setString(2, passwordAnswer.getAnswer());
			ps.setString(3, passwordAnswer.getEmail());
			ps.setString(4, passwordAnswer.getAnswerid());
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
	public PasswordAnswer searchPasswordAnswer(String userid) {
		Connection conn = null;
		//，所有sql语句运行前，必然有个解析过程，
		PreparedStatement ps = null;
		conn = DBUtil.getConnection();
		ResultSet rs = null;
		PasswordAnswer psan  = null;
		String sql = "select * from t_passwordanswer where userid=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, userid);
			rs = ps.executeQuery();
			if(rs.next()){
				psan = new PasswordAnswer(rs.getString("answerid"),rs.getString("aquestion") , rs.getString("answer"), rs.getString("userid"),rs.getString("email"));
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
		return psan;
	}

	@Override
	public void removePasswordAnswer(String userid) {
		Connection conn = null;
		//，所有sql语句运行前，必然有个解析过程，
		PreparedStatement ps = null;
		conn = DBUtil.getConnection();
		String sql = "DELETE FROM t_passwordanswer WHERE userid=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, userid);
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

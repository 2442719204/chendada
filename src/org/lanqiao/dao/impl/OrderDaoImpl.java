package org.lanqiao.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.lanqiao.dao.OrderDao;
import org.lanqiao.entity.Order;
import org.lanqiao.entity.OrderDetail;
import org.lanqiao.entity.User;
import org.lanqiao.util.DBUtil;

public class OrderDaoImpl implements OrderDao {

	@Override
	public void insertOrder(Order order) {
		Connection conn = null;
		PreparedStatement pre = null;
		conn = DBUtil.getConnection();
		String sql = "insert into t_order values(?,?,?,?,?)";
		try {
			pre = conn.prepareStatement(sql);
			pre.setString(1, order.getOrderid());
			pre.setString(2, order.getGid());
			pre.setString(3, order.getUserid());
			pre.setDouble(4, order.getTotalprice());
			Date date = new Date(order.getOrderdate().getTime());
			pre.setDate(5, date);
			pre.executeUpdate();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void insertOrderDetail(OrderDetail orderDetail) {
		Connection conn = null;
		PreparedStatement pre = null;
		conn = DBUtil.getConnection();
		String sql = "insert into t_orderdetail values(?,?,?,?,?)";
		try {
			pre = conn.prepareStatement(sql);
			pre.setString(1, orderDetail.getOrderdetailid());
			pre.setString(2, orderDetail.getGtitle());
			pre.setDouble(3, orderDetail.getGsalprice());
			pre.setDouble(4, orderDetail.getGnumber());
			pre.setString(5, orderDetail.getOrderid());
			pre.executeUpdate();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void removeOrder(String userid) {

		Connection conn = null;
		//，所有sql语句运行前，必然有个解析过程，
		PreparedStatement ps = null;
		conn = DBUtil.getConnection();
		String sql = "DELETE FROM t_order WHERE userid=?";
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

	@Override
	public void removeOrderDetail(String orderid) {

		Connection conn = null;
		//，所有sql语句运行前，必然有个解析过程，
		PreparedStatement ps = null;
		conn = DBUtil.getConnection();
		String sql = "DELETE FROM t_orderdetail WHERE orderid=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, orderid);
			int flag = ps.executeUpdate();
			System.out.println("我是flag"+flag);
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
	public Map<Integer, String> getOrderidByUserid(String userid) {
		 Map<Integer, String> map = new HashMap<Integer, String>();
		Connection conn = null;
		PreparedStatement pre = null;
		ResultSet rs = null;
		conn = DBUtil.getConnection();
		int index = 0;
		String sql = "select orderid from t_order where  userid=? ";
		try {
			pre = conn.prepareStatement(sql);
			pre.setString(1, userid);
			rs = pre.executeQuery();
			while(rs.next()){
				index++;
				String orderid = rs.getString(1);
				map.put(index, orderid);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		return map;
	}

	@Override
	public void removeOrderByGoodsId(String goodid) {

		Connection conn = null;
		//，所有sql语句运行前，必然有个解析过程，
		PreparedStatement ps = null;
		conn = DBUtil.getConnection();
		String sql = "DELETE FROM t_order WHERE gid=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, goodid);
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
	public List<Order> getAllOrder() {
		List<Order> list = new ArrayList<Order>();
		Connection conn = null;
		//，所有sql语句运行前，必然有个解析过程，
		PreparedStatement ps = null;
		
		PreparedStatement ps2 = null;
		
		conn = DBUtil.getConnection();
		ResultSet rs = null;
		
		ResultSet rs2 = null;
		
		Order order = null;
		String sql = "select * from t_order";
		
		String sql2 = "select uaddress from t_user where userid=?";
		
		
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			ps2 = conn.prepareStatement(sql2);
			
			
			
			while(rs.next()){				
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
				String str=sdf.format(rs.getDate("orderdate"));  
				String userid = rs.getString("userid");
				ps2.setString(1, userid);		
				rs2 = ps2.executeQuery();
				String address = null;
				if(rs2.next()){
					address = rs2.getString(1);
					order = new Order(rs.getString("orderid"),rs.getString("gid") , rs.getString("userid"),rs.getDouble("totalprice") ,str,address);
					
				}
				
				list.add(order);
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
	public void UpdateOrder(Order order) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Order> getAllOrderById(String userid) {
		// TODO Auto-generated method stub
		List<Order> list = new ArrayList<Order>();
		Connection conn = null;
		//，所有sql语句运行前，必然有个解析过程，
		PreparedStatement ps = null;
		conn = DBUtil.getConnection();
		ResultSet rs = null;	
		Order order = null;
		String sql = "select * from t_order where userid=?";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, userid);
			rs = ps.executeQuery();		
			while(rs.next()){
				order = new Order(rs.getString("orderid"),rs.getString("gid") , rs.getString("userid"),rs.getDouble("totalprice") ,rs.getDate("orderdate"));
				list.add(order);
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
	public List<OrderDetail> getAllOrderDetailById(String orderid) {
		// TODO Auto-generated method stub
				List<OrderDetail> list = new ArrayList<OrderDetail>();
				Connection conn = null;
				//，所有sql语句运行前，必然有个解析过程，
				PreparedStatement ps = null;
			
				conn = DBUtil.getConnection();
				ResultSet rs = null;
				OrderDetail order = null;
				String sql = "select * from t_orderdetail where orderid=?";
			
				try {
					ps = conn.prepareStatement(sql);
					ps.setString(1, orderid);
					rs = ps.executeQuery();
					
					
					while(rs.next()){
						order = new OrderDetail(rs.getString("orderdetailid"), rs.getString("gtitle"),rs.getDouble("gsalprice") ,rs.getDouble("gnumber") ,rs.getString("orderid") );
						list.add(order);
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
	public Order getOrderByOrderId(String orderid) {
		Connection conn = null;
		//，所有sql语句运行前，必然有个解析过程，
		PreparedStatement ps = null;
	
		conn = DBUtil.getConnection();
		ResultSet rs = null;
		Order order = null;
		String sql = "select * from t_order where orderid=?";
	
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, orderid);
			rs = ps.executeQuery();
			
			
			if(rs.next()){
			
				order = new Order(orderid, null, rs.getString("userid"), rs.getDouble("totalprice"), rs.getDate("orderdate"));
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
		
		return order;
	}

	@Override
	public void updateOrder(Order order) {
		Connection conn = null;
		//，所有sql语句运行前，必然有个解析过程，
		PreparedStatement ps = null;
	
		conn = DBUtil.getConnection();
		java.sql.Date date = new Date(order.getOrderdate().getTime());
		String sql = "update  t_order set userid=?,totalprice=?,orderdate=? where orderid=?";
	
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, order.getUserid());
			ps.setDouble(2, order.getTotalprice());
			ps.setDate(3, date);
			ps.setString(4, order.getOrderid());
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
	public void removeOrderByOrderId(String orderid) {
		Connection conn = null;
		//，所有sql语句运行前，必然有个解析过程，
		PreparedStatement ps = null;
	
		conn = DBUtil.getConnection();
		String sql = "delete from  t_order where orderid=?";
	
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, orderid);
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

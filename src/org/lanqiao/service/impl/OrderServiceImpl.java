package org.lanqiao.service.impl;

import java.util.List;
import java.util.Map;

import org.lanqiao.dao.OrderDao;
import org.lanqiao.dao.impl.OrderDaoImpl;
import org.lanqiao.entity.Order;
import org.lanqiao.entity.OrderDetail;
import org.lanqiao.service.OrderService;

public class OrderServiceImpl implements OrderService{
	OrderDao os = null;
	public OrderServiceImpl() {
		os = new OrderDaoImpl();
	}
	@Override
	public void insertOrder(Order order) {
		os.insertOrder(order);
		
	}

	@Override
	public void insertOrderDetail(OrderDetail orderDetail) {
		
		os.insertOrderDetail(orderDetail);
	}
	@Override
	public void removeOrder(String userid) {
		os.removeOrder(userid);
		
	}
	@Override
	public void removeOrderDetail(String orderid) {
		os.removeOrderDetail(orderid);
		
	}
	@Override
	public Map<Integer, String> getOrderidByUserid(String userid) {
		// TODO Auto-generated method stub
		return os.getOrderidByUserid(userid);
	}
	@Override
	public void removeOrderByGoodid(String goodid) {
		os.removeOrderByGoodsId(goodid);
	}
	@Override
	public List<Order> getAllOrder() {
		// TODO Auto-generated method stub
		return os.getAllOrder();
	}
	@Override
	public List<Order> getAllOrderById(String userid) {
		// TODO Auto-generated method stub
		return os.getAllOrderById(userid);
	}
	@Override
	public List<OrderDetail> getAllOrderDetailById(String userid) {
		// TODO Auto-generated method stub
		return os.getAllOrderDetailById(userid);
	}
	@Override
	public Order getOrderByOrderId(String orderid) {
		// TODO Auto-generated method stub
		return os.getOrderByOrderId(orderid);
	}
	@Override
	public void updateOrder(Order order) {
		// TODO Auto-generated method stub
		os.updateOrder(order);
	}
	@Override
	public void removeOrderByOrderId(String orderid) {
		os.removeOrderByOrderId(orderid);
	}

}

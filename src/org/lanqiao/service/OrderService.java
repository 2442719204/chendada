package org.lanqiao.service;

import java.util.List;
import java.util.Map;

import org.lanqiao.entity.Order;
import org.lanqiao.entity.OrderDetail;

public interface OrderService {
	public void insertOrder(Order order);
	public void insertOrderDetail(OrderDetail orderDetail);
	public void removeOrder(String userid);
	public void removeOrderDetail(String orderid);
	public Map<Integer, String> getOrderidByUserid(String userid);
	public void removeOrderByGoodid(String goodid);
	public List<Order> getAllOrder();
	public List<Order> getAllOrderById(String userid);
	public List<OrderDetail> getAllOrderDetailById(String orderid);
	public Order getOrderByOrderId(String orderid);
	public void updateOrder(Order order);
	public void removeOrderByOrderId(String orderid);
	
}

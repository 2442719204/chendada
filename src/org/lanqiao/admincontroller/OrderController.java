package org.lanqiao.admincontroller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.lanqiao.entity.Order;
import org.lanqiao.entity.OrderDetail;
import org.lanqiao.entity.User;
import org.lanqiao.service.OrderService;
import org.lanqiao.service.UserService;
import org.lanqiao.service.impl.OrderServiceImpl;
import org.lanqiao.service.impl.UserserviceImpl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * Servlet implementation class OrderController
 */
@WebServlet(name = "orderController", urlPatterns = { "/ordercontroller.do" })
public class OrderController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("type");
		OrderService os = new OrderServiceImpl();
		if(type.equals("order")){
			 List<Order> list = os.getAllOrder();
			 Gson gson = new Gson();
			 String data = gson.toJson(list);
			 response.getWriter().write(data);
		}else if(type.equals("modify")){
			String orderid = request.getParameter("orderid");
			String userid = request.getParameter("userid");
			System.out.println("wwwww"+userid);
			String totalprice = request.getParameter("totalprice");
			String orderdate = request.getParameter("orderdate");
			String orderaddress = request.getParameter("orderaddress");
			Order order = os.getOrderByOrderId(orderid);
			order.setOrderAddress(orderaddress);
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");//小写的mm表示的是分钟    
			java.util.Date date;
			try {
				date = sdf.parse(orderdate);
				order.setOrderdate(date);
				order.setTotalprice(Double.parseDouble(totalprice));
				order.setUserid(userid);
				os.updateOrder(order);
				
				UserService us = new UserserviceImpl();
				User user = us.getUserByUserId(userid);
				user.setUaddress(orderaddress);
				us.updateUser(user);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
			
			response.getWriter().write("1");
		}else if(type.equals("remove")){
			String data = request.getParameter("data");
			// System.out.println(data);
			Gson gson = new Gson();
			// 要将需要获取类型的泛型类作为TypeToken的泛型参数构造一个匿名的子类，
			// 就可以通过getType()方法获取到我们使用的泛型类的泛型参数类型。
			TypeToken<List<Order>> listType = new TypeToken<List<Order>>() {

			};
			List<Order> list = gson.fromJson(data, listType.getType());
			String orderid = list.get(0).getOrderid();
			List<OrderDetail> orderdetails =null;
			orderdetails = os.getAllOrderDetailById(orderid);
			if(orderdetails.size()>0){
				os.removeOrderDetail(orderid);
			}
			os.removeOrderByOrderId(orderid);
			response.getWriter().write("1");
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

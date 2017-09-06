package org.lanqiao.admincontroller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.lanqiao.entity.Order;
import org.lanqiao.entity.OrderDetail;
import org.lanqiao.service.OrderService;
import org.lanqiao.service.impl.OrderServiceImpl;

import com.google.gson.Gson;

/**
 * Servlet implementation class OrderDetailController
 */
@WebServlet(name = "orderDetailController", urlPatterns = { "/orderdetailcontroller.do" })
public class OrderDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userid = request.getParameter("id");
		System.out.println(userid);
		 OrderService os = new OrderServiceImpl();
		 List<OrderDetail> list = os.getAllOrderDetailById(userid);
		
		 Gson gson = new Gson();
		 String data = gson.toJson(list);
		 response.getWriter().write(data);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

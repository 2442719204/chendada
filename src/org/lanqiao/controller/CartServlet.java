package org.lanqiao.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.lanqiao.entity.Cart;
import org.lanqiao.entity.CookieItem;
import org.lanqiao.entity.Order;
import org.lanqiao.entity.OrderDetail;
import org.lanqiao.entity.User;
import org.lanqiao.service.OrderService;
import org.lanqiao.service.impl.OrderServiceImpl;
import org.lanqiao.util.CartUtil;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * Servlet implementation class CartServlet
 */
@WebServlet("/cart.do")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("type");
		if(type.equals("buy")){
			String gid = request.getParameter("gid");
			CookieItem item = new CookieItem(gid, 1);
			addItem(item, request, response);
			List<CookieItem> cart = getItems(request);
			List<Cart> list = CartUtil.convertCookieItemListToCartList(cart);
			request.setAttribute("cart", list);
			request.getRequestDispatcher("/WEB-INF/cart.jsp").forward(request, response);
		}else if(type!=null && type.equals("finishorder")){
			 List<CookieItem> list= getItems( request);
			 List<Cart> carts = CartUtil.convertCookieItemListToCartList(list);
			 String orderid = UUID.randomUUID().toString();
			 String userid = ((User)request.getSession().getAttribute("user")).getUserid();
			 double totalprice = 0 ;
			for(Cart c:carts){
				 totalprice += c.getGsaleprice()*c.getAmount();
			}
			Date orderdate = new Date();
			Order order = new Order(orderid, null, userid, totalprice, orderdate);
			OrderService os = new OrderServiceImpl();
			os.insertOrder(order);
			//订单详情
			for(Cart c:carts){
				String  orderdetailid = UUID.randomUUID().toString();
				String  gtitle = c.getGtitle();
				Double gsalprice = c.getGsaleprice();
				int gnumber = c.getAmount();
				OrderDetail od = new OrderDetail(orderdetailid, gtitle, gsalprice, gnumber, orderid);
				os.insertOrderDetail(od);
			}
			//删除购物车中的物品
			Cookie[] cookies = request.getCookies();
			for(Cookie c: cookies){
				if(c.getName().equals("cart")){
					c.setMaxAge(0);
					response.addCookie(c);
				}
			}
			request.getRequestDispatcher("/WEB-INF/success.jsp").forward(request, response);
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	private List<CookieItem> getItems(HttpServletRequest request){
		Cookie[] cookie = request.getCookies();
		Cookie  cart = null;
		for(Cookie c:cookie){
			if(c.getName().equals("cart")){
				cart=c;
			}
		}
		if(cart==null){
			return null;
		}
		String json = cart.getValue();
		Gson gson = new Gson();
		//要将需要获取类型的泛型类作为TypeToken的泛型参数构造一个匿名的子类，
		//就可以通过getType()方法获取到我们使用的泛型类的泛型参数类型。
		TypeToken<List<CookieItem>> listType = new TypeToken<List<CookieItem>>(){
			
		};
		List<CookieItem> list = gson.fromJson(json, listType.getType());
		
		return list;
		
	}
	private void addItem(CookieItem item,HttpServletRequest request,HttpServletResponse response){
		List<CookieItem> list = getItems(request);
		if(list==null){
			list = new ArrayList<CookieItem>();
			list.add(item);
		}
		if(list!=null){
			CookieItem currentItem = null;
			for(CookieItem goods : list){
				if(goods.getGid().equals(item.getGid())){
					currentItem = goods;
					break;
				}
			}
			if(currentItem==null){
				list.add(item);
			}else{
				currentItem.setAmount(currentItem.getAmount()+1);
			}
		}
		Gson gson = new Gson();
		String json = gson.toJson(list);
		Cookie cookie = new Cookie("cart", json);
		cookie.setMaxAge(60*60*24*365);
		response.addCookie(cookie);
	}
	private void removeItem(String gid,HttpServletRequest request,HttpServletResponse response){
		List<CookieItem> list = getItems(request);
		if(list==null){
			return ;
		}
		for(CookieItem goods:list){
			if(goods.getGid().equals(gid)){
				list.remove(goods);
			}
		}
		Gson gson = new Gson();
		String json = gson.toJson(list);
		Cookie cookie = new Cookie("cart", json);
		cookie.setMaxAge(60*60*24*365);
		response.addCookie(cookie);
	}

}

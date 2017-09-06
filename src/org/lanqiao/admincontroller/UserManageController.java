package org.lanqiao.admincontroller;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.lanqiao.entity.CookieItem;
import org.lanqiao.entity.User;
import org.lanqiao.service.OrderService;
import org.lanqiao.service.PasswordAnswerService;
import org.lanqiao.service.UserService;
import org.lanqiao.service.impl.OrderServiceImpl;
import org.lanqiao.service.impl.PasswordAnswerServiceImpl;
import org.lanqiao.service.impl.UserserviceImpl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * Servlet implementation class UserManageController
 */
@WebServlet(name = "userManageController", urlPatterns = { "/usercontroller.do" })
public class UserManageController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("type");

		if (type != null && type.equals("remove")) {
			String data = request.getParameter("data");
			// System.out.println(data);
			Gson gson = new Gson();
			// 要将需要获取类型的泛型类作为TypeToken的泛型参数构造一个匿名的子类，
			// 就可以通过getType()方法获取到我们使用的泛型类的泛型参数类型。
			TypeToken<List<User>> listType = new TypeToken<List<User>>() {

			};
			List<User> list = gson.fromJson(data, listType.getType());
			for (int i = 0; i < list.size(); i++) {
				String id = list.get(i).getUserid();
				UserService us = new UserserviceImpl();
				OrderService os = new OrderServiceImpl();
				PasswordAnswerService pas = new PasswordAnswerServiceImpl();
				Map<Integer, String> maps = os.getOrderidByUserid(id);
				for (int j = 0; j < maps.size(); j++) {
					os.removeOrderDetail(maps.get(j));
				}
				os.removeOrder(id);
				pas.removePasswordAnswer(id);
				us.removeUser(id);
				System.out.println(id);
			}
			response.getWriter().write("1");
		} else if (type != null && type.equals("add")) {
			String userid = UUID.randomUUID().toString();
			String uemail = request.getParameter("uemail");
			String uname = request.getParameter("uname");
			String password = request.getParameter("password");
			String usex = request.getParameter("usex");
			String utel = request.getParameter("utel");
			String uaddress = request.getParameter("uaddress");
			String ustateid = "B5868B7A06E54DAEB19658343D3A2B28";// 有效
			String uroleid = "116F9526C319462780B9CA72F6BB9B41";// 普通用户
			User user = new User(userid, uemail, uname, password, usex,
					uaddress, utel, ustateid, uroleid);
			UserService userService = new UserserviceImpl();
			userService.insetUser(user);
			response.getWriter().write("1");
		} else if (type != null && type.equals("modify")) {
			String uemail = request.getParameter("uemail");
			String uname = request.getParameter("uname");
			String password = request.getParameter("password");
			String usex = request.getParameter("usex");
			String utel = request.getParameter("utel");
			String uaddress = request.getParameter("uaddress");
			UserService userService = new UserserviceImpl();
			User user = userService.getUserByLoginId(uname);
			user.setUaddress(uaddress);
			user.setUemail(uemail);
			user.setUpassword(password);
			user.setUsex(usex);
			user.setUtel(utel);
			userService.updateUser(user);
			response.getWriter().write("1");
		} else {
			String uloginid = request.getParameter("uloginid");
			UserService us = new UserserviceImpl();
			System.out.println(uloginid);
			String data = null;
			if(uloginid!=null){
				List<User> list = us.getUserBySearch(uloginid);
				Gson gson = new Gson();
				data = gson.toJson(list);
//				response.getWriter().write(data);
				
			}else{
				List<User> list = us.getUserList();
				Gson gson = new Gson();
				data = gson.toJson(list);
			}
			
			
			response.getWriter().write(data);
		}

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

package org.lanqiao.admincontroller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.lanqiao.entity.User;
import org.lanqiao.service.UserService;
import org.lanqiao.service.impl.UserserviceImpl;

import com.google.gson.Gson;

/**
 * Servlet implementation class UserController
 */
@WebServlet("/userController.do")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("type");
		if(type!=null&&type.equals("list")){
			UserService us = new UserserviceImpl();		
			List<User> list = us.getUserList();
			Gson gson = new Gson();
			String data = gson.toJson(list);
			response.getWriter().write(data);
		}else if(type!=null&&type.equals("update")){
			String userid = request.getParameter("userid");
			String uloginid = request.getParameter("uloginid");
			String ustateid = request.getParameter("ustateid");
			String uroleid = request.getParameter("uroleid");
			UserService us = new UserserviceImpl();
			User user = us.getUserByUserId(userid);
			user.setUloginid(uloginid);
			user.setUroleid(uroleid);
			user.setUstateid(ustateid);
			us.updateUser(user);
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

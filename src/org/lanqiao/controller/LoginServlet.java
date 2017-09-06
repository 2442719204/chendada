package org.lanqiao.controller;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.lanqiao.entity.User;
import org.lanqiao.service.UserService;
import org.lanqiao.service.impl.UserserviceImpl;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login.do")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uname = request.getParameter("uname");
		String upassword = request.getParameter("upassword");
		UserService userService = new UserserviceImpl();
		User user = userService.login(uname, upassword);
		if(user!=null){
			request.getSession().setAttribute("user", user);
			String chk = request.getParameter("checkbox");
			if(chk!=null){
				Cookie cookie = new Cookie("uloginid", URLEncoder.encode(uname,"UTF-8"));
				cookie.setMaxAge(60*60*24*7);
				response.addCookie(cookie);
			}
		}
		request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}

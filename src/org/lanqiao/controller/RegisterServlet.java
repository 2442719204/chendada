package org.lanqiao.controller;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.lanqiao.entity.PasswordAnswer;
import org.lanqiao.entity.User;
import org.lanqiao.service.PasswordAnswerService;
import org.lanqiao.service.UserService;
import org.lanqiao.service.impl.PasswordAnswerServiceImpl;
import org.lanqiao.service.impl.UserserviceImpl;
import org.lanqiao.util.MailUtil;

/**
 * Servlet implementation class Register
 */
@WebServlet(name = "register", urlPatterns = { "/register.do" })
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String codes = request.getSession().getAttribute("codes").toString();
		String verificationcode = request.getParameter("verificationcode");
		if(codes.equalsIgnoreCase(verificationcode)){
			String userid = UUID.randomUUID().toString();
			String uemail = request.getParameter("uemail");
			String uname = request.getParameter("uname");
			String password = request.getParameter("password");
			String usex = request.getParameter("usex");
			String utel = request.getParameter("utel");
			String uaddress = request.getParameter("uaddress");
			String ustateid = "B5868B7A06E54DAEB19658343D3A2B28";//有效
			String uroleid = "116F9526C319462780B9CA72F6BB9B41";//普通用户
			User user = new User(userid,uemail,uname,password,usex,uaddress,utel,ustateid,uroleid);
			UserService userService = new UserserviceImpl();
			userService.insetUser(user);
			String answerid = UUID.randomUUID().toString();
			String squestion = request.getParameter("squestion");
			String sanswer = request.getParameter("sanswer");
			String backupemail = request.getParameter("backupemail");
			PasswordAnswer passwordAnswer = new PasswordAnswer(answerid, squestion, sanswer, userid, backupemail);
			PasswordAnswerService passwordAnswerService = new PasswordAnswerServiceImpl();
			passwordAnswerService.insertPasswordAnswer(passwordAnswer);
			//发一封激活邮件;
			MailUtil.sendMail("2442719204@qq.com", "蓝桥注册激活", "http://localhost:8080/webproject/enableUser.do?userid="+userid);
			request.getRequestDispatcher("/WEB-INF/registersuccess.jsp").forward(request, response);
		
		}else {
			request.getRequestDispatcher("/WEB-INF/regedit.jsp?msg=error").forward(request, response);
		}
			
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}

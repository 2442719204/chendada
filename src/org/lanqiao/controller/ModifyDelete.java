package org.lanqiao.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.lanqiao.entity.PasswordAnswer;
import org.lanqiao.entity.User;
import org.lanqiao.service.PasswordAnswerService;
import org.lanqiao.service.UserService;
import org.lanqiao.service.impl.PasswordAnswerServiceImpl;
import org.lanqiao.service.impl.UserserviceImpl;



/**
 * Servlet implementation class ModifyDelete
 */
@WebServlet("/md.do")
public class ModifyDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("type");
		if(type.equals("modify")){
			HttpSession  session = request.getSession();
			User user = (User) session.getAttribute("user");
			String upassword = request.getParameter("upassword");
			String usex = request.getParameter("usex");
			String utel = request.getParameter("utel");
			String uaddress = request.getParameter("uaddress");
			user.setUaddress(uaddress);
			user.setUtel(utel);
			user.setUsex(usex);
			user.setUpassword(upassword);
			UserService us = new UserserviceImpl();
			PasswordAnswerService pas = new PasswordAnswerServiceImpl();
			PasswordAnswer ps =  pas.searchPasswordAnswer(user.getUserid());
			String uquestion = request.getParameter("uquestion");
			String uanswer = request.getParameter("uanswer");
			String uemail = request.getParameter("uemail");
			ps.setAquestion(uquestion);
			ps.setAnswer(uanswer);
			ps.setEmail(uemail);
			pas.updatePasswordAnswer(ps);
			us.updateUser(user);
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

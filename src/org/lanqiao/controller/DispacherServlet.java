package org.lanqiao.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.lanqiao.entity.Goods;
import org.lanqiao.entity.News;
import org.lanqiao.entity.PasswordAnswer;
import org.lanqiao.entity.User;
import org.lanqiao.service.GoodsService;
import org.lanqiao.service.NewsService;
import org.lanqiao.service.PasswordAnswerService;
import org.lanqiao.service.UserService;
import org.lanqiao.service.impl.GoodsServiceImpl;
import org.lanqiao.service.impl.NewsServiceImpl;
import org.lanqiao.service.impl.PasswordAnswerServiceImpl;
import org.lanqiao.service.impl.UserserviceImpl;

/**
 * Servlet implementation class DispacherServlet
 */
@WebServlet(name = "dispacher", urlPatterns = { "/dispacher.do" })
public class DispacherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("type");
		String id = request.getParameter("id");
		if (type != null && type.equals("news") && id != null) {
			NewsService ns = new NewsServiceImpl();
			News news = ns.getNewsById(id);
			request.setAttribute("news", news);
			request.getRequestDispatcher("/WEB-INF/title.jsp").forward(request,response);
		} else if (type != null && type.equals("goods") && id != null) {
			GoodsService goodsService = new GoodsServiceImpl();
			Goods goods = goodsService.getGoodsBygid(id);
			request.setAttribute("goods", goods);
			request.getRequestDispatcher("/WEB-INF/detail.jsp").forward(request, response);
		} else if (type != null && type.equals("regedit")) {

			request.getRequestDispatcher("/WEB-INF/regedit.jsp").forward(request, response);
		} else if (type != null && type.equals("loginsuccess")) {
			Cookie[] cookies = request.getCookies();
			Cookie cookie = null;
			for (Cookie ck : cookies) {
				if (ck.getName().equals("uloginid")) {
					cookie = ck;
					break;
				}
			}
			if (cookie != null) {// 用户选择记住密码
				String uloginid = cookie.getValue();
				UserService userService = new UserserviceImpl();
				User user = userService.getUserByLoginId(uloginid);
				request.getSession().setAttribute("user", user);
			}

			request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request,response);
		} else if (type != null && type.equals("final")) {
			request.getRequestDispatcher("/WEB-INF/my.jsp").forward(request,response);
		} else if (type != null && type.equals("modify")) {
			HttpSession session = request.getSession();
			User user = (User) session.getAttribute("user");
			if(user!=null){
				String userid = user.getUserid();
				PasswordAnswerService psan = new PasswordAnswerServiceImpl();
				PasswordAnswer ps =  psan.searchPasswordAnswer(userid);
				request.setAttribute("ps", ps);
				request.getRequestDispatcher("/WEB-INF/modifyimformation.jsp")
						.forward(request, response);
			}
			
		}else if(type!=null && type.equals("cart")){
			request.getRequestDispatcher("/WEB-INF/cart.jsp").forward(request, response);
		}else if(type!=null && type.equals("ensureorder")){
			request.getRequestDispatcher("/WEB-INF/order.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}

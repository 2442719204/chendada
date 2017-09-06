package org.lanqiao.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.lanqiao.entity.Goods;
import org.lanqiao.entity.PageInfo;
import org.lanqiao.service.GoodsService;
import org.lanqiao.service.impl.GoodsServiceImpl;

/**
 * Servlet implementation class ListServlet
 */
@WebServlet(name = "listServlet", urlPatterns = { "/list.do" })
public class ListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String cid = request.getParameter("cid");
		String pageIndex = request.getParameter("pageIndex");
		if(pageIndex==null){
			pageIndex="1";
		}
		if(cid==null){
			cid="1";
		}
		int pageSize = 5;
		GoodsService goodsService = new GoodsServiceImpl();
		PageInfo<Goods> pageInfo  = goodsService.goodsList(cid, pageSize, Integer.parseInt(pageIndex));
		System.out.println(pageInfo.toString());
		request.setAttribute("pageInfo", pageInfo);
		request.getRequestDispatcher("/WEB-INF/list.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}

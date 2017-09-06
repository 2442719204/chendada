package org.lanqiao.admincontroller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.lanqiao.entity.Category;
import org.lanqiao.entity.Goods;
import org.lanqiao.service.CategoryService;
import org.lanqiao.service.GoodsService;
import org.lanqiao.service.impl.CategoryServiceImpl;
import org.lanqiao.service.impl.GoodsServiceImpl;

import com.google.gson.Gson;

/**
 * Servlet implementation class CategoryController
 */
@WebServlet(name = "categoryController", urlPatterns = { "/categorycontroller.do" })
public class CategoryController extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		CategoryService cs = new CategoryServiceImpl();
		 List<Category> list = cs.categoryList();
		 Gson gson = new Gson();
		 String json = gson.toJson(list);
		 response.getWriter().write(json);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

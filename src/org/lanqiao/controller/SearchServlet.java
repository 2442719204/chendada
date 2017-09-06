package org.lanqiao.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.lanqiao.entity.KeyWord;
import org.lanqiao.util.DBUtil;

import com.google.gson.Gson;

/**
 * Servlet implementation class SearchServlet
 */
@WebServlet(name = "searchServlet", urlPatterns = { "/search.do" })
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String keyword = request.getParameter("key");
		List<KeyWord> list = search(keyword);
		Gson gson = new Gson();
		String json = gson.toJson(list);
		response.setContentType("application/json");
		response.getWriter().write(json);
	}


	private List<KeyWord> search(String keyword) {
		List<KeyWord> list = new ArrayList<KeyWord>();
		Connection con = DBUtil.getConnection();
		String sql = "select t.* ,rownum from (select * from keyvalue where key like ?) t where rownum<4";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, "%"+keyword+"%");
			ResultSet rs = ps.executeQuery();
			KeyWord ky = null;
			while (rs.next()) {
				ky = new KeyWord(rs.getString(1),rs.getString(2));
				list.add(ky);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	
	}

}

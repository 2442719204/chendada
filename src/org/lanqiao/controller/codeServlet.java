package org.lanqiao.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Servlet implementation class codeServlet
 */
@WebServlet("/code.do")
public class codeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String chars = "QWERTYUIOPASDFGHJKLZXCVBNMqwertyuiopasdfghjklzxcvbnm0123456789";
		String codes = "";
		StringBuilder builder = new StringBuilder();
		Random rand = new Random();
		for(int i=0;i<4;i++){
			int index = rand.nextInt(61);
			builder.append(chars.charAt(index));	
		}
		codes = builder.toString();
		HttpSession session = request.getSession();
		session.setAttribute("codes", codes);
		//BufferedImage.TYPE_INT_RGB ： 表示一个图像，该图像具有整数像素的 8 位 RGB 颜色
		BufferedImage bufferedImage = new BufferedImage(100, 35, BufferedImage.TYPE_INT_RGB);
		//拿到一个画笔，绘制图片及图片内容
		Graphics g = bufferedImage.getGraphics();
		//填充颜色
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, 100, 35);
		//画边框
		g.setColor(Color.RED);
		g.drawRect(0, 0, 90, 33);
		
		g.setColor(Color.RED);
		g.setFont(new Font("宋体",Font.BOLD,20));
		
		g.drawString(codes, 20, 25);
		
		//输出图片
		//指定输出的图片格式数据
		response.setContentType("image/jpeg");
		ImageIO.write(bufferedImage, "jpg", response.getOutputStream());
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}

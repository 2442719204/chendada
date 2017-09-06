package org.lanqiao.admincontroller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.lanqiao.dao.GoodsDao;
import org.lanqiao.dao.impl.GoodsDaoImpl;
import org.lanqiao.entity.Category;
import org.lanqiao.entity.Goods;
import org.lanqiao.entity.PageInfo;
import org.lanqiao.entity.Publisher;
import org.lanqiao.entity.User;
import org.lanqiao.service.GoodsService;
import org.lanqiao.service.OrderService;
import org.lanqiao.service.impl.GoodsServiceImpl;
import org.lanqiao.service.impl.OrderServiceImpl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * Servlet implementation class GoodsController
 */
@WebServlet(name = "goodsController", urlPatterns = { "/goodscontroller.do" })
public class GoodsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("type");
		String data = null;
		if(type!=null&&type.equals("add")){
			String gid = UUID.randomUUID().toString();
			String gtitle = request.getParameter("gtitle");
			String gauthor = request.getParameter("gauthor");
			String gimg = request.getParameter("gimg");
			
			String gsaleprice = request.getParameter("gsaleprice");
			String ginprice = request.getParameter("ginprice");
			
			String gclicks = request.getParameter("gclicks");
			String cid = request.getParameter("cid");
			String pid = request.getParameter("pid");
			GoodsDao gd = new GoodsDaoImpl();
			 Category cate = gd.getCategory(cid);
			 Publisher pub = gd.getPublisher(pid);
			String gdesc = request.getParameter("gdesc");
			Goods good = new Goods(gid, gtitle, gauthor, Double.parseDouble(gsaleprice), Double.parseDouble(ginprice), gdesc, gimg, Integer.parseInt(gclicks), cate, pub);
			gd.insertGoods(good);
			data = "1";
		}else if(type!=null&&type.equals("modify")){
			String gid = request.getParameter("gid");
			String gtitle = request.getParameter("gtitle");
			String gauthor = request.getParameter("gauthor");
			String gimg = request.getParameter("gimg");
			
			String gsaleprice = request.getParameter("gsaleprice");
			String ginprice = request.getParameter("ginprice");
			
			String gclicks = request.getParameter("gclicks");
			String cname = request.getParameter("cid");
			String pname = request.getParameter("pid");
			GoodsDao gd = new GoodsDaoImpl();
			 Category cate = gd.getCategoryBycname(cname);
			 Publisher pub = gd.getPublisherByPname(pname);
			String gdesc = request.getParameter("gdesc");
			Goods goods = gd.get(gid);
			goods.setCategory(cate);
	        goods.setGauthor(gauthor);
	        goods.setGclicks(Integer.parseInt(gclicks));
	        goods.setGdesc(gdesc);
	        goods.setGimg(gimg);
	        goods.setGinprice(Double.parseDouble(ginprice));
	        goods.setGsaleprice(Double.parseDouble(gsaleprice));
	        goods.setGtitle(gtitle);
	        goods.setPublisher(pub);
	        GoodsService gs = new GoodsServiceImpl();
	        gs.updateGoods(goods);
	        data = "1";
			
		}else if(type!=null&&type.equals("remove")){
			String tempdata = request.getParameter("data");
			GoodsService gs = new GoodsServiceImpl();
			OrderService os = new OrderServiceImpl();
			// System.out.println(data);
			Gson gson = new Gson();
			// 要将需要获取类型的泛型类作为TypeToken的泛型参数构造一个匿名的子类，
			// 就可以通过getType()方法获取到我们使用的泛型类的泛型参数类型。
			TypeToken<List<Goods>> listType = new TypeToken<List<Goods>>() {

			};
			List<Goods> list = gson.fromJson(tempdata, listType.getType());
			for (int i = 0; i < list.size(); i++) {
				os.removeOrderByGoodid(list.get(i).getGid());
				gs.removeGoods(list.get(i).getGid());
			}
			data="1";
		}else{
			String cid = request.getParameter("cid");
			String pageIndex = request.getParameter("page");
			String pageSize = request.getParameter("rows");
			GoodsService gs = new GoodsServiceImpl();
			PageInfo<Goods> pages = gs.goodsList(cid, Integer.parseInt(pageSize), Integer.parseInt(pageIndex));
			Map<String, Object>  map = new HashMap<String, Object>();
			map.put("total", pages.getTotalnumber());
			map.put("rows", pages.getData());
			Gson gson = new Gson();
			data = gson.toJson(map);
		}
		
		response.getWriter().write(data);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

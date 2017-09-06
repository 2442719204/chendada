package org.lanqiao.entity;

public class OrderDetail {
	private String orderdetailid;
	private String gtitle;
	private double gsalprice;
	private double gnumber;
	private String orderid;
	public String getOrderdetailid() {
		return orderdetailid;
	}
	public void setOrderdetailid(String orderdetailid) {
		this.orderdetailid = orderdetailid;
	}
	public String getGtitle() {
		return gtitle;
	}
	public void setGtitle(String gtitle) {
		this.gtitle = gtitle;
	}
	public double getGsalprice() {
		return gsalprice;
	}
	public void setGsalprice(double gsalprice) {
		this.gsalprice = gsalprice;
	}
	public double getGnumber() {
		return gnumber;
	}
	public void setGnumber(double gnumber) {
		this.gnumber = gnumber;
	}
	public String getOrderid() {
		return orderid;
	}
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
	public OrderDetail(String orderdetailid, String gtitle, double gsalprice,
			double gnumber, String orderid) {
		super();
		this.orderdetailid = orderdetailid;
		this.gtitle = gtitle;
		this.gsalprice = gsalprice;
		this.gnumber = gnumber;
		this.orderid = orderid;
	}
	
}

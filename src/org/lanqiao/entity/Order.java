package org.lanqiao.entity;

import java.util.Date;

public class Order {
	private String orderid;
	private String gid;
	private String userid;
	private double totalprice;
	private Date orderdate;
	private String orderDateString;
	private String orderAddress;
	public String getOrderAddress() {
		return orderAddress;
	}

	public void setOrderAddress(String orderAddress) {
		this.orderAddress = orderAddress;
	}

	@Override
	public String toString() {
		return "Order [orderid=" + orderid + ", gid=" + gid + ", userid="
				+ userid + ", totalprice=" + totalprice + ", orderdate="
				+ orderdate + ", orderDateString=" + orderDateString
				+ ", orderAddress=" + orderAddress + "]";
	}

	public Order(String orderid, String gid, String userid, double totalprice,
			Date orderdate) {
		super();
		this.orderid = orderid;
		this.gid = gid;
		this.userid = userid;
		this.totalprice = totalprice;
		this.orderdate = orderdate;
	}
	
	public Order(String orderid, String gid, String userid, double totalprice,
			String orderDateString, String orderAddress) {
		super();
		this.orderid = orderid;
		this.gid = gid;
		this.userid = userid;
		this.totalprice = totalprice;
		this.orderDateString = orderDateString;
		this.orderAddress = orderAddress;
	}

	public Order(String orderid, String gid, String userid, double totalprice,
			 String orderDateString) {
		super();
		this.orderid = orderid;
		this.gid = gid;
		this.userid = userid;
		this.totalprice = totalprice;
		this.orderDateString = orderDateString;
	}

	public String getOrderDateString() {
		return orderDateString;
	}

	public void setOrderDateString(String orderDateString) {
		this.orderDateString = orderDateString;
	}

	public String getOrderid() {
		return orderid;
	}
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
	public String getGid() {
		return gid;
	}
	public void setGid(String gid) {
		this.gid = gid;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public double getTotalprice() {
		return totalprice;
	}
	public void setTotalprice(double totalprice) {
		this.totalprice = totalprice;
	}
	public Date getOrderdate() {
		return orderdate;
	}
	public void setOrderdate(Date orderdate) {
		this.orderdate = orderdate;
	}
	
}

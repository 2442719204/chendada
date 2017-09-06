package org.lanqiao.entity;

public class Cart {
	private String gid;
	private String gtitle;
	private Double gsaleprice;
	private Double ginprice;
	private int amount;
	public String getGid() {
		return gid;
	}
	public void setGid(String gid) {
		this.gid = gid;
	}
	public String getGtitle() {
		return gtitle;
	}
	public void setGtitle(String gtitle) {
		this.gtitle = gtitle;
	}
	public Double getGsaleprice() {
		return gsaleprice;
	}
	public void setGsaleprice(Double gsaleprice) {
		this.gsaleprice = gsaleprice;
	}
	public Double getGinprice() {
		return ginprice;
	}
	public void setGinprice(Double ginprice) {
		this.ginprice = ginprice;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public Cart(String gid, String gtitle, Double gsaleprice, Double ginprice,
			int amount) {
		super();
		this.gid = gid;
		this.gtitle = gtitle;
		this.gsaleprice = gsaleprice;
		this.ginprice = ginprice;
		this.amount = amount;
	}
	
	
}

package org.lanqiao.entity;

public class CookieItem {
	private String gid;
	private Integer amount;
	public String getGid() {
		return gid;
	}
	public void setGid(String gid) {
		this.gid = gid;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public CookieItem(String gid, Integer amount) {
		super();
		this.gid = gid;
		this.amount = amount;
	}
	
}

package org.lanqiao.entity;

import java.util.ArrayList;
import java.util.List;

public class PageInfo<T> {
	private int pagesize;
	private int pageindex;
	private int totalnumber;
	private int totalpage;
	public PageInfo(int pagesize, int pageindex, int totalnumber,
			int totalpage, boolean isfirstpage, boolean islastpage, List<T> data) {
		super();
		this.pagesize = pagesize;
		this.pageindex = pageindex;
		this.totalnumber = totalnumber;
		this.totalpage = totalpage;
		this.isfirstpage = isfirstpage;
		this.islastpage = islastpage;
		this.data = data;
	}
	public PageInfo() {
		super();
	}
	public int getPagesize() {
		return pagesize;
	}
	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}
	public int getPageindex() {
		return pageindex;
	}
	public void setPageindex(int pageindex) {
		this.pageindex = pageindex;
	}
	public int getTotalnumber() {
		return totalnumber;
	}
	public void setTotalnumber(int totalnumber) {
		this.totalnumber = totalnumber;
	}
	public int getTotalpage() {
		return totalpage;
	}
	public void setTotalpage(int totalpage) {
		this.totalpage = totalpage;
	}
	public boolean getIsfirstpage() {
		return isfirstpage;
	}
	public void setIsfirstpage(boolean isfirstpage) {
		this.isfirstpage = isfirstpage;
	}
	public boolean getIslastpage() {
		return islastpage;
	}
	public void setIslastpage(boolean islastpage) {
		this.islastpage = islastpage;
	}
	public List<T> getData() {
		return data;
	}
	public void setData(List<T> data) {
		this.data = data;
	}
	private boolean isfirstpage;
	private boolean islastpage;
	private List<T> data = new ArrayList<T>();
}

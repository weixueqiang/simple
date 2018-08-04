package com.jo.dy.ot.util;

import java.io.Serializable;
import java.util.List;

public class PageUtils<T> implements Serializable{

	private int code;
	private boolean success=true;
	private String msg="操作成功";
	private List<T> data;
	private int count;
	private int page=1;
	private int limit=10;
	private int start;
	
	public void fail(String msg) {
		this.success=false;
		this.msg=msg;
	}
	public void fail(int code,String msg) {
		this.success=false;
		this.msg=msg;
		this.code=code;
	}
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public List<T> getData() {
		return data;
	}
	public void setData(List<T> data) {
		this.data = data;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public int getStart() {
		start=(page-1)*limit;
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	
	
}

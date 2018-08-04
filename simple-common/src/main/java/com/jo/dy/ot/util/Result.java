package com.jo.dy.ot.util;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Result implements Serializable{

	private int code;
	private boolean success=true;
	private Object Data;
	private String msg="操作成功";
	private Map<String,Object> datas;
	
	public void fail(String msg) {
		this.success=false;
		this.msg=msg;
	}
	public void fail(int code,String msg) {
		this.success=false;
		this.msg=msg;
		this.code=code;
	}
	public void putData(String key,Object data) {
		if(datas==null) {
			datas=new HashMap<String,Object>();
		}
		datas.put(key, data);
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
	public Object getData() {
		return Data;
	}
	public void setData(Object data) {
		Data = data;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Map<String, Object> getDatas() {
		return datas;
	}
	public void setDatas(Map<String, Object> datas) {
		this.datas = datas;
	}
	
	
}

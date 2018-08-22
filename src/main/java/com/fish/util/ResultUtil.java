package com.fish.util;
/*
 * 作者: dou
 * 时间: 2018-08-22 17:01:37
 * desc: 返回结果封装
 * */
public class ResultUtil {
	
	private Boolean success;
	
	private Object data;
	
	private String message;
	
	
	
	/**
	 * 成功
	 * @param data
	 * @return
	 */
	public static ResultUtil success (Object data) {
		ResultUtil ru = new ResultUtil();
		ru.setData(data);
		ru.setSuccess(true);
		ru.setMessage(null);
		return ru;
	}
	
	/**
	 * 程序执行异常
	 * @param message
	 * @return
	 */
	public static ResultUtil fail (String message) {
		ResultUtil ru = new ResultUtil();
		ru.setData(null);
		ru.setSuccess(false);
		ru.setMessage(message);
		return ru;
	}

	
	/**
	 * setter && getter
	 * @return
	 */
	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}

	
	
	

}

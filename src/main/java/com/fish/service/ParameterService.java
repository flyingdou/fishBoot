package com.fish.service;
/*
 * 作者: dou
 * 时间: 2018-08-30 10:50:31
 * desc: 参数接口
 * */

import com.alibaba.fastjson.JSONObject;

public interface ParameterService {

	/**
	 * 查询参数列表
	 * 
	 * @param param
	 * @return
	 */
	JSONObject getParameterList(JSONObject param);
	
	
	/**
	 * 查询单类型参数
	 * @param param
	 * @return
	 */
	JSONObject getParameters (JSONObject param);

}

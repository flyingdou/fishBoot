package com.fish.service;
/**
 * 作者: dou
 * 时间: 2018-09-07 09:26:43
 * desc: 订单业务逻辑接口
 */

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONObject;

public interface OrderService {

	/**
	 * 生成订单
	 * 
	 * @param param
	 * @return
	 */
	JSONObject create(HttpServletRequest request, JSONObject param) throws Exception;

	
	
	/**
	 * 微信支付回调修改订单状态
	 * @param orderno
	 */
	void updateOrderStatus(String orderno);
	
	
	
	/**
	 * 
	 * @param param
	 * @return
	 */
	List<Map<String, Object>> rewardListByPost (JSONObject param);
	

	
	
	
	
	
	
	
	
	
	
	

}

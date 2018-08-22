package com.fish.service;

import com.alibaba.fastjson.JSONObject;

/**
 * 
 * @author dou 用户service层
 */
public interface UserService {

	/**
	 * 微信登录
	 * 
	 * @param param
	 * @return
	 */
	public JSONObject wechatLogin(JSONObject param) throws Exception;

	/**
	 * 查询用户垂钓基本信息
	 * 
	 * @param param
	 * @return
	 */
	public JSONObject getUserFishInfo(JSONObject param);

	/**
	 * 生成小程序二维码
	 * 
	 * @param param
	 * @return
	 */
	public String createQRCodeB(JSONObject param);

}

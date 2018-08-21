package com.fish.controller;

import java.net.URLDecoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fish.service.UserService;

/**
 * 
 * @author dou 用户管理控制器
 *
 */
@Controller
@RequestMapping("/user")
@RestController
public class UserController {

	/**
	 * 注入userService对象
	 */
	@Autowired
	private UserService userService;

	/**
	 * 微信登录
	 * 
	 * @param json
	 * @return
	 */
	@RequestMapping("/wechatLogin")
	public String wechatLogin(String json) {
		JSONObject ret = new JSONObject();
		try {
			// 处理请求参数
			JSONObject param = JSONObject.parseObject(json);
			ret = userService.wechatLogin(param);

		} catch (Exception e) {
			ret.fluentPut("success", false)
			   .fluentPut("message", e.toString())
			   ;
			e.printStackTrace();
		}

		return JSON.toJSONString(ret);
	}

	/**
	 * 查询用户个人数据
	 * 
	 * @param json
	 * @return
	 */
	@RequestMapping("getUserFishInfo")
	public String findMe(String json) {
		JSONObject ret = new JSONObject();
		try {
			// 处理请求参数
			JSONObject param = JSONObject.parseObject(URLDecoder.decode(json, "UTF-8"));
			JSONObject result = userService.getUserFishInfo(param);
			ret.fluentPut("success", true)
			   .fluentPut("userInfo", result)
			   ;
		} catch (Exception e) {
			e.printStackTrace();
			return JSON.toJSONString(e);
		}
		
		// 返回数据
		return JSON.toJSONString(ret);
	}

}

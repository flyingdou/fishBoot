package com.fish.controller;

import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fish.service.UserService;
import com.fish.util.ResultUtil;

/**
 * 
 * @author dou 用户管理控制器
 *
 */
@RestController
@RequestMapping("/user")
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
	
	
	/**
	 * 生成小程序二维码
	 * @param json
	 * @return
	 */
	@RequestMapping("/createQRCode")
	public String createQRCodeB(String json) {
		JSONObject ret = new JSONObject();
		try {
			// 处理请求参数
			JSONObject param = JSONObject.parseObject(URLDecoder.decode(json, "UTF-8"));
			String result = userService.createQRCodeB(param);
			ret.fluentPut("success", true)
			   .fluentPut("qrCode", result)
			   ;
		} catch (Exception e) {
			e.printStackTrace();
			return JSON.toJSONString(e);
		}
		
		
		// 返回数据
		return JSON.toJSONString(ret);
	}

	/**
	 * 达人榜
	 * @param json
	 * @return
	 */
	@RequestMapping("/getRanking")
	public String getRanking(String json) {
		try {
			// 处理请求参数
			JSONObject param = JSONObject.parseObject(URLDecoder.decode(json, "UTF-8"));
			List<Map<String, Object>> list = userService.getRanking(param);
			ResultUtil result = ResultUtil.success(list);
			return JSONObject.toJSONStringWithDateFormat(result, "yyyy-MM-dd HH:mm");
		} catch (Exception e) {
			e.printStackTrace();
			return JSON.toJSONString(e);
		}
	}
}

package com.fish.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;

/**
 * 
 * @author dou
 * 登录控制器
 */
@RestController
@RequestMapping("/login")
public class LoginController {
	
	
	
	@RequestMapping("/wechatLogin")
	@ResponseBody
	public String wechatLogin(String code, HttpServletRequest request, HttpServletResponse response) {
		JSONObject ret = new JSONObject();
		try {
			ret.fluentPut("success", true).fluentPut("message", "dou");
						
		} catch (Exception e) {
			ret.fluentPut("success", false).fluentPut("message", e.toString());
		}
		
		return ret.toString();
	}

}

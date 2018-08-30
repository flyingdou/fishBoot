package com.fish.controller;

import java.net.URLDecoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fish.service.ParameterService;
import com.fish.util.ResultUtil;

/*
 * 作者: dou
 * 时间: 2018-08-30 10:39:39
 * desc: 参数处理控制器
 * */
@RestController
@Controller
@RequestMapping("/parameter")
public class ParameterController {

	/**
	 * 注入parameterService对象
	 */
	@Autowired
	private ParameterService parameterService;

	/**
	 * 单类型查询参数
	 * 
	 * @param json
	 * @return
	 */
	@RequestMapping("/getParameters")
	public String getParameters(String json) {
		try {
			// 处理请求对象
			JSONObject param = JSONObject.parseObject(URLDecoder.decode(json, "UTF-8"));
			ResultUtil rs = ResultUtil.success(parameterService.getParameters(param));
			return JSON.toJSONString(rs);
		} catch (Exception e) {
			e.printStackTrace();
			return JSON.toJSONString(e);
		}

	}

	/**
	 * 多类型查询参数
	 * 
	 * @param json
	 * @return
	 */
	@RequestMapping("/getParameterList")
	public String getParamterList(String json) {
		try {
			// 处理请求参数
			JSONObject param = JSONObject.parseObject(URLDecoder.decode(json, "UTF-8"));
			ResultUtil rs = ResultUtil.success(parameterService.getParameterList(param));
			return JSON.toJSONString(rs);
		} catch (Exception e) {
			e.printStackTrace();
			return JSON.toJSONString(e);
		}

	}

}

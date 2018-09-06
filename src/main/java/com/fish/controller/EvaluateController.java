package com.fish.controller;

import java.net.URLDecoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fish.service.EvaluateService;
import com.fish.util.ResultUtil;

/*
 * 作者: dou
 * 时间: 2018-09-06 13:08:27
 * desc: 帖子评论控制器
 * */
@RestController
@RequestMapping("/evaluate")
public class EvaluateController {

	/**
	 * 注入evaluateService对象
	 */
	@Autowired
	private EvaluateService evaluateService;

	/**
	 * 评论、回复
	 * 
	 * @param json
	 * @return
	 */
	@RequestMapping("/evaluate")
	public String evaluate(String json) {
		try {
			// 处理请求参数
			JSONObject param = JSONObject.parseObject(URLDecoder.decode(json,"UTF-8"));
			ResultUtil rs = ResultUtil.success(evaluateService.evaluate(param));
			return JSON.toJSONString(rs);
		} catch (Exception e) {
			e.printStackTrace();
			return JSON.toJSONString(e);
		}
	}

	/**
	 * 帖子评论列表
	 * 
	 * @param json
	 * @return
	 */
	@RequestMapping("/evaluateList")
	public String evalueteList(String json) {
		try {
			JSONObject param = JSONObject.parseObject(URLDecoder.decode(json, "UTF-8"));
			ResultUtil rs = ResultUtil.success(evaluateService.evaluateList(param));
			return JSON.toJSONString(rs);
		} catch (Exception e) {
			e.printStackTrace();
			return JSON.toJSONString(e);
		}

	}

}

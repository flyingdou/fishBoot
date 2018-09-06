package com.fish.controller;

import java.net.URLDecoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fish.service.PraiseService;
import com.fish.util.ResultUtil;

/*
 * 作者: dou
 * 时间: 2018-09-04 09:35:20
 * desc: 点赞控制器
 * */
@RestController
@Controller
@RequestMapping("/praise")
public class PraiseController {

	/**
	 * 注入praiseService对象
	 */
	@Autowired
	private PraiseService praiseService;

	/**
	 * 点赞或取消点赞
	 * 
	 * @param json
	 * @return
	 */
	@RequestMapping("/praise")
	public String praise(String json) {
		try {
			// 处理请求参数
			JSONObject param = JSONObject.parseObject(URLDecoder.decode(json, "UTF-8"));
			ResultUtil rs = ResultUtil.success(praiseService.praise(param));
			return JSON.toJSONString(rs);

		} catch (Exception e) {
			e.printStackTrace();
			return JSON.toJSONString(e);
		}

	}

	/**
	 * 帖子点赞列表
	 * 
	 * @param json
	 * @return
	 */
	@RequestMapping("/praiseList")
	public String praiseList(String json) {
		try {
			// 处理请求参数
			JSONObject param = JSONObject.parseObject(URLDecoder.decode(json, "UTF-8"));
			ResultUtil rs = ResultUtil.success(praiseService.praiseList(param));
			return JSON.toJSONString(rs);
		} catch (Exception e) {
			e.printStackTrace();
			return JSON.toJSONString(e);
		}
	}
	
	
	

}

package com.fish.controller;

import java.net.URLDecoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fish.service.PostService;
import com.fish.util.ResultUtil;

/*
 * 作者: dou
 * 时间: 2018-08-30 14:56:58
 * desc: 帖子管理控制器
 * */
@RestController
@RequestMapping("/post")
public class PostController {

	/**
	 * 注入postService对象
	 */
	@Autowired
	private PostService postService;

	/**
	 * 发布帖子
	 * 
	 * @param json
	 * @return
	 */
	@RequestMapping("/releasePost")
	public String releasePost(String json) {
		try {
			// 处理请求参数
			JSONObject param = JSONObject.parseObject(URLDecoder.decode(json, "UTF-8"));
			ResultUtil rs = ResultUtil.success(postService.releasePost(param));
			return JSON.toJSONString(rs);

		} catch (Exception e) {
			e.printStackTrace();
			return JSON.toJSONString(e);
		}

	}
	
	

	/**
	 * 查询帖子列表
	 * 
	 * @param json
	 * @return
	 */
	@RequestMapping("/postList")
	public String postList(String json) {
		try {
			// 处理请求参数
			JSONObject param = JSONObject.parseObject(URLDecoder.decode(json, "UTF-8"));
			ResultUtil rs = ResultUtil.success(postService.postList(param));
			return JSON.toJSONString(rs);
		} catch (Exception e) {
			e.printStackTrace();
			return JSON.toJSONString(e);
		}

	}

	
	
	/**
	 * 查询帖子详情
	 * 
	 * @param json
	 * @return
	 */
	@RequestMapping("/postDetail")
	public String postDetail(String json) {
		try {
			// 处理请求参数
			JSONObject param = JSONObject.parseObject(URLDecoder.decode(json, "UTF-8"));
			ResultUtil rs = ResultUtil.success(postService.postDetail(param));
			return JSON.toJSONString(rs);
		} catch (Exception e) {
			e.printStackTrace();
			return JSON.toJSONString(e);
		}
		
	}
	
	
	

}

package com.fish.service;
/*
 * 作者: dou
 * 时间: 2018-08-30 15:01:25
 * desc: 帖子逻辑接口
 * */

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

public interface PostService {

	/**
	 * 发布帖子
	 * 
	 * @param param
	 * @return
	 */
	JSONObject releasePost(JSONObject param);

	/**
	 * 帖子列表
	 * 
	 * @param param
	 * @return
	 */
	List<Map<String, Object>> postList(JSONObject param);

	/**
	 * 帖子详情
	 * 
	 * @param param
	 * @return
	 */
	JSONObject postDetail(JSONObject param);

}

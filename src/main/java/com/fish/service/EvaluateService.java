package com.fish.service;
/*
 * 作者: dou
 * 时间: 2018-09-06 13:12:53
 * desc: 帖子评论业务逻辑接口
 * */

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

public interface EvaluateService {

	/**
	 * 帖子评论列表
	 * 
	 * @param param
	 * @return
	 */
	List<Map<String, Object>> evaluateList(JSONObject param);

}

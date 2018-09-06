package com.fish.service;
/*
 * 作者: dou
 * 时间: 2018-09-04 09:40:51
 * desc: 点赞业务逻辑接口
 * */

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

public interface PraiseService {

	/**
	 * 点赞或取消
	 * 
	 * @param param
	 * @return
	 */
	JSONObject praise(JSONObject param);

	/**
	 * 帖子点赞列表
	 * 
	 * @param param
	 * @return
	 */
	List<Map<String, Object>> praiseList(JSONObject param);

}

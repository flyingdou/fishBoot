package com.fish.service;
/*
 * 作者: dou
 * 时间: 2018-08-30 15:01:25
 * desc: 帖子逻辑接口
 * */

import com.alibaba.fastjson.JSONObject;

public interface PostService {
	
	/**
	 * 发布帖子
	 * @param param
	 * @return
	 */
	JSONObject releasePost (JSONObject param);

}

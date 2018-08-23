package com.fish.service;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

/**
 * 
 * @author 华文
 * @time 2018-08-22 16:19
 *
 */
public interface LiveService {

	/**
	 * 查询直播间列表
	 * 
	 * @return
	 */
	List<Map<String, Object>> getLiveList();

	/**
	 * 获得直播推流URL
	 * 
	 * @param param
	 * @return
	 * @throws Exception
	 */
	String liveUrl(JSONObject param) throws Exception;

	/**
	 * 改变直播状态
	 * 
	 * @param param
	 */
	void changeLiveStatus(JSONObject param);

	/**
	 * 查询视频列表
	 * 
	 * @return
	 */
	List<Map<String, Object>> getVideoList();
}

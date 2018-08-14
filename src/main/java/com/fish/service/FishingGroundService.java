package com.fish.service;

import java.util.Map;

import com.alibaba.fastjson.JSONObject;

/**
 * 钓场业务接口
 * 
 * @author Administrator
 *
 */
public interface FishingGroundService {

	/**
	 * 发布钓场
	 * 
	 * @return
	 */
	public JSONObject release(JSONObject param);

	/**
	 * 查询钓场列表
	 * 
	 * @return
	 */
	public JSONObject getFishingGroundList(JSONObject param);

	/**
	 * 查询钓场详情
	 * 
	 * @param param
	 * @return
	 */
	public Map<String, Object> getFishingGroundDetail(JSONObject param);
}

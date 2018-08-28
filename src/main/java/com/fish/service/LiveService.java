package com.fish.service;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.fish.pojo.Live;
import com.fish.pojo.Order;

/**
 * 
 * @author 华文
 * @time 2018-08-22 16:19
 *
 */
public interface LiveService {

	/**
	 * 保存直播数据(发布或修改)
	 * 
	 * @param param
	 * @return
	 */
	Live saveLive(JSONObject param);

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

	/**
	 * 查询直播数据
	 * 
	 * @param param
	 * @return
	 */
	Live getLiveDetailById(JSONObject param);

	/**
	 * 获取直播播放的Url
	 * 
	 * @param param
	 * @return
	 */
	Map<String, Object> play(JSONObject param);

	/**
	 * 生成直播订单
	 * 
	 * @param param
	 * @return
	 */
	Order createLiveOrder(JSONObject param);
}

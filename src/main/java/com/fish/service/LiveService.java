package com.fish.service;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author hw
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
}

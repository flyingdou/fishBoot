package com.fish.service;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.fish.pojo.City;

/**
 * 
 * @author 华文
 *
 */
public interface CityService {

	/**
	 * 查询城市列表
	 * 
	 * @param param
	 * @return
	 */
	List<City> getCityList(JSONObject param);

}

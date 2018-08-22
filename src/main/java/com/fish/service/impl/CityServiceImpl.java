package com.fish.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.fish.dao.CityMapper;
import com.fish.pojo.City;
import com.fish.service.CityService;

/**
 * 
 * @author 华文
 *
 */
@Service
@Transactional
public class CityServiceImpl implements CityService {

	@Autowired
	private CityMapper cityMapper;

	/**
	 * 查询城市列表
	 */
	@Override
	public List<City> getCityList(JSONObject param) {
		return cityMapper.getCityList(param);
	}

}

package com.fish.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fish.dao.ParameterMapper;
import com.fish.service.ParameterService;

/*
 * 作者: dou
 * 时间: 2018-08-30 10:56:07
 * desc: 参数处理实现类
 * */
@Service
@Transactional
public class ParameterServiceImpl implements ParameterService {

	/**
	 * 注入parameterMapper对象
	 */
	@Autowired
	private ParameterMapper parameterMapper;
	
	
	
	/**
	 * 多类型查询参数列表
	 */
	@Override
	public JSONObject getParameterList(JSONObject param) {
		JSONObject result = new JSONObject();
		JSONArray codes = param.getJSONArray("codes");
		for (int i = 0; i < codes.size(); i++) {
			JSONObject code = codes.getJSONObject(i);
			// 查询普通参数的，直接查询
			List<Map<String, Object>> paramList = parameterMapper.getParameterList(code);
			result.fluentPut(code.getString("name"), paramList);
		}
		
		// 返回数据
		return result;
	}



	/**
	 * 单类型查询参数列表
	 */
	@Override
	public JSONObject getParameters(JSONObject param) {
		JSONObject result = new JSONObject();
		List<Map<String, Object>> paramList = parameterMapper.getParameterList(param);
		result.fluentPut(param.getString("name"), paramList);
		return result;
	}
	
	
	
	
	

}

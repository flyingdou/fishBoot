package com.fish.dao;

import java.util.List;
import java.util.Map;

import com.fish.pojo.Parameter;

public interface ParameterMapper {
	int deleteByPrimaryKey(Long id);

	int insertSelective(Parameter record);

	Parameter selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(Parameter record);

	int updateByPrimaryKeyWithBLOBs(Parameter record);

	/**
	 * 查询参数
	 * 
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> getParameterList(Map<String, Object> map);

	
}
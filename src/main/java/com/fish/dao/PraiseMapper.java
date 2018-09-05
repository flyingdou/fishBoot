package com.fish.dao;

import java.util.List;
import java.util.Map;

import com.fish.pojo.Praise;

public interface PraiseMapper {
	int deleteByPrimaryKey(Integer id);

	int insertSelective(Praise record);

	Praise selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(Praise record);

	/**
	 * 查询帖子的有效点赞数
	 * 
	 * @param param
	 * @return
	 */
	Map<String, Object> praiseCount(Map<String, Object> param);
	
	
	
	/**
	 * 查询帖子的有效点赞列表
	 * @param param
	 * @return
	 */
	List<Map<String, Object>> praiseListByPost (Map<String, Object> param);

}
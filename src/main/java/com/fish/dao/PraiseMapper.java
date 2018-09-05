package com.fish.dao;

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

}
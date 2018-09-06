package com.fish.dao;

import java.util.List;
import java.util.Map;

import com.fish.pojo.Evaluate;

public interface EvaluateMapper {
	int deleteByPrimaryKey(Integer id);

	int insertSelective(Evaluate record);

	Evaluate selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(Evaluate record);

	/**
	 * 查询帖子评论列表
	 * 
	 * @param param
	 * @return
	 */
	List<Map<String, Object>> evaluateListByPost(Map<String, Object> param);

	/**
	 * 评论回复列表
	 * 
	 * @param param
	 * @return
	 */
	List<Map<String, Object>> replyList(Map<String, Object> param);

}
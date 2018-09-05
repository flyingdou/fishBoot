package com.fish.dao;

import java.util.List;
import java.util.Map;

import com.fish.pojo.Post;

public interface PostMapper {
	int deleteByPrimaryKey(Integer id);

	int insertSelective(Post record);

	Post selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(Post record);

	/**
	 * 查询帖子列表
	 * 
	 * @param param
	 * @return
	 */
	List<Map<String, Object>> postList(Map<String, Object> param);

	
	
	/**
	 * 帖子详情
	 * 
	 * @param param
	 * @return
	 */
	Map<String, Object> postDetail(Map<String, Object> param);
	
	
	
	
	
	
	
	

}
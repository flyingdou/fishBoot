package com.fish.dao;

import java.util.List;
import java.util.Map;

import com.fish.pojo.User;

public interface UserMapper {
	/**
	 * 根据id删除用户
	 * 
	 * @param id
	 * @return
	 */
	int deleteByPrimaryKey(Integer id);

	/**
	 * 新增用户
	 * 
	 * @param record
	 * @return
	 */
	int insertSelective(User record);

	/**
	 * 根据id查询用户
	 * 
	 * @param id
	 * @return
	 */
	User selectByPrimaryKey(Integer id);

	/**
	 * 更新用户
	 * 
	 * @param record
	 * @return
	 */
	int updateByPrimaryKeySelective(User record);

	/**
	 * 通过wechatId查询用户信息
	 * 
	 * @param map
	 * @return
	 */
	User getUserByWechatid(Map<String, Object> map);

	/**
	 * 查询用户垂钓基本信息
	 * 
	 * @param paramMap
	 * @return
	 */
	Map<String, Object> getUserFishInfo(Map<String, Object> paramMap);

	/**
	 * 达人榜
	 * 
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> getRanking(Map<String, Object> map);

}
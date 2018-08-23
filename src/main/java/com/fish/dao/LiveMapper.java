package com.fish.dao;

import java.util.List;
import java.util.Map;

import com.fish.pojo.Live;
import com.fish.pojo.User;

/**
 * 
 * @author 华文
 * @time 2018-08-22 16:15
 *
 */
public interface LiveMapper {

	/**
	 * 根据id删除数据
	 * 
	 * @param id
	 * @return
	 */
	int deleteByPrimaryKey(Integer id);

	/**
	 * 添加一条数据
	 * 
	 * @param record
	 * @return
	 */
	int insertSelective(User record);

	/**
	 * 根据id查询单条数据
	 * 
	 * @param id
	 * @return
	 */
	User selectByPrimaryKey(Integer id);

	/**
	 * 根据id修改一条数据
	 * 
	 * @param record
	 * @return
	 */
	int updateByPrimaryKeySelective(Live record);

	/**
	 * 查询直播间列表
	 * 
	 * @return
	 */
	List<Map<String, Object>> getLiveList();

	/**
	 * 根据用户查询直播数据
	 * 
	 * @param param
	 * @return
	 */
	Live getLiveByUser(Map<String, Object> param);

	/**
	 * 根据直播码查询直播数据
	 * 
	 * @param param
	 * @return
	 */
	Live getLiveByLiveNumber(Map<String, Object> param);

	/**
	 * 查询视频列表
	 * 
	 * @return
	 */
	List<Map<String, Object>> getVideoList();
}

package com.fish.dao;

import java.util.List;
import java.util.Map;

import com.fish.pojo.Live;

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
	int insertSelective(Live record);

	/**
	 * 根据id查询单条数据
	 * 
	 * @param id
	 * @return
	 */
	Live selectByPrimaryKey(Integer id);

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
	List<Map<String, Object>> getLiveList(Map<String, Object> param);

	/**
	 * 根据用户查询直播数据
	 * 
	 * @param param
	 * @return
	 */
	Live getLiveByUser(Integer userId);

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
	List<Map<String, Object>> getVideoList(Map<String, Object> param);

	/**
	 * 检查用户是否有观看直播的权限
	 * 
	 * @param param
	 * @return
	 */
	boolean checkUserPlayLivePower(Map<String, Object> param);

	/**
	 * 生成直播订单
	 * 
	 * @param param
	 * @return
	 */
	int createLiveOrder(Map<String, Object> param);

	/**
	 * 
	 * @return
	 */
	int getLiveSwitch();
}

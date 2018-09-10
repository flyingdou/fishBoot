package com.fish.dao;

import java.util.List;
import java.util.Map;

import com.fish.pojo.Order;

public interface OrderMapper {
	int deleteByPrimaryKey(Integer id);

	int insertSelective(Order record);

	Order selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(Order record);

	/**
	 * 帖子打赏列表
	 * 
	 * @param param
	 * @return
	 */
	List<Map<String, Object>> rewardListByPost(Map<String, Object> param);

	/**
	 * 根据订单号查询订单
	 * 
	 * @param param
	 * @return
	 */

	Order getOrderByNo(Map<String, Object> param);

}
package com.fish.dao;

import java.util.List;
import java.util.Map;

import com.fish.pojo.FishingGround;

/**
 * 
 * @author 华文
 *
 */
public interface FishingGroundMapper {
	int deleteByPrimaryKey(Integer id);

	int insertSelective(FishingGround record);

	FishingGround selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(FishingGround record);

	/**
	 * 通过id列表查询poi_id列表
	 * 
	 * @param ids
	 * @return
	 */
	List<String> getPoiIdListByIdList(String ids);

	/**
	 * 通过钓场id列表查询钓场列表信息
	 * 
	 * @param ids
	 * @return
	 */
	List<Map<String, Object>> getFishingGroundListByIdList(String ids);

	/**
	 * 查询钓场列表
	 * 
	 * @return
	 */
	List<Map<String, Object>> getFishingGroundList(Map<String, Object> param);

	/**
	 * 查询钓场详情
	 * 
	 * @param param
	 * @return
	 */
	Map<String, Object> getFishingGroundDetail(Map<String, Object> param);

	/**
	 * 根据钓场Id查询钓场所属的活动和垂钓券
	 * 
	 * @param param
	 * @return
	 */
	List<Map<String, Object>> getFishingTicketByFishingGroundId(Map<String, Object> param);
}
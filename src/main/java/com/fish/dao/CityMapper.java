package com.fish.dao;

import java.util.List;
import java.util.Map;

import com.fish.pojo.City;

public interface CityMapper {

	/**
	 * 根据主键Id删除数据
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
	int insertSelective(City record);

	/**
	 * 根据主键id查询数据
	 * 
	 * @param id
	 * @return
	 */
	City selectByPrimaryKey(Integer id);

	/**
	 * 修改一条数据
	 * 
	 * @param record
	 * @return
	 */
	int updateByPrimaryKeySelective(City record);

	/**
	 * 查询城市列表
	 * 
	 * @param param
	 * @return
	 */
	List<City> getCityList(Map<String, Object> param);

}
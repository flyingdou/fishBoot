package com.fish.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.fish.constants.Constants;
import com.fish.dao.PraiseMapper;
import com.fish.pojo.Praise;
import com.fish.service.PraiseService;

/*
 * 作者: dou
 * 时间: 2018-09-04 09:43:00
 * desc: 点赞业务逻辑实现类
 * */
@Service
@Transactional
public class PraiseServiceImpl implements PraiseService {

	/**
	 * 注入praiseMapper对象
	 */
	@Autowired
	private PraiseMapper praiseMapper;

	/**
	 * 点赞或取消点赞
	 */
	@Override
	public JSONObject praise(JSONObject param) {
		JSONObject result = new JSONObject();
		Praise praise = new Praise();

		// userId
		praise.setUser(param.getInteger("userId"));

		// post
		praise.setPost(param.getInteger("post"));

		// status
		praise.setStatus(param.getInteger("status"));

		// date
		praise.setAutoDate(new Date());

		// id
		if (param.containsKey("id") && StringUtils.isNotBlank(param.getString("id"))) {
			praise.setId(param.getInteger("id"));
			// 更新数据
			praiseMapper.updateByPrimaryKeySelective(praise);
		} else {
			// 新增数据
			praiseMapper.insertSelective(praise);
		}
		
		// 获取当前帖子的点赞数
		JSONObject countParam = new JSONObject();
		countParam.fluentPut("post", param.getInteger("post"))
		          .fluentPut("status", Constants.VALID_STATUS)
		          ;
		Map<String, Object> praiseCountMap = praiseMapper.praiseCount(countParam);
		result.fluentPut("id", praise.getId())
		      .fluentPut("praiseCount", praiseCountMap.get("praiseCount"))
		      ;

		return result;
	}

	
	
	/**
	 * 查询帖子点赞列表
	 */
	@Override
	public List<Map<String, Object>> praiseList(JSONObject param) {
		param.fluentPut("status", Constants.VALID_STATUS);
		List<Map<String, Object>> praiseList = praiseMapper.praiseListByPost(param);
		return praiseList;
	}
	
	

}

package com.fish.service.impl;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
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
		result.fluentPut("id", praise.getId());

		return result;
	}

}

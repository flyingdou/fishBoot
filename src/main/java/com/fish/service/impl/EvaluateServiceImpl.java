package com.fish.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.fish.dao.EvaluateMapper;
import com.fish.pojo.Evaluate;
import com.fish.service.EvaluateService;

/*
 * 作者: dou
 * 时间: 2018-09-06 13:15:07
 * desc: 帖子评论业务逻辑实现类
 * */
@Service
public class EvaluateServiceImpl implements EvaluateService {

	/**
	 * 注入evaluateMapper对象
	 */
	@Autowired
	private EvaluateMapper evaluateMapper;
	
	
	/**
	 * 评论、回复
	 */
	@Override
	public JSONObject evaluate(JSONObject param) {
		// 初始化评论对象
		Evaluate eval = new Evaluate();
		eval.setContent(param.getString("content"));
		eval.setPost(param.getInteger("post"));
		eval.setUser(param.getInteger("userId"));
		eval.setAutoDate(new Date());
		
		// 没有parent则为单独对帖子的评论，有则为对他人的评论、回复进行回复
		if (param.containsKey("parent") && StringUtils.isNotBlank(param.getString("parent"))) {
			eval.setParent(param.getInteger("parent"));
			eval.setEvaluate_parent(param.getInteger("evaluate_parent"));
		}
		
		// 持久化数据
		evaluateMapper.insertSelective(eval);
		JSONObject result = new JSONObject();
		result.fluentPut("id", eval.getId());
		return result;
	}
	
	
	/**
	 * 查询帖子评论列表
	 */
	@Override
	public List<Map<String, Object>> evaluateList(JSONObject param) {
		// 查询帖子评论
		List<Map<String, Object>> evaluatelist = evaluateMapper.evaluateListByPost(param);

		// 查询每个评论的回复
		JSONObject paramJson = new JSONObject();
		for (Map<String, Object> map : evaluatelist) {
			paramJson.fluentPut("parent", map.get("id"));
			List<Map<String, Object>> replyList = evaluateMapper.replyList(paramJson);
			map.put("replyList", replyList);
		}

		return evaluatelist;
	}

	

}

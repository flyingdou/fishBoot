package com.fish.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.fish.constants.Constants;
import com.fish.dao.FishingGroundMapper;
import com.fish.pojo.FishingGround;
import com.fish.service.FishingGroundService;

/**
 * 钓场业务接口实现类
 * 
 * @author 华文
 *
 */
@Service
@Transactional
public class FishingGroundServiceImpl implements FishingGroundService {

	@Autowired
	private FishingGroundMapper fishingGroundMapper;

	/**
	 * 发布钓场
	 */
	public JSONObject release(JSONObject param) {
		// 在本地生成一条钓场数据
		FishingGround fishingGround = JSONObject.toJavaObject(param, FishingGround.class);
		fishingGround.setWechat_audit(Constants.APPLY_STATUS_AUDITING);
		fishingGround.setAutoDate(new Date());

		// 保存到数据库
		fishingGroundMapper.insertSelective(fishingGround);

		// 返回结果
		JSONObject result = new JSONObject();
		result.fluentPut("success", true).fluentPut("fishingGround", fishingGround);
		return result;
	}

	/**
	 * 查询钓场列表
	 */
	public JSONObject getFishingGroundList(JSONObject param) {
		// 添加参数
		param.fluentPut("audit", Constants.APPLY_STATUS_PASS);
		// 查询钓场
		List<Map<String, Object>> fishingGroundList = fishingGroundMapper.getFishingGroundList(param);
		JSONObject result = new JSONObject();
		result.fluentPut("success", true).fluentPut("fishingGroundList", fishingGroundList);
		return result;
	}

	/**
	 * 查询钓场详情
	 */
	@Override
	public Map<String, Object> getFishingGroundDetail(JSONObject param) {
		// 查询钓场详情
		Map<String, Object> resultMap = fishingGroundMapper.getFishingGroundDetail(param);
		List<Map<String, Object>> fishingTickets = fishingGroundMapper.getFishingTicketByFishingGroundId(param);
		resultMap.put("fishingTickets", fishingTickets);
		return resultMap;
	}

}

package com.fish.service.impl;

import java.util.Date;
import java.util.HashMap;
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
 * @author Administrator
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
		FishingGround fishingGround = new FishingGround();
		fishingGround.setPoster(param.getString("poster"));
		fishingGround.setName(param.getString("name"));
		fishingGround.setType(param.getString("type"));
		fishingGround.setFeeType(param.getInteger("feeType"));
		fishingGround.setFee(param.getInteger("fee"));
		fishingGround.setWaterArea(param.getInteger("waterArea"));
		fishingGround.setWaterDeep(param.getInteger("waterDeep"));
		fishingGround.setAddress(param.getString("address"));
		fishingGround.setLongitude(param.getBigDecimal("longitude"));
		fishingGround.setLatitude(param.getBigDecimal("latitude"));
		fishingGround.setTelephone(param.getString("telephone"));
		fishingGround.setRemark(param.getString("remark"));
		fishingGround.setCreator(param.getInteger("memberId"));
		fishingGround.setCity(param.getString("city"));
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
		if (param.containsKey("name")) {
			if (param.containsKey("city")) {
				param.remove("city");
			}
			if (param.containsKey("type")) {
				param.remove("type");
			}
		}
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
		Map<String, Object> resultMap = new HashMap<>();
		return resultMap;
	}

}

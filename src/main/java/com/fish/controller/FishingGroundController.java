package com.fish.controller;

import java.net.URLDecoder;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fish.service.FishingGroundService;

@Controller
@RequestMapping("/fishingGround")
@RestController
public class FishingGroundController {

	@Autowired
	private FishingGroundService fishingGroundService;

	/**
	 * 发布钓场
	 * 
	 * @param json
	 * @return
	 */
	@RequestMapping("/release")
	public String release(String json) {
		try {
			JSONObject param = JSONObject.parseObject(URLDecoder.decode(json, "UTF-8"));
			JSONObject result = fishingGroundService.release(param);
			return JSON.toJSONStringWithDateFormat(result, "yyyy-MM-dd HH:mm");
		} catch (Exception e) {
			e.printStackTrace();
			return JSON.toJSONString(e);
		}
	}

	/**
	 * 查询钓场列表
	 * 
	 * @param json
	 * @return
	 */
	@RequestMapping("/getFishingGroundList")
	public String getFishingGroundList(String json) {
		try {
			JSONObject param = JSONObject.parseObject(URLDecoder.decode(json, "UTF-8"));
			JSONObject result = fishingGroundService.getFishingGroundList(param);
			return JSON.toJSONStringWithDateFormat(result, "yyyy-MM-dd HH:mm");
		} catch (Exception e) {
			e.printStackTrace();
			return JSON.toJSONString(e);
		}
	}

	/**
	 * 查询钓场详情
	 * 
	 * @return
	 */
	@RequestMapping("/getFishingGroundDetail")
	public String getFishingGroundDetail(String json) {
		try {
			JSONObject param = JSONObject.parseObject(json);
			Map<String, Object> result = fishingGroundService.getFishingGroundDetail(param);
			result.put("success", true);
			return JSON.toJSONStringWithDateFormat(result, "yyyy-MM-dd HH:mm");
		} catch (Exception e) {
			e.printStackTrace();
			return JSON.toJSONString(e);
		}
	}
}

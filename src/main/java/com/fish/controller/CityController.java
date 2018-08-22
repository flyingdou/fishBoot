package com.fish.controller;

import java.net.URLDecoder;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fish.pojo.City;
import com.fish.service.CityService;

/**
 * 
 * @author 华文
 *
 */
@Controller
@RestController
public class CityController {
	
	@Autowired
	private CityService cityService;

	/**
	 * 查询城市列表
	 * 
	 * @param json
	 * @return
	 */
	@RequestMapping("/getCityList")
	public String getCityList(String json) {
		try {
			JSONObject param = JSONObject.parseObject(URLDecoder.decode(json, "UTF-8"));
			List<City> cityList = cityService.getCityList(param);
			JSONObject result = new JSONObject();
			result.fluentPut("success", true).fluentPut("cityList", cityList);
			return JSON.toJSONStringWithDateFormat(result, "yyyy-MM-dd");
		} catch (Exception e) {
			e.printStackTrace();
			return JSON.toJSONString(e);
		}
	}
	
}

package com.fish.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.fish.service.LiveService;
import com.fish.util.ResultUtil;

/**
 * 
 * @author 华文
 * @time 2018-08-22 16:35
 *
 */
@RestController
@RequestMapping("/live")
public class LiveController {

	@Autowired
	private LiveService liveService;

	/**
	 * 查询直播列表
	 * 
	 * @return
	 */
	@RequestMapping("/getLiveList")
	public String getLiveList() {
		try {
			List<Map<String, Object>> liveList = liveService.getLiveList();
			ResultUtil result = ResultUtil.success(liveList);
			return JSON.toJSONStringWithDateFormat(result, "yyyy-MM-dd HH:mm");
		} catch (Exception e) {
			e.printStackTrace();
			return JSON.toJSONString(e);
		}
	}

}

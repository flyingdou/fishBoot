package com.fish.controller;

import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fish.service.OrderService;
import com.fish.util.ResultUtil;

/**
 * 作者: dou 时间: 2018-09-07 09:19:52 desc: 订单管理器
 */
@RestController
@RequestMapping("/order")
public class OrderController {

	/**
	 * 注入orderService对象
	 */
	@Autowired
	private OrderService orderService;

	/**
	 * 生成订单
	 * 
	 * @param json
	 * @return
	 */
	@RequestMapping("/create")
	public String create(HttpServletRequest request, String json) {
		try {
			// 处理请求参数
			JSONObject param = JSONObject.parseObject(URLDecoder.decode(json, "UTF-8"));
			ResultUtil rs = ResultUtil.success(orderService.create(request, param));
			return JSON.toJSONString(rs);
		} catch (Exception e) {
			e.printStackTrace();
			return JSON.toJSONString(e);
		}
	}

	/**
	 * 帖子打赏列表
	 * 
	 * @param json
	 * @return
	 */
	@RequestMapping("/rewardList")
	public String rewardList(String json) {
		try {
			// 处理请求参数
			JSONObject param = JSONObject.parseObject(URLDecoder.decode(json, "UTF-8"));
			ResultUtil rs = ResultUtil.success(orderService.rewardListByPost(param));
			return JSON.toJSONString(rs);
		} catch (Exception e) {
			e.printStackTrace();
			return JSON.toJSONString(e);
		}

	}

}

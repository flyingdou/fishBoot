package com.fish.controller;

import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fish.constants.Constants;
import com.fish.pojo.Live;
import com.fish.pojo.Order;
import com.fish.service.LiveService;
import com.fish.util.ResultUtil;
import com.fish.util.commentsUtil;
import com.fish.wechat.PayManager;
import com.fish.wechat.PayRequest;

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
	 * 保存直播数据(发布或修改)
	 * 
	 * @param json
	 * @return
	 */
	@RequestMapping("/saveLive")
	public String saveLive(String json) {
		try {
			JSONObject param = JSONObject.parseObject(URLDecoder.decode(json, "UTF-8"));
			Live live = liveService.saveLive(param);
			ResultUtil result = ResultUtil.success(live);
			return JSON.toJSONStringWithDateFormat(result, "yyyy-MM-dd HH:mm");
		} catch (Exception e) {
			e.printStackTrace();
			return JSON.toJSONString(e);
		}
	}

	/**
	 * 查询直播列表
	 * 
	 * @return
	 */
	@RequestMapping("/getLiveList")
	public String getLiveList(String json) {
		try {
			JSONObject param = JSONObject.parseObject(URLDecoder.decode(json, "UTF-8"));
			List<Map<String, Object>> liveList = liveService.getLiveList(param);
			ResultUtil result = ResultUtil.success(liveList);
			return JSON.toJSONStringWithDateFormat(result, "yyyy-MM-dd HH:mm");
		} catch (Exception e) {
			e.printStackTrace();
			return JSON.toJSONString(e);
		}
	}

	/**
	 * 改变直播状态
	 */
	public void changeStatus(HttpServletRequest request) {
		try {
			JSONObject param = commentsUtil.getInputStreamToJSONObject(request);
			int stream_id = param.getIntValue("stream_id");
			int event_type = param.getIntValue("event_type");
			if (event_type != 0 && event_type != 1) {
				event_type = 0;
			}
			param.fluentClear().fluentPut("liveNumber", stream_id).fluentPut("liveNumber", event_type);
			liveService.changeLiveStatus(param);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 查询视频列表
	 * 
	 * @return
	 */
	@RequestMapping("/getVideoList")
	public String getVideoList(String json) {
		try {
			JSONObject param = JSONObject.parseObject(URLDecoder.decode(json, "UTF-8"));
			List<Map<String, Object>> videoList = liveService.getVideoList(param);
			ResultUtil result = ResultUtil.success(videoList);
			return JSON.toJSONStringWithDateFormat(result, "yyyy-MM-dd HH:mm");
		} catch (Exception e) {
			e.printStackTrace();
			return JSON.toJSONString(e);
		}
	}

	/**
	 * 获得直播推流URL
	 * 
	 * @param json
	 * @return
	 */
	@RequestMapping("/liveUrl")
	public String liveUrl(String json) {
		try {
			JSONObject param = JSONObject.parseObject(URLDecoder.decode(json, "UTF-8"));
			String url = liveService.liveUrl(param);
			ResultUtil result = ResultUtil.success(url);
			return JSON.toJSONString(result);
		} catch (Exception e) {
			e.printStackTrace();
			return JSON.toJSONString(e);
		}
	}

	/**
	 * 查询直播数据
	 * 
	 * @param json
	 * @return
	 */
	@RequestMapping("/getLiveDetailById")
	public String getLiveDetailById(String json) {
		try {
			JSONObject param = JSONObject.parseObject(URLDecoder.decode(json, "UTF-8"));
			Live live = liveService.getLiveDetailById(param);
			ResultUtil result = ResultUtil.success(live);
			return JSON.toJSONStringWithDateFormat(result, "yyyy-MM-dd HH:mm");
		} catch (Exception e) {
			e.printStackTrace();
			return JSON.toJSONString(e);
		}
	}

	/**
	 * 查询直播数据
	 * 
	 * @param json
	 * @return
	 */
	@RequestMapping("/getLiveDetailByUser")
	public String getLiveDetailByUser(String json) {
		try {
			JSONObject param = JSONObject.parseObject(URLDecoder.decode(json, "UTF-8"));
			Live live = liveService.getLiveDetailByUser(param);
			ResultUtil result = ResultUtil.success(live);
			return JSON.toJSONStringWithDateFormat(result, "yyyy-MM-dd HH:mm");
		} catch (Exception e) {
			e.printStackTrace();
			return JSON.toJSONString(e);
		}
	}

	/**
	 * 获取直播播放的Url
	 * 
	 * @param json
	 * @return
	 */
	@RequestMapping("/play")
	public String play(String json) {
		try {
			JSONObject param = JSONObject.parseObject(URLDecoder.decode(json, "UTF-8"));
			Map<String, Object> map = liveService.play(param);
			ResultUtil result = map == null ? ResultUtil.fail("未付费") : ResultUtil.success(map);
			return JSON.toJSONString(result);
		} catch (Exception e) {
			e.printStackTrace();
			return JSON.toJSONString(e);
		}
	}

	/**
	 * 获取直播状态
	 * 
	 * @param json
	 * @return
	 */
	@RequestMapping("/getLiveStatus")
	public String getLiveStatus(String json) {
		try {
			JSONObject param = JSONObject.parseObject(URLDecoder.decode(json, "UTF-8"));
			Live live = liveService.getLiveDetailById(param);
			ResultUtil result = ResultUtil.success(live.getLiveState());
			return JSON.toJSONString(result);
		} catch (Exception e) {
			e.printStackTrace();
			return JSON.toJSONString(e);
		}
	}

	/**
	 * 生成直播订单
	 * 
	 * @param json
	 * @return
	 */
	@RequestMapping("/createLiveOrder")
	public String createLiveOrder(String json, HttpServletRequest request) {
		try {
			JSONObject param = JSONObject.parseObject(URLDecoder.decode(json, "UTF-8"));
			Order order = liveService.createLiveOrder(param);
			PayRequest payRequest = new PayRequest(Constants.APPID, Constants.MCH_ID, Constants.KEY,
					param.getString("openId"));
			PayManager payManager = new PayManager(request);
			return payManager.paySign(payRequest, order);
		} catch (Exception e) {
			e.printStackTrace();
			return JSON.toJSONString(e);
		}
	}

	/**
	 * 查询直播开关
	 * 
	 * @return
	 */
	@RequestMapping("/getLiveSwitch")
	public String getLiveSwitch() {
		try {
			int liveSwitch = liveService.getLiveSwitch();
			ResultUtil result = ResultUtil.success(liveSwitch);
			return JSON.toJSONString(result);
		} catch (Exception e) {
			return JSON.toJSONString(e);
		}
	}
}

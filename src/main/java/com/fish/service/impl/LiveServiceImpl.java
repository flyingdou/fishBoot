package com.fish.service.impl;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fish.constants.Constants;
import com.fish.dao.LiveMapper;
import com.fish.dao.UserMapper;
import com.fish.pojo.Live;
import com.fish.pojo.User;
import com.fish.service.LiveService;
import com.fish.util.HttpRequestUtils;

/**
 * 
 * @author 华文
 * @time 2018-08-22 16:19
 *
 */
@Service
@Transactional
public class LiveServiceImpl implements LiveService {

	/**
	 * 腾讯云直播服务的bizid
	 */
	private static final String BIZID = "10803";

	/**
	 * 腾讯云直播服务的APPID
	 */
	private static final String APPID = "1252000172";

	/**
	 * 防盗链key
	 */
	private static final String KEY = "5e3daa3446d2ca2be1d2479a50edc749";

	/**
	 * 调用直播相关API的key
	 */
	private static final String API_KEY = "e0a4e017c4fa74c955a27a4ec6f4224a";

	@Autowired
	private LiveMapper liveMapper;

	@Autowired
	private UserMapper userMapper;

	/**
	 * 保存直播数据(发布或修改)
	 */
	@Override
	public Live saveLive(JSONObject param) {
		Live live = liveMapper.getLiveByUser(param.getInteger("userId"));
		if (live == null) {
			live = JSONObject.toJavaObject(param, Live.class);
			live.setLiveState(Integer.valueOf(Constants.LIVE_STATUS_CLOSE));
			live.setTribeId(UUID.randomUUID().toString().substring(0, 10));
			liveMapper.insertSelective(live);
		} else {
			int liveId = live.getId();
			live = JSONObject.toJavaObject(param, Live.class);
			live.setId(liveId);
			live.setLiveState(Integer.valueOf(Constants.LIVE_STATUS_CLOSE));
			live.setTribeId(UUID.randomUUID().toString().substring(0, 10));
			liveMapper.updateByPrimaryKeySelective(live);
		}
		return live;
	}

	/**
	 * 查询直播间列表
	 */
	@Override
	public List<Map<String, Object>> getLiveList() {
		List<Map<String, Object>> liveList = liveMapper.getLiveList();
		for (Map<String, Object> map : liveList) {
			// 获取当前直播间的观看人数
			Object total_online = null;
			if (Constants.LIVE_STATUS_ON.equals(String.valueOf(map.get("liveState")))) {
				total_online = getUserCount(String.valueOf(map.get("liveNumber")));
			} else {
				total_online = 0;
			}
			map.put("total_online", total_online);
		}
		return liveList;
	}

	/**
	 * 获得直播推流URL
	 */
	@Override
	public String liveUrl(JSONObject param) throws Exception {
		User user = userMapper.selectByPrimaryKey(param.getInteger("userId"));
		// 过期时间戳(当前时间加上24个小时)
		long time = (System.currentTimeMillis() + (24 * 60 * 60 * 1000)) / 1000;

		// 根据当前登录用户查询对应直播数据
		Live live = liveMapper.getLiveByUser(param.getInteger("userId"));

		// 每次推流不改变直播码, 只更新时间
		if (StringUtils.isEmpty(live.getLiveName())) {
			// 生成随机码
			StringBuilder str = new StringBuilder();
			str.append(user.getId()).append(BIZID).append(Math.round(Math.random() * 10000));
			String randomCode = Long.toHexString(Long.valueOf(str.toString()));

			// 生成直播码
			String liveNumber = new StringBuilder().append(BIZID).append("_").append(randomCode).toString();

			live.setLiveNumber(liveNumber);
		}
		
		// 更新历史直播时间和过期时间并持久化
		Timestamp history = new Timestamp(System.currentTimeMillis());
		live.setTimeOut(String.valueOf(time));
		live.setLiveHistoryTime(history);
		liveMapper.updateByPrimaryKeySelective(live);

		// 生成直播推流url(签名生成部分url)
		String input = new StringBuilder().append(KEY).append(live.getLiveNumber())
				.append(Long.toHexString(time).toUpperCase()).toString();
		MessageDigest messageDigest = MessageDigest.getInstance("MD5");
		String sign = byteArrayToHexString(messageDigest.digest(input.getBytes("UTF-8")));
		String signURL = new StringBuilder().append("txSecret=").append(sign).append("&").append("txTime=")
				.append(Long.toHexString(time).toUpperCase()).toString();

		// 生成直播推流url(最后拼接完整url)
		String liveUrl = new StringBuilder("rtmp://").append(BIZID).append(".livepush.myqcloud.com/live/")
				.append(live.getLiveName()).append("?bizid=").append(BIZID).append("&").append(signURL).toString();

		return liveUrl;
	}

	/**
	 * 查询直播数据
	 */
	@Override
	public Map<String, Object> getLiveDetailById(JSONObject param) {
		return liveMapper.getLiveDetailById(param);
	}

	/**
	 * 获取直播播放Url
	 */
	@Override
	public Map<String, Object> play(JSONObject param) {
		Map<String, Object> resultMap = new HashMap<>();
		Live live = liveMapper.getLiveByUser(param.getInteger("anchor"));
		// 检查用户是否有观看直播的权限
		if (!liveMapper.checkUserPlayLivePower(param)) {
			return null;
		}
		// 拼接播放url
		resultMap.put("RTMP", new StringBuilder("rtmp://").append(BIZID).append(".liveplay.myqcloud.com/live/")
				.append(live.getLiveNumber()).toString());
		return resultMap;
	}

	/**
	 * 改变直播状态
	 */
	@Override
	public void changeLiveStatus(JSONObject param) {
		// 更新直播历史时间和直播状态
		Live live = liveMapper.getLiveByLiveNumber(param);
		Timestamp history = new Timestamp(System.currentTimeMillis());
		live.setLiveHistoryTime(history);
		live.setLiveState(param.getInteger("liveStatus"));
		liveMapper.updateByPrimaryKeySelective(live);
	}

	/**
	 * 获取直播观看人数
	 */
	private String getUserCount(String streamId) {
		// 过期时间戳(当前时间加上24个小时)
		long time = System.currentTimeMillis() / 1000 + 60;
		StringBuilder url = new StringBuilder("http://statcgi.video.qcloud.com/common_access");
		url.append("?cmd=").append(APPID);
		url.append("&interface=Get_LivePlayStat");
		url.append("&Param.s.stream_id=").append(streamId);
		url.append("&sign=").append(getSign(API_KEY, time));
		url.append("&t=").append(time);
		String result = HttpRequestUtils.httpReques(url.toString(), null);
		JSONObject res = JSONObject.parseObject(result);
		try {
			if (!"null".equals(String.valueOf(res.get("output")))) {
				JSONObject outputJson = JSONObject.parseObject(res.getString("out_put"));
				JSONArray streamInfo = JSONArray.parseArray(outputJson.getString("stream_info"));
				result = streamInfo.getJSONObject(0).getString("online");
			} else {
				// 未直播直接返回人数0
				result = "0";
			}
		} catch (Exception e) {
			result = "0";
		}
		return result;
	}

	/**
	 * 获得防盗链签名sign
	 */
	private static String getSign(String key, long txTime) {
		String input = new StringBuilder().append(key).append(txTime).toString();
		String txSecret = null;
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			txSecret = byteArrayToHexString(messageDigest.digest(input.getBytes("UTF-8")));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return txSecret;
	}

	/**
	 * 加密
	 * 
	 * @return
	 */
	private static String byteArrayToHexString(byte[] data) {
		char[] DIGITS_LOWER = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
		char[] out = new char[data.length << 1];

		for (int i = 0, j = 0; i < data.length; i++) {
			out[j++] = DIGITS_LOWER[(0xF0 & data[i]) >>> 4];
			out[j++] = DIGITS_LOWER[0x0F & data[i]];
		}
		return new String(out);
	}

	/**
	 * 查询视频列表
	 */
	@Override
	public List<Map<String, Object>> getVideoList() {

		return liveMapper.getVideoList();
	}
}

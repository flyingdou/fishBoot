package com.fish.service.impl;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fish.constants.Constants;
import com.fish.dao.LiveMapper;
import com.fish.pojo.Live;
import com.fish.service.LiveService;
import com.fish.util.HttpRequestUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

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
		JSONObject res = JSONObject.fromObject(result);
		try {
			if (!"null".equals(String.valueOf(res.get("output")))) {
				JSONObject outputJson = JSONObject.fromObject(res.get("output"));
				JSONArray streamInfo = JSONArray.fromObject(outputJson.get("stream_info"));
				result = streamInfo.getJSONObject(0).get("online").toString();
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

}

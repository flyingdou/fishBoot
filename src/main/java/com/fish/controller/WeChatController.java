package com.fish.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.fish.util.AES;

import net.sf.json.JSONObject;

/**
 * 
 * @author 华文
 *
 */
@RestController
@RequestMapping("/wechat")
public class WeChatController {

	/**
	 * 解密手机号
	 */
	@RequestMapping("/decodePhoneNumber")
	public String decodePhoneNumber(String json) {
		try {
			JSONObject param = JSONObject.fromObject(json);
			JSONObject detail = param.getJSONObject("detail");
			String encryptedData = detail.getString("encryptedData");
			String session_key = param.getString("session_key");
			String iv = detail.getString("iv");
			String result = AES.wxDecrypt(encryptedData, session_key, iv);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return JSON.toJSONString(e);
		}
	}

}

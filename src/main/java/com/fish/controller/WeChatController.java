package com.fish.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fish.constants.Constants;
import com.fish.util.HttpRequestUtils;
import com.fish.util.commentsUtil;

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
			JSONObject param = JSONObject.parseObject(json);
			JSONObject detail = param.getJSONObject("detail");
			String encryptedData = detail.getString("encryptedData");
			String session_key = param.getString("session_key");
			String iv = detail.getString("iv");
			String result = commentsUtil.wxDecrypt(encryptedData, session_key, iv);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return JSON.toJSONString(e);
		}
	}

	/**
	 * 接收消息推送
	 */
	@RequestMapping("/receive")
	public String receive(HttpServletRequest request, HttpServletResponse response) {
		try {
			// 响应微信服务器
			if (request.getParameter("echostr") != null) {
				return request.getParameter("echostr");
			}
			// 获取微信传来的参数
			JSONObject param = commentsUtil.getInputStreamToJSONObject(request);
			// 判断消息类型
			if (!param.containsKey("MsgType") && StringUtils.isEmpty(param.getString("MsgType"))) {
				return null;
			}
			JSONObject result = new JSONObject();
			// 第一步:请求AccessToken
			String getAccessTokenURL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="
					+ Constants.APPID + "&secret=" + Constants.APP_SECRET;
			JSONObject accessTokenResult = HttpRequestUtils.httpGet(getAccessTokenURL);
			String accessToken = accessTokenResult.getString("access_token");
			// 第二步:创建消息数据包
			JSONObject sendParam = new JSONObject();
			JSONObject text = new JSONObject();
			sendParam.fluentPut("touser", param.get("FromUserName"));
			sendParam.fluentPut("msgtype", "text");
			// 用户进入俱乐部小程序页面(发送欢迎消息)
			if (Constants.MSG_TYPE_EVENT.equals(param.getString("MsgType"))) {
				text.fluentPut("content", Constants.FISH_MINI_PROGRAM_WELLCOME_MESSAGE);
				result.fluentPut("success", true);
			} else {
				// 用户发送消息(回应普通消息模板)
				text.fluentPut("content", Constants.FISH_MINI_PROGRAM_PUBLIC_MESSAGE);
				result.fluentPut("ToUserName", param.get("FromUserName"))
						.fluentPut("FromUserName", param.get("ToUserName"))
						.fluentPut("CreateTime", param.get("CreateTime"))
						.fluentPut("MsgType", "transfer_customer_service");
			}
			sendParam.fluentPut("text", text);
			// 第三步:请求微信小程序服务器发送消息接口(发送消息)
			String sendMessageURL = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=" + accessToken;
			HttpRequestUtils.httpPost(sendMessageURL, sendParam);
			return JSON.toJSONString(result);
		} catch (Exception e) {
			e.printStackTrace();
			return JSON.toJSONString(e);
		}
	}

}

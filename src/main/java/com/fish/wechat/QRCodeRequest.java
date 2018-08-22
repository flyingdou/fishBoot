package com.fish.wechat;


import com.alibaba.fastjson.JSONObject;

/*
 * 作者: dou
 * 时间: 2018-08-22 11:36:44
 * desc: 获取微信小程序码请求类(处理请求参数)
 * */
public class QRCodeRequest {

	/**
	 * 请求的微信服务地址
	 */
	private String reqUrl = "https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token=ACCESS_TOKEN";

	/**
	 * 用户识别二维码后，跳转的小程序页面路径
	 */
	private String path;

	/**
	 * 传递的参数(用户id)
	 */
	private String id;

	/**
	 * 有参构造
	 * 
	 * @param path
	 * @param userId
	 */
	public QRCodeRequest(String path, String id) {
		super();
		this.path = path;
		this.id = id;
	}

	/**
	 * setter && getter
	 * 
	 * @return
	 */
	public String getReqUrl() {
		return reqUrl;
	}

	public void setReqUrl(String reqUrl) {
		this.reqUrl = reqUrl;
	}

	/**
	 * 设置所需参数
	 * 
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public JSONObject getParam() {
		JSONObject param = new JSONObject();
		param.fluentPut("path", path).fluentPut("scene", id);
		return param;
	}

}

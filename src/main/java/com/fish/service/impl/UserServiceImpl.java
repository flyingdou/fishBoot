package com.fish.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fish.constants.Constants;
import com.fish.dao.UserMapper;
import com.fish.pojo.User;
import com.fish.service.UserService;
import com.fish.util.commentsUtil;
import com.fish.wechat.GetUserInfoResponse;
import com.fish.wechat.QRCodeRequest;
import com.fish.wechat.WeChatAPI;

/**
 * 
 * @author dou user Service逻辑层
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMaper;

	/**
	 * 微信用户登录
	 * 
	 * @throws Exception
	 */
	public JSONObject wechatLogin(JSONObject param) throws Exception {
		JSONObject ret = new JSONObject();
		// 初始化user
		User user = new User();

		// 从微信服务器获取unionId, session_key, iv
		GetUserInfoResponse getUserInfoRespone = new GetUserInfoResponse();
		getUserInfoRespone = (GetUserInfoResponse) getUserInfoRespone.getUserInfo(param.getString("code"));

		// 解密信息
		String decodeStr = "";
		decodeStr = commentsUtil.wxDecrypt(param.getString("encryptedData"), getUserInfoRespone.getSession_key(),
				param.getString("iv"));

		String unionId = commentsUtil.getTargetFromAES(decodeStr, "unionId");

		// 获取前台传递过来的数据
		JSONObject userInfo = JSONObject.parseObject(param.getString("userInfo"));

		// 根据uniodId查询用户
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("wechat_id", unionId);
		user = userMaper.getUserByWechatid(queryMap);

		if (user == null || user.getId() == null) {
			user = new User();
			// 新用户，注册操作
			String fileName = commentsUtil.getRandomName(4) + ".jpg";
			commentsUtil.download(userInfo.getString("avatarUrl"), fileName, Constants.PICTURE_UPLOAD_PATH);
			user.setNick(userInfo.getString("nickName"));
			user.setImage(fileName);
			user.setSex("1".equals(userInfo.getString("gender")) ? Constants.USER_SEX_MALE : Constants.USER_SEX_FEMALE);
			user.setRegisterDate(new Date());
			user.setLoginDate(new Date());
			user.setWechatId(unionId);
			user.setScore(0);
			user.setType("0");
			if (StringUtils.isNotEmpty(userInfo.getString("province"))) {
				user.setProvince(userInfo.getString("province"));
			}
			if (StringUtils.isNotEmpty(userInfo.getString("city"))) {
				user.setCity(userInfo.getString("city"));
			}

			// 持久化数据
			userMaper.insertSelective(user);

		} else {
			// 老用户，更新登录时间，更新用户头像
			commentsUtil.download(userInfo.getString("avatarUrl"), user.getImage(), Constants.PICTURE_UPLOAD_PATH);
			user.setLoginDate(new Date());

			// 持久化数据
			userMaper.updateByPrimaryKeySelective(user);

		}

		ret.fluentPut("success", true).fluentPut("message", "OK")
				.fluentPut("session_key", getUserInfoRespone.getSession_key())
				.fluentPut("openId", getUserInfoRespone.getOpenid()).fluentPut("key", user.getId());
		;

		return ret;
	}

	@Override
	public JSONObject getUserFishInfo(JSONObject param) {
		param.fluentPut("parent_code", Constants.USER_LEVEL_CODE);
		Map<String, Object> userInfoMap = userMaper.getUserFishInfo(param);
		return JSON.parseObject(JSON.toJSONString(userInfoMap));
	}

	@Override
	public String createQRCodeB(JSONObject param) {
		String path = Constants.QRCODE_SHARE_PATH;
		String id = param.getString("userId");
		String accessToken = WeChatAPI.getAccessToken(Constants.APPID, Constants.APP_SECRET).getString("access_token");
		QRCodeRequest qrCodeRequest = new QRCodeRequest(path, id);
		String result = Constants.BASE64_IMAGE_PREFIX + WeChatAPI.createQRCodeB(accessToken, qrCodeRequest);
		return result;
	}

	@Override
	public List<Map<String, Object>> getRanking(JSONObject param) {
		param.fluentPut("parent_code", Constants.USER_LEVEL_CODE);
		return userMaper.getRanking(param);
	}
}

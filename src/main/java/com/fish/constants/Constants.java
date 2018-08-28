package com.fish.constants;

/**
 * fish常量类
 * 
 * @author dou
 *
 */
public class Constants {

	/**
	 * fish APPID APP_SECRET
	 */
	public static String APPID = "wx2e8d35be5f888eb3";

	public static String APP_SECRET = "c46624d7b33293cd30a7e008986e4cd2";
	
	public static String MCH_ID = "";
	
	public static String KEY = "";

	/**
	 * event APPID APP_SECRET
	 */
	public static String APPID_EVENT = "wxbc83d7dbb3a30e14";

	public static String APP_SECRET_EVENT = "049c7653e2b44a72bbbee5c8e065b8be";

	/**
	 * 测试号
	 */
	public static String APPID_TEST = "wx3fe7a3210eacd362";

	public static String APP_SECRET_TEST = "743401935ef868a4bf3659133be6a82f";

	/**
	 * 获取用户信息URL
	 */
	public static String GET_USERINFO_URL = "https://api.weixin.qq.com/sns/jscode2session?";

	/**
	 * 开启
	 */
	public static final Integer OPEN_STATUS = 1;

	/**
	 * 关闭
	 */
	public static final Integer CLOSE_SATUS = 0;

	/**
	 * 审核状态(审核中)
	 */
	public static final Integer APPLY_STATUS_AUDITING = 0;

	/**
	 * 审核状态(通过)
	 */
	public static final Integer APPLY_STATUS_PASS = 1;

	/**
	 * 审核状态(未通过)
	 */
	public static final Integer APPLY_STATUS_UNPASS = 2;

	/**
	 * 推荐
	 */
	public static final Integer RECOMMEND_STATUS = 1;

	/**
	 * 未推荐
	 */
	public static final Integer UN_RECOMMEND_STATUS = 0;

	/**
	 * 商品类型(拜师)
	 */
	public static final String PRODUCT_TYPE_LEARNING = "A";

	/**
	 * 商品类型(参加活动)
	 */
	public static final String PRODUCT_TYPE_ACTIVE = "B";

	/**
	 * 商品类型(打赏)
	 */
	public static final String PRODUCT_TYPE_AWARD = "C";

	/**
	 * 商品类型(结算)
	 */
	public static final String PRODUCT_TYPE_BALANCE = "D";

	/**
	 * 商品类型(提现)
	 */
	public static final String PRODUCT_TYPE_CASH = "E";

	/**
	 * 商品类型(卡券)
	 */
	public static final String PRODUCT_TYPE_TICKET = "F";
	
	/**
	 * 商品类型(直播)
	 */
	public static final String PRODUCT_TYPE_LIVE = "L";

	/**
	 * 订单状态(未付款)
	 */
	public static final Integer ORDER_STATUS_BEPAY = 0;

	/**
	 * 订单状态(已付款)
	 */
	public static final Integer ORDER_STATUS_PAYED = 1;

	/**
	 * 订单状态(已结算)
	 */
	public static final Integer ORDER_STATUS_BALANCED = 2;

	/**
	 * 订单状态(结算金额分配)
	 */
	public static final Integer ORDER_STATUS_BALANCE_AMOUNT_ALLOCATION = 3;

	/**
	 * 订单状态(提现支出)
	 */
	public static final Integer ORDER_STATUS_CASH = 4;

	/**
	 * 用户图片上传地址
	 */
	public static final String PICTURE_UPLOAD_PATH = "D://java/fish/picture";

	/**
	 * 用户性别 男
	 */
	public static final String USER_SEX_MALE = "M";

	/**
	 * 用户性别 女
	 */
	public static final String USER_SEX_FEMALE = "F";
	
	/**
	 * 小程序二维码path路径
	 */
	public static final String QRCODE_SHARE_PATH = "pages/mine/mine";
	
	
	/**
	 * base64图片前缀
	 */
	public static final String BASE64_IMAGE_PREFIX = "data:image/jpg;base64,";
	
	/**
	 * 直播状态(未直播)
	 */
	public static final String LIVE_STATUS_NOT = "0";
	
	/**
	 * 直播状态(直播中)
	 */
	public static final String LIVE_STATUS_ON = "1";
	
	/**
	 * 直播状态(已关闭: 后台管理系统关闭)
	 */
	public static final String LIVE_STATUS_CLOSE = "2";
	
	/**
	 * 小程序客服消息类型(用户在小程序“客服会话按钮”进入客服会话)
	 */
	public static final String MSG_TYPE_EVENT = "event";

	/**
	 * 小程序欢迎消息模板
	 */
	public static final String FISH_MINI_PROGRAM_WELLCOME_MESSAGE = "欢迎来到钓鱼日记";

	/**
	 * 小程序普通消息模板
	 */
	public static final String FISH_MINI_PROGRAM_PUBLIC_MESSAGE = "您好，钓鱼日记小程序【客服001号】正在为您服务";
}

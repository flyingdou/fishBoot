package com.fish.service.impl;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.fish.constants.Constants;
import com.fish.dao.OrderMapper;
import com.fish.pojo.Order;
import com.fish.service.OrderService;
import com.fish.util.commentsUtil;
import com.fish.wechat.PayRequest;
import com.fish.wechat.WeChatAPI;

/**
 * 作者: dou
 * 时间: 2018-09-07 09:28:50
 * desc: 订单业务逻辑实现类
 */
@Service
@Transactional
public class OrderServiceImpl implements OrderService {

	/**
	 * 注入orderMapper对象
	 */
	private OrderMapper orderMapper;
	
	
	
	
	/**
	 * 生成订单
	 * @throws Exception 
	 */
	@Override
	public JSONObject create(HttpServletRequest request,JSONObject param) throws Exception {
		// 初始化订单对象
		Order order = new Order();
		
		// 付款人
		order.setFromUser(param.getInteger("from_user"));
		
		// 收款人
		order.setToUser(param.getInteger("to_user"));
		
		// 订单类型
		order.setProductType(param.getString("product_type"));
		
		// 商品id
		order.setProductId(param.getInteger("product_id"));
		
		// 订单状态(生成订单时为未付款状态)
		order.setStatus(Constants.INVALID_STATUS);
		
		// 订单生成时间
		order.setAutoDate(new Date());
		
		// 订单金额
		order.setMoney(param.getDouble("money"));
		
		// 订单编号
		order.setNo(commentsUtil.getRandomNo("yyyyMMddHHmmss", 6));
		
		// 持久化数据
		orderMapper.insertSelective(order);
		
		// 微信支付签名、统一下单
		PayRequest payRequest = new PayRequest(Constants.APPID, Constants.MCH_ID, Constants.KEY, param.getString("open_id"), request);
		String paySignStr = WeChatAPI.paySign(payRequest, order);
		JSONObject result = new JSONObject();
		result.fluentPut("paySignStr", paySignStr);
		
		// 暂未考虑0元购买的情况
		return result;
	}




	
	
	
	
	
	
	
	
	
	/**
	 * 微信支付回调修改订单状态
	 */
	@Override
	public void updateOrderStatus(String orderno) {
		
		// 将order查询出来
		Order order = orderMapper.getOrderByNo(orderno);
		order.setPayTime(new Date());
		order.setStatus(Constants.ORDER_STATUS_PAYED);
		
		String order_type = order.getProductType();
		
		// 根据订单类型做特定的操作
		if (Constants.PRODUCT_TYPE_AWARD.equals(order_type)) {
			// nothing to do
			// 此处由于是打赏订单，只需修改订单状态即可，所以不做操作
		}
		
		// 持久化数据
		orderMapper.updateByPrimaryKeySelective(order);
		
	}

}

package com.fish.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.fish.constants.Constants;
import com.fish.dao.EvaluateMapper;
import com.fish.dao.OrderMapper;
import com.fish.dao.PostImageMapper;
import com.fish.dao.PostMapper;
import com.fish.dao.PraiseMapper;
import com.fish.pojo.Post;
import com.fish.pojo.PostImage;
import com.fish.service.PostService;
import com.fish.util.commentsUtil;

/*
 * 作者: dou
 * 时间: 2018-08-30 15:03:19
 * desc: 帖子逻辑实现类
 * */
@Service
@Transactional
public class PostServiceImpl implements PostService {

	/**
	 * 注入postMapper对象
	 */
	@Autowired
	private PostMapper postMapper;
	
	

	/**
	 * 注入postImageMapper对象
	 */
	@Autowired
	private PostImageMapper postImageMapper;
	
	

	/**
	 * 注入praiseMapper对象
	 */
	@Autowired
	private PraiseMapper praiseMapper; 
	
	
	/**
	 * 注入evaluateMapper对象
	 */
	@Autowired
	private EvaluateMapper evaluateMapper; 
	
	
	/**
	 * 注入orderMapper对象
	 */
	@Autowired
	private OrderMapper orderMapper;
	
	
	
	
	/**
	 * 发布帖子
	 */
	@Override
	public JSONObject releasePost(JSONObject param) {

		JSONObject result = new JSONObject();

		// 初始化post对象
		Post post = new Post();

		// 发帖内容
		if (param.containsKey("content")) {
			post.setContent(param.getString("content"));
		}

		// 发帖人
		post.setUser(param.getInteger("userId"));

		// 视频url
		if (param.containsKey("video_url")) {
			post.setVideoUrl(param.getString("video_url"));
		}

		// 钓法
		if (param.containsKey("fishMethod")) {
			post.setFishingMethod(param.getString("fishMethod"));
		}

		// 鱼的种类
		if (param.containsKey("fishGrain")) {
			post.setFishingGrain(param.getString("fishGrain"));
		}

		// 经纬度
		if (param.containsKey("longitude") && param.containsKey("latitude")) {
			post.setLongitude(param.getDouble("longitude"));
			post.setLatitude(param.getDouble("latitude"));
		}

		// 发帖时间
		post.setPosterDate(new Date());

		// 持久化数据
		postMapper.insertSelective(post);

		// 帖子图片处理
		if (param.containsKey("pictures")) {
			String pictures = param.getString("pictures");
			String[] pics = pictures.split(",");
			for (String string : pics) {
				PostImage pi = new PostImage();
				pi.setPost(post.getId());
				pi.setImage(string);
				pi.setAutoDate(new Date());

				// 持久化数据
				postImageMapper.insertSelective(pi);

			}

		}

		result.fluentPut("key", post.getId());

		return result;
	}

	/**
	 * 查询帖子列表
	 */
	@Override
	public List<Map<String, Object>> postList(JSONObject param) {
		param.fluentPut("status", Constants.VALID_STATUS);
		param.fluentPut("parent_code", Constants.USER_LEVEL_CODE);
		param.fluentPut("reward_type", Constants.ORDER_TYPE_REWARD);
		if (param.containsKey("chooseMethodStr")) {
			String chooseMethods = param.getString("chooseMethodStr");
			List<Integer> chooseList = commentsUtil.getIntegers(chooseMethods);
			param.fluentPut("chooseMethodStr", chooseList);
		}
		
		if (param.containsKey("chooseClassStr")) {
			String chooseClass = param.getString("chooseClassStr");
			List<Integer> chooseList = commentsUtil.getIntegers(chooseClass);
			param.fluentPut("chooseClassStr", chooseList);
		}
		return postMapper.postList(param);
	}

	/**
	 * 帖子详情
	 */
	@Override
	public JSONObject postDetail(JSONObject param) {
		param.fluentPut("parent_code", Constants.USER_LEVEL_CODE)
		     .fluentPut("status", Constants.VALID_STATUS)
		     .fluentPut("product_type", Constants.ORDER_TYPE_REWARD)
		     ;
		// 查询帖子自身详情
		Map<String, Object> postDetail = postMapper.postDetail(param);
		
		// 查询帖子有效点赞列表
		List<Map<String, Object>> praiseList = praiseMapper.praiseListByPost(param);
		
		// 查询帖子评论列表
		List<Map<String, Object>> evaluateList = evaluateMapper.evaluateListByPost(param);
		
		// 查询帖子打赏列表
		List<Map<String, Object>> rewardList = orderMapper.rewardListByPost(param);
		
		
		
		JSONObject result = new JSONObject();
		result.fluentPut("postDetail", postDetail)
		      .fluentPut("praiseList", praiseList)
		      .fluentPut("evaluateList", evaluateList)
		      .fluentPut("rewardList", rewardList)
		      ;
		return result;
	}

}

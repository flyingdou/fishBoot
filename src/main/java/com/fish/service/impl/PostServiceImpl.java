package com.fish.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.fish.dao.PostImageMapper;
import com.fish.dao.PostMapper;
import com.fish.pojo.Post;
import com.fish.pojo.PostImage;
import com.fish.service.PostService;

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

}

package com.fish.pojo;

import java.sql.Timestamp;

/**
 * @author hw
 * @version 创建时间：2018年3月12日 上午9:53:16
 * @ClassName 类名称
 * @Description 类描述
 */
public class Live {

	public Integer id;

	// 直播间所属用户id,外键
	private Integer member;

	// 直播名称
	private String liveName;

	// 直播费用
	private Double liveCost;

	// 直播封面
	private String liveImage;

	// 直播公告
	private String liveNotice;

	// 直播状态(0.未直播,1.直播中)
	private Integer liveState;

	// 最后一次直播时间
	private Timestamp liveHistoryTime;

	// 直播的房间号
	private String liveNumber;

	// 直播过期时间
	private String timeOut;

	// 直播开始时间
	private String startTime;

	// 群组id
	private String tribeId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getMember() {
		return member;
	}

	public void setMember(Integer member) {
		this.member = member;
	}

	public String getLiveName() {
		return liveName;
	}

	public void setLiveName(String liveName) {
		this.liveName = liveName;
	}

	public Double getLiveCost() {
		return liveCost;
	}

	public void setLiveCost(Double liveCost) {
		this.liveCost = liveCost;
	}

	public String getLiveImage() {
		return liveImage;
	}

	public void setLiveImage(String liveImage) {
		this.liveImage = liveImage;
	}

	public String getLiveNotice() {
		return liveNotice;
	}

	public void setLiveNotice(String liveNotice) {
		this.liveNotice = liveNotice;
	}

	public Integer getLiveState() {
		return liveState;
	}

	public void setLiveState(Integer liveState) {
		this.liveState = liveState;
	}

	public Timestamp getLiveHistoryTime() {
		return liveHistoryTime;
	}

	public void setLiveHistoryTime(Timestamp liveHistoryTime) {
		this.liveHistoryTime = liveHistoryTime;
	}

	public String getLiveNumber() {
		return liveNumber;
	}

	public void setLiveNumber(String liveNumber) {
		this.liveNumber = liveNumber;
	}

	public String getTimeOut() {
		return timeOut;
	}

	public void setTimeOut(String timeOut) {
		this.timeOut = timeOut;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getTribeId() {
		return tribeId;
	}

	public void setTribeId(String tribeId) {
		this.tribeId = tribeId;
	}

}

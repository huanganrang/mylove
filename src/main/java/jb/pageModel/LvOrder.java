package jb.pageModel;

import java.util.Date;

import jb.listener.Application;

public class LvOrder implements java.io.Serializable {

	private static final long serialVersionUID = 5454155825314635342L;

	private java.lang.String id;	
	private java.lang.Long orderNo;	
	private java.lang.Integer openId;	
	private java.lang.String vipLevel;	
	private java.lang.String orderStatus;	
	private java.lang.String channel;	
	private java.lang.Float amount;	
	private Date paytime;			
	private Date createtime;			
	private String appId;
	
	private Date paytimeBegin;
	private Date paytimeEnd;
	
	public String getVipLevelZh() {
		return Application.getString(this.vipLevel);
	}
	
	public String getOrderStatusZh() {
		return Application.getString(this.orderStatus);
	}
	
	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public void setId(java.lang.String value) {
		this.id = value;
	}
	
	public java.lang.String getId() {
		return this.id;
	}

	
	public void setOrderNo(java.lang.Long orderNo) {
		this.orderNo = orderNo;
	}
	
	public java.lang.Long getOrderNo() {
		return this.orderNo;
	}
	public void setOpenId(java.lang.Integer openId) {
		this.openId = openId;
	}
	
	public java.lang.Integer getOpenId() {
		return this.openId;
	}
	public void setVipLevel(java.lang.String vipLevel) {
		this.vipLevel = vipLevel;
	}
	
	public java.lang.String getVipLevel() {
		return this.vipLevel;
	}
	public void setOrderStatus(java.lang.String orderStatus) {
		this.orderStatus = orderStatus;
	}
	
	public java.lang.String getOrderStatus() {
		return this.orderStatus;
	}
	public void setChannel(java.lang.String channel) {
		this.channel = channel;
	}
	
	public java.lang.String getChannel() {
		return this.channel;
	}
	public void setAmount(java.lang.Float amount) {
		this.amount = amount;
	}
	
	public java.lang.Float getAmount() {
		return this.amount;
	}
	public void setPaytime(Date paytime) {
		this.paytime = paytime;
	}
	
	public Date getPaytime() {
		return this.paytime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	
	public Date getCreatetime() {
		return this.createtime;
	}

	public Date getPaytimeBegin() {
		return paytimeBegin;
	}

	public void setPaytimeBegin(Date paytimeBegin) {
		this.paytimeBegin = paytimeBegin;
	}

	public Date getPaytimeEnd() {
		return paytimeEnd;
	}

	public void setPaytimeEnd(Date paytimeEnd) {
		this.paytimeEnd = paytimeEnd;
	}
}

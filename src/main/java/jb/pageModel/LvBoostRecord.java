package jb.pageModel;

import java.util.Date;

public class LvBoostRecord implements java.io.Serializable {

	private static final long serialVersionUID = 5454155825314635342L;

	private java.lang.String id;	
	private java.lang.Integer openId;	
	private java.lang.String activtyId;	
	private java.lang.Integer assistNum;	
	private java.lang.Integer visitNum;	
	private Date boostTime;			
	private Date openTime;			
	private java.lang.Integer openStatus;	

	private String nickName; 
	private String goodsImg;
	private String goodsName;

	public void setId(java.lang.String value) {
		this.id = value;
	}
	
	public java.lang.String getId() {
		return this.id;
	}

	
	public void setOpenId(java.lang.Integer openId) {
		this.openId = openId;
	}
	
	public java.lang.Integer getOpenId() {
		return this.openId;
	}
	public void setActivtyId(java.lang.String activtyId) {
		this.activtyId = activtyId;
	}
	
	public java.lang.String getActivtyId() {
		return this.activtyId;
	}
	public void setAssistNum(java.lang.Integer assistNum) {
		this.assistNum = assistNum;
	}
	
	public java.lang.Integer getAssistNum() {
		return this.assistNum;
	}
	public void setVisitNum(java.lang.Integer visitNum) {
		this.visitNum = visitNum;
	}
	
	public java.lang.Integer getVisitNum() {
		return this.visitNum;
	}
	public void setBoostTime(Date boostTime) {
		this.boostTime = boostTime;
	}
	
	public Date getBoostTime() {
		return this.boostTime;
	}
	public void setOpenTime(Date openTime) {
		this.openTime = openTime;
	}
	
	public Date getOpenTime() {
		return this.openTime;
	}
	public void setOpenStatus(java.lang.Integer openStatus) {
		this.openStatus = openStatus;
	}
	
	public java.lang.Integer getOpenStatus() {
		return this.openStatus;
	}
	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getGoodsImg() {
		return goodsImg;
	}

	public void setGoodsImg(String goodsImg) {
		this.goodsImg = goodsImg;
	}
	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	
}

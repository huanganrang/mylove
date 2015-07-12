package jb.pageModel;

import java.util.Date;

@SuppressWarnings("serial")
public class LvBoostActivty implements java.io.Serializable {

	private static final long serialVersionUID = 5454155825314635342L;

	private java.lang.String id;	
	private java.lang.String goodsName;	
	private java.lang.Double goodsPrice;	
	private java.lang.String goodsImg;	
	private java.lang.Integer assistNum;	
	private Date startTime;			
	private Date endTime;			
	private java.lang.String goodsDetailImg;	

	

	public void setId(java.lang.String value) {
		this.id = value;
	}
	
	public java.lang.String getId() {
		return this.id;
	}

	
	public void setGoodsName(java.lang.String goodsName) {
		this.goodsName = goodsName;
	}
	
	public java.lang.String getGoodsName() {
		return this.goodsName;
	}
	public void setGoodsPrice(java.lang.Double goodsPrice) {
		this.goodsPrice = goodsPrice;
	}
	
	public java.lang.Double getGoodsPrice() {
		return this.goodsPrice;
	}
	public void setGoodsImg(java.lang.String goodsImg) {
		this.goodsImg = goodsImg;
	}
	
	public java.lang.String getGoodsImg() {
		return this.goodsImg;
	}
	public void setAssistNum(java.lang.Integer assistNum) {
		this.assistNum = assistNum;
	}
	
	public java.lang.Integer getAssistNum() {
		return this.assistNum;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	
	public Date getStartTime() {
		return this.startTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
	public Date getEndTime() {
		return this.endTime;
	}
	public void setGoodsDetailImg(java.lang.String goodsDetailImg) {
		this.goodsDetailImg = goodsDetailImg;
	}
	
	public java.lang.String getGoodsDetailImg() {
		return this.goodsDetailImg;
	}

}

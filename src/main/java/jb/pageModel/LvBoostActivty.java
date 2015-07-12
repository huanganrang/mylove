package jb.pageModel;

import java.util.List;

public class LvBoostActivty implements java.io.Serializable {

	private static final long serialVersionUID = 5454155825314635342L;

	private java.lang.String id;	
	private java.lang.String goodsName;	
	private java.lang.Double goodsPrice;	
	private java.lang.String goodsImg;	
	private java.lang.Integer assistNum;	
	private java.lang.String goodsDetailImg;	
	private java.lang.Integer status;	
	private java.lang.Integer hourOfDay;	
	
	private List<LvBoostRecord> recordList;
	private Integer recordNum; // 参与人数
	private Integer assistedNum; // 已获得的助力数

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
	public void setGoodsDetailImg(java.lang.String goodsDetailImg) {
		this.goodsDetailImg = goodsDetailImg;
	}
	
	public java.lang.String getGoodsDetailImg() {
		return this.goodsDetailImg;
	}
	public void setStatus(java.lang.Integer status) {
		this.status = status;
	}
	
	public java.lang.Integer getStatus() {
		return this.status;
	}
	public void setHourOfDay(java.lang.Integer hourOfDay) {
		this.hourOfDay = hourOfDay;
	}
	
	public java.lang.Integer getHourOfDay() {
		return this.hourOfDay;
	}

	public List<LvBoostRecord> getRecordList() {
		return recordList;
	}

	public void setRecordList(List<LvBoostRecord> recordList) {
		this.recordList = recordList;
	}
	
	public Integer getRecordNum() {
		return recordNum;
	}

	public void setRecordNum(Integer recordNum) {
		this.recordNum = recordNum;
	}

	public Integer getAssistedNum() {
		return assistedNum;
	}

	public void setAssistedNum(Integer assistedNum) {
		this.assistedNum = assistedNum;
	}
}

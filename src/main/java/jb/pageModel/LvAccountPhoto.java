package jb.pageModel;

import java.util.Date;

import jb.listener.Application;

public class LvAccountPhoto implements java.io.Serializable {

	private static final long serialVersionUID = 5454155825314635342L;

	private java.lang.String id;	
	private java.lang.Integer openId;	
	private java.lang.String photoImg;	
	private java.lang.String auditStatus;
	private Date createTime;			

	public java.lang.String getAuditStatusZh() {
		return Application.getString(this.auditStatus);
	}

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
	public void setPhotoImg(java.lang.String photoImg) {
		this.photoImg = photoImg;
	}
	
	public java.lang.String getPhotoImg() {
		return this.photoImg;
	}
	public java.lang.String getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(java.lang.String auditStatus) {
		this.auditStatus = auditStatus;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	public Date getCreateTime() {
		return this.createTime;
	}

}

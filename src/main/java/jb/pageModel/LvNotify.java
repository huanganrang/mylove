package jb.pageModel;

import java.util.Date;

public class LvNotify implements java.io.Serializable {

	private static final long serialVersionUID = 5454155825314635342L;

	private java.lang.String id;	
	private java.lang.Integer openId;	
	private java.lang.Integer notifyOpenId;	
	private Date createTime;			

	

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
	public void setNotifyOpenId(java.lang.Integer notifyOpenId) {
		this.notifyOpenId = notifyOpenId;
	}
	
	public java.lang.Integer getNotifyOpenId() {
		return this.notifyOpenId;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	public Date getCreateTime() {
		return this.createTime;
	}

}

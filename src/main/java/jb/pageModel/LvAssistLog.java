package jb.pageModel;

import java.util.Date;

@SuppressWarnings("serial")
public class LvAssistLog implements java.io.Serializable {

	private static final long serialVersionUID = 5454155825314635342L;

	private java.lang.String id;	
	private java.lang.String boostRecordId;	
	private java.lang.Integer openId;	
	private Date assistTime;			

	

	public void setId(java.lang.String value) {
		this.id = value;
	}
	
	public java.lang.String getId() {
		return this.id;
	}

	
	public void setBoostRecordId(java.lang.String boostRecordId) {
		this.boostRecordId = boostRecordId;
	}
	
	public java.lang.String getBoostRecordId() {
		return this.boostRecordId;
	}
	public void setOpenId(java.lang.Integer openId) {
		this.openId = openId;
	}
	
	public java.lang.Integer getOpenId() {
		return this.openId;
	}
	public void setAssistTime(Date assistTime) {
		this.assistTime = assistTime;
	}
	
	public Date getAssistTime() {
		return this.assistTime;
	}

}

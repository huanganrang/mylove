package jb.pageModel;

import java.util.Date;

@SuppressWarnings("serial")
public class LvFollow implements java.io.Serializable {

	private static final long serialVersionUID = 5454155825314635342L;

	private java.lang.String id;	
	private java.lang.Integer fromOpenId;	
	private java.lang.Integer toOpenId;	
	private Date createTime;			

	

	public void setId(java.lang.String value) {
		this.id = value;
	}
	
	public java.lang.String getId() {
		return this.id;
	}

	
	public void setFromOpenId(java.lang.Integer fromOpenId) {
		this.fromOpenId = fromOpenId;
	}
	
	public java.lang.Integer getFromOpenId() {
		return this.fromOpenId;
	}
	public void setToOpenId(java.lang.Integer toOpenId) {
		this.toOpenId = toOpenId;
	}
	
	public java.lang.Integer getToOpenId() {
		return this.toOpenId;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	public Date getCreateTime() {
		return this.createTime;
	}

}

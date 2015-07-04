package jb.pageModel;

import java.util.Date;

@SuppressWarnings("serial")
public class LvFollow implements java.io.Serializable {

	private static final long serialVersionUID = 5454155825314635342L;

	private java.lang.String id;	
	private java.lang.String fromAccountId;	
	private java.lang.String toAccountId;	
	private Date createTime;			

	

	public void setId(java.lang.String value) {
		this.id = value;
	}
	
	public java.lang.String getId() {
		return this.id;
	}

	
	public void setFromAccountId(java.lang.String fromAccountId) {
		this.fromAccountId = fromAccountId;
	}
	
	public java.lang.String getFromAccountId() {
		return this.fromAccountId;
	}
	public void setToAccountId(java.lang.String toAccountId) {
		this.toAccountId = toAccountId;
	}
	
	public java.lang.String getToAccountId() {
		return this.toAccountId;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	public Date getCreateTime() {
		return this.createTime;
	}

}

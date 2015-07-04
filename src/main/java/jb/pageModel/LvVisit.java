package jb.pageModel;

import java.util.Date;

@SuppressWarnings("serial")
public class LvVisit implements java.io.Serializable {

	private static final long serialVersionUID = 5454155825314635342L;

	private java.lang.String id;	
	private java.lang.String accountId;	
	private java.lang.String visitAccountId;	
	private Date createTime;			

	

	public void setId(java.lang.String value) {
		this.id = value;
	}
	
	public java.lang.String getId() {
		return this.id;
	}

	
	public void setAccountId(java.lang.String accountId) {
		this.accountId = accountId;
	}
	
	public java.lang.String getAccountId() {
		return this.accountId;
	}
	public void setVisitAccountId(java.lang.String visitAccountId) {
		this.visitAccountId = visitAccountId;
	}
	
	public java.lang.String getVisitAccountId() {
		return this.visitAccountId;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	public Date getCreateTime() {
		return this.createTime;
	}

}

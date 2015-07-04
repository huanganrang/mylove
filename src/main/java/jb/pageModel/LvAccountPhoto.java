package jb.pageModel;

import java.util.Date;

@SuppressWarnings("serial")
public class LvAccountPhoto implements java.io.Serializable {

	private static final long serialVersionUID = 5454155825314635342L;

	private java.lang.String id;	
	private java.lang.Integer accountId;	
	private java.lang.String photoImg;	
	private Date createTime;			

	

	public void setId(java.lang.String value) {
		this.id = value;
	}
	
	public java.lang.String getId() {
		return this.id;
	}

	
	public void setAccountId(java.lang.Integer accountId) {
		this.accountId = accountId;
	}
	
	public java.lang.Integer getAccountId() {
		return this.accountId;
	}
	public void setPhotoImg(java.lang.String photoImg) {
		this.photoImg = photoImg;
	}
	
	public java.lang.String getPhotoImg() {
		return this.photoImg;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	public Date getCreateTime() {
		return this.createTime;
	}

}

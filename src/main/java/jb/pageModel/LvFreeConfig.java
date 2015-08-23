package jb.pageModel;


public class LvFreeConfig implements java.io.Serializable {

	private static final long serialVersionUID = 5454155825314635342L;

	private java.lang.String id;	
	private java.lang.Integer openId;	
	private java.lang.Integer usedNum;	
	private java.lang.String ftype;	

	

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
	public void setUsedNum(java.lang.Integer usedNum) {
		this.usedNum = usedNum;
	}
	
	public java.lang.Integer getUsedNum() {
		return this.usedNum;
	}
	public void setFtype(java.lang.String ftype) {
		this.ftype = ftype;
	}
	
	public java.lang.String getFtype() {
		return this.ftype;
	}

}

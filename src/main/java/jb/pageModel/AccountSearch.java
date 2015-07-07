package jb.pageModel;


public class AccountSearch implements java.io.Serializable {

	private static final long serialVersionUID = 5454155825314635342L;

	private java.lang.String openId;	// 当前用户openId
	private java.lang.String searchType;	// 搜索类型(1、全国；2、同城；3、openId搜索)
	private java.lang.String searchOpenId; // 搜索账户号openId值
	
	public java.lang.String getOpenId() {
		return openId;
	}
	public void setOpenId(java.lang.String openId) {
		this.openId = openId;
	}
	public java.lang.String getSearchType() {
		return searchType;
	}
	public void setSearchType(java.lang.String searchType) {
		this.searchType = searchType;
	}
	public java.lang.String getSearchOpenId() {
		return searchOpenId;
	}
	public void setSearchOpenId(java.lang.String searchOpenId) {
		this.searchOpenId = searchOpenId;
	}	

}

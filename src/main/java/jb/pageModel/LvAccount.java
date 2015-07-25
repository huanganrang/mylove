package jb.pageModel;

import java.util.Date;
import java.util.List;

import jb.listener.Application;

public class LvAccount implements java.io.Serializable {

	private static final long serialVersionUID = 5454155825314635342L;

	private java.lang.String id;	
	private java.lang.Integer openId;	
	private java.lang.String loginName;	
	private java.lang.String nickName;	
	private java.lang.String password;	
	private java.lang.String sex;	
	private Date birthday;			
	private java.lang.Integer height;	
	private java.lang.Integer weight;	
	private java.lang.String mobile;	
	private java.lang.String qq;	
	private java.lang.String address;	
	private java.lang.String education;	
	private java.lang.String profession;	
	private java.lang.String monthIncome;	
	private java.lang.String marryStatus;	
	private java.lang.String constellation;	
	private java.lang.String personDesc;	
	private Date createTime;			
	private Date updateTime;			
	private java.lang.String auditStatus;	
	private java.lang.String headImg;	
	private Long longitude;	
	private Long latitude;	
	private java.lang.String vipLevel;	
	private Date vipOpenTime;			
	private Date vipEndTime;			
	private java.lang.String online;	
	private java.lang.String lastLoginArea;
	private Date lastLoginTime;			
	private java.lang.Integer visitNum;	
	private java.lang.Integer followNum;
	private java.lang.Integer qqSecret;
	private java.lang.Integer mobileSecret;
	private java.lang.String hxPassword;
	private java.lang.Integer hxStatus;
	
	private Integer age;
	private String oldPass;
	private java.lang.Integer byOpenId;	
	private java.lang.Integer isVisit;	
	private List<LvAccountPhoto> photoList;
	
	public String getSexZh() {
		return Application.getString(this.sex);
	}
	public String getAuditStatusZh() {
		return Application.getString(this.auditStatus);
	}
	public String getVipLevelZh() {
		return Application.getString(this.vipLevel);
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
	public void setLoginName(java.lang.String loginName) {
		this.loginName = loginName;
	}
	
	public java.lang.String getLoginName() {
		return this.loginName;
	}
	public void setNickName(java.lang.String nickName) {
		this.nickName = nickName;
	}
	
	public java.lang.String getNickName() {
		return this.nickName;
	}
	public void setPassword(java.lang.String password) {
		this.password = password;
	}
	
	public java.lang.String getPassword() {
		return this.password;
	}
	public void setSex(java.lang.String sex) {
		this.sex = sex;
	}
	
	public java.lang.String getSex() {
		return this.sex;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
	public Date getBirthday() {
		return this.birthday;
	}
	public void setHeight(java.lang.Integer height) {
		this.height = height;
	}
	
	public java.lang.Integer getHeight() {
		return this.height;
	}
	public void setWeight(java.lang.Integer weight) {
		this.weight = weight;
	}
	
	public java.lang.Integer getWeight() {
		return this.weight;
	}
	public void setMobile(java.lang.String mobile) {
		this.mobile = mobile;
	}
	
	public java.lang.String getMobile() {
		return this.mobile;
	}
	public void setQq(java.lang.String qq) {
		this.qq = qq;
	}
	
	public java.lang.String getQq() {
		return this.qq;
	}
	public void setAddress(java.lang.String address) {
		this.address = address;
	}
	
	public java.lang.String getAddress() {
		return this.address;
	}
	public void setEducation(java.lang.String education) {
		this.education = education;
	}
	
	public java.lang.String getEducation() {
		return this.education;
	}
	public void setProfession(java.lang.String profession) {
		this.profession = profession;
	}
	
	public java.lang.String getProfession() {
		return this.profession;
	}
	public void setMonthIncome(java.lang.String monthIncome) {
		this.monthIncome = monthIncome;
	}
	
	public java.lang.String getMonthIncome() {
		return this.monthIncome;
	}
	public void setMarryStatus(java.lang.String marryStatus) {
		this.marryStatus = marryStatus;
	}
	
	public java.lang.String getMarryStatus() {
		return this.marryStatus;
	}
	public void setConstellation(java.lang.String constellation) {
		this.constellation = constellation;
	}
	
	public java.lang.String getConstellation() {
		return this.constellation;
	}
	public void setPersonDesc(java.lang.String personDesc) {
		this.personDesc = personDesc;
	}
	
	public java.lang.String getPersonDesc() {
		return this.personDesc;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	public Date getCreateTime() {
		return this.createTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	public Date getUpdateTime() {
		return this.updateTime;
	}
	public void setAuditStatus(java.lang.String auditStatus) {
		this.auditStatus = auditStatus;
	}
	
	public java.lang.String getAuditStatus() {
		return this.auditStatus;
	}
	public void setHeadImg(java.lang.String headImg) {
		this.headImg = headImg;
	}
	
	public java.lang.String getHeadImg() {
		return this.headImg;
	}
	public void setLongitude(Long longitude) {
		this.longitude = longitude;
	}
	
	public Long getLongitude() {
		return this.longitude;
	}
	public void setLatitude(Long latitude) {
		this.latitude = latitude;
	}
	
	public Long getLatitude() {
		return this.latitude;
	}
	public void setVipLevel(java.lang.String vipLevel) {
		this.vipLevel = vipLevel;
	}
	
	public java.lang.String getVipLevel() {
		return this.vipLevel;
	}
	public void setVipOpenTime(Date vipOpenTime) {
		this.vipOpenTime = vipOpenTime;
	}
	
	public Date getVipOpenTime() {
		return this.vipOpenTime;
	}
	public void setVipEndTime(Date vipEndTime) {
		this.vipEndTime = vipEndTime;
	}
	
	public Date getVipEndTime() {
		return this.vipEndTime;
	}
	public void setOnline(java.lang.String online) {
		this.online = online;
	}
	
	public java.lang.String getOnline() {
		return this.online;
	}
	public java.lang.String getLastLoginArea() {
		return lastLoginArea;
	}

	public void setLastLoginArea(java.lang.String lastLoginArea) {
		this.lastLoginArea = lastLoginArea;
	}
	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	
	public Date getLastLoginTime() {
		return this.lastLoginTime;
	}
	public void setVisitNum(java.lang.Integer visitNum) {
		this.visitNum = visitNum;
	}
	
	public java.lang.Integer getVisitNum() {
		return this.visitNum;
	}

	public java.lang.Integer getFollowNum() {
		return followNum;
	}

	public void setFollowNum(java.lang.Integer followNum) {
		this.followNum = followNum;
	}
	
	public java.lang.Integer getQqSecret() {
		return qqSecret;
	}

	public void setQqSecret(java.lang.Integer qqSecret) {
		this.qqSecret = qqSecret;
	}

	public java.lang.Integer getMobileSecret() {
		return mobileSecret;
	}

	public void setMobileSecret(java.lang.Integer mobileSecret) {
		this.mobileSecret = mobileSecret;
	}
	
	public java.lang.String getHxPassword() {
		return hxPassword;
	}

	public void setHxPassword(java.lang.String hxPassword) {
		this.hxPassword = hxPassword;
	}

	public java.lang.Integer getHxStatus() {
		return hxStatus;
	}

	public void setHxStatus(java.lang.Integer hxStatus) {
		this.hxStatus = hxStatus;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getOldPass() {
		return oldPass;
	}

	public void setOldPass(String oldPass) {
		this.oldPass = oldPass;
	}

	public java.lang.Integer getByOpenId() {
		return byOpenId;
	}

	public void setByOpenId(java.lang.Integer byOpenId) {
		this.byOpenId = byOpenId;
	}

	public java.lang.Integer getIsVisit() {
		return isVisit;
	}

	public void setIsVisit(java.lang.Integer isVisit) {
		this.isVisit = isVisit;
	}

	public List<LvAccountPhoto> getPhotoList() {
		return photoList;
	}

	public void setPhotoList(List<LvAccountPhoto> photoList) {
		this.photoList = photoList;
	}
}

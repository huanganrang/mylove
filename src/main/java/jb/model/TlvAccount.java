
/*
 * @author John
 * @date - 2015-07-06
 */

package jb.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "lv_account")
@DynamicInsert(true)
@DynamicUpdate(true)
public class TlvAccount implements java.io.Serializable{

	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "LvAccount";
	public static final String ALIAS_ID = "主键";
	public static final String ALIAS_OPEN_ID = "openId";
	public static final String ALIAS_LOGIN_NAME = "登录账号";
	public static final String ALIAS_NICK_NAME = "昵称";
	public static final String ALIAS_PASSWORD = "登录密码";
	public static final String ALIAS_SEX = "性别";
	public static final String ALIAS_BIRTHDAY = "生日";
	public static final String ALIAS_HEIGHT = "身高（cm）";
	public static final String ALIAS_WEIGHT = "体重（kg）";
	public static final String ALIAS_MOBILE = "手机号";
	public static final String ALIAS_QQ = "QQ号";
	public static final String ALIAS_ADDRESS = "居住地";
	public static final String ALIAS_EDUCATION = "学历";
	public static final String ALIAS_PROFESSION = "职业";
	public static final String ALIAS_MONTH_INCOME = "月收入";
	public static final String ALIAS_MARRY_STATUS = "婚姻状态";
	public static final String ALIAS_CONSTELLATION = "星座";
	public static final String ALIAS_PERSON_DESC = "个性签名";
	public static final String ALIAS_CREATE_TIME = "注册时间";
	public static final String ALIAS_UPDATE_TIME = "修改时间";
	public static final String ALIAS_AUDIT_STATUS = "头像审核状态";
	public static final String ALIAS_HEAD_IMG = "头像图片路径";
	public static final String ALIAS_LONGITUDE = "经度";
	public static final String ALIAS_LATITUDE = "纬度";
	public static final String ALIAS_VIP_LEVEL = "VIP等级";
	public static final String ALIAS_VIP_OPEN_TIME = "VIP开通时间";
	public static final String ALIAS_VIP_END_TIME = "VIP结束时间";
	public static final String ALIAS_ONLINE = "在线状态";
	public static final String ALIAS_LAST_LOGIN_AREA = "最近上线区域";
	public static final String ALIAS_LAST_LOGIN_TIME = "最近在线时间";
	public static final String ALIAS_VISIT_NUM = "来访数量";
	public static final String ALIAS_FOLLOW_NUM = "关注我的用户数量";
	public static final String ALIAS_QQ_SECRET = "QQ公开度";
	public static final String ALIAS_MOBILE_SECRET = "手机公开度";
	public static final String ALIAS_HX_PASSWORD = "环信登录密码";
	public static final String ALIAS_HX_STATUS = "环信注册状态（1：成功；2：失败））";
	
	//date formats
	public static final String FORMAT_BIRTHDAY = jb.util.Constants.DATE_FORMAT_For_Entity;
	public static final String FORMAT_CREATE_TIME = jb.util.Constants.DATE_FORMAT_For_Entity;
	public static final String FORMAT_UPDATE_TIME = jb.util.Constants.DATE_FORMAT_For_Entity;
	public static final String FORMAT_VIP_OPEN_TIME = jb.util.Constants.DATE_FORMAT_For_Entity;
	public static final String FORMAT_LAST_LOGIN_TIME = jb.util.Constants.DATE_FORMAT_For_Entity;
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
	//@Length(max=36)
	private java.lang.String id;
	//@NotNull 
	private java.lang.Integer openId;
	//@Length(max=20)
	private java.lang.String loginName;
	//@Length(max=20)
	private java.lang.String nickName;
	//@NotBlank @Length(max=50)
	private java.lang.String password;
	//@NotBlank @Length(max=20)
	private java.lang.String sex;
	//@NotNull 
	private java.util.Date birthday;
	//
	private java.lang.Integer height;
	//
	private java.lang.Integer weight;
	//@Length(max=20)
	private java.lang.String mobile;
	//@Length(max=20)
	private java.lang.String qq;
	//@Length(max=100)
	private java.lang.String address;
	//@Length(max=20)
	private java.lang.String education;
	//@Length(max=20)
	private java.lang.String profession;
	//@Length(max=20)
	private java.lang.String monthIncome;
	//@Length(max=20)
	private java.lang.String marryStatus;
	//@Length(max=20)
	private java.lang.String constellation;
	//@Length(max=65535)
	private java.lang.String personDesc;
	//@NotNull 
	private java.util.Date createTime;
	//
	private java.util.Date updateTime;
	//@Length(max=20)
	private java.lang.String auditStatus;
	//@Length(max=100)
	private java.lang.String headImg;
	//
	private Long longitude;
	//
	private Long latitude;
	//@Length(max=20)
	private java.lang.String vipLevel;
	//
	private java.util.Date vipOpenTime;
	//
	private java.util.Date vipEndTime;
	//@Length(max=128)
	private java.lang.String lastLoginArea;
	//@Length(max=20)
	private java.lang.String online;
	//@NotNull 
	private java.util.Date lastLoginTime;
	//
	private java.lang.Integer visitNum;
	private java.lang.Integer followNum;
	private java.lang.Integer qqSecret;
	private java.lang.Integer mobileSecret;
	//@Length(max=50)
	private java.lang.String hxPassword;
	//
	private java.lang.Integer hxStatus;
	//columns END


		public TlvAccount(){
		}
		public TlvAccount(String id) {
			this.id = id;
		}
	

	public void setId(java.lang.String id) {
		this.id = id;
	}
	
	@Id
	@Column(name = "id", unique = true, nullable = false, length = 36)
	public java.lang.String getId() {
		return this.id;
	}
	
	@Column(name = "openId", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getOpenId() {
		return this.openId;
	}
	
	public void setOpenId(java.lang.Integer openId) {
		this.openId = openId;
	}
	
	@Column(name = "loginName", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
	public java.lang.String getLoginName() {
		return this.loginName;
	}
	
	public void setLoginName(java.lang.String loginName) {
		this.loginName = loginName;
	}
	
	@Column(name = "nickName", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
	public java.lang.String getNickName() {
		return this.nickName;
	}
	
	public void setNickName(java.lang.String nickName) {
		this.nickName = nickName;
	}
	
	@Column(name = "password", unique = false, nullable = false, insertable = true, updatable = true, length = 50)
	public java.lang.String getPassword() {
		return this.password;
	}
	
	public void setPassword(java.lang.String password) {
		this.password = password;
	}
	
	@Column(name = "sex", unique = false, nullable = false, insertable = true, updatable = true, length = 20)
	public java.lang.String getSex() {
		return this.sex;
	}
	
	public void setSex(java.lang.String sex) {
		this.sex = sex;
	}
	

	@Column(name = "birthday", unique = false, nullable = false, insertable = true, updatable = true, length = 10)
	public java.util.Date getBirthday() {
		return this.birthday;
	}
	
	public void setBirthday(java.util.Date birthday) {
		this.birthday = birthday;
	}
	
	@Column(name = "height", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getHeight() {
		return this.height;
	}
	
	public void setHeight(java.lang.Integer height) {
		this.height = height;
	}
	
	@Column(name = "weight", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getWeight() {
		return this.weight;
	}
	
	public void setWeight(java.lang.Integer weight) {
		this.weight = weight;
	}
	
	@Column(name = "mobile", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
	public java.lang.String getMobile() {
		return this.mobile;
	}
	
	public void setMobile(java.lang.String mobile) {
		this.mobile = mobile;
	}
	
	@Column(name = "qq", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
	public java.lang.String getQq() {
		return this.qq;
	}
	
	public void setQq(java.lang.String qq) {
		this.qq = qq;
	}
	
	@Column(name = "address", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public java.lang.String getAddress() {
		return this.address;
	}
	
	public void setAddress(java.lang.String address) {
		this.address = address;
	}
	
	@Column(name = "education", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
	public java.lang.String getEducation() {
		return this.education;
	}
	
	public void setEducation(java.lang.String education) {
		this.education = education;
	}
	
	@Column(name = "profession", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
	public java.lang.String getProfession() {
		return this.profession;
	}
	
	public void setProfession(java.lang.String profession) {
		this.profession = profession;
	}
	
	@Column(name = "monthIncome", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
	public java.lang.String getMonthIncome() {
		return this.monthIncome;
	}
	
	public void setMonthIncome(java.lang.String monthIncome) {
		this.monthIncome = monthIncome;
	}
	
	@Column(name = "marryStatus", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
	public java.lang.String getMarryStatus() {
		return this.marryStatus;
	}
	
	public void setMarryStatus(java.lang.String marryStatus) {
		this.marryStatus = marryStatus;
	}
	
	@Column(name = "constellation", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
	public java.lang.String getConstellation() {
		return this.constellation;
	}
	
	public void setConstellation(java.lang.String constellation) {
		this.constellation = constellation;
	}
	
	@Column(name = "personDesc", unique = false, nullable = true, insertable = true, updatable = true, length = 65535)
	public java.lang.String getPersonDesc() {
		return this.personDesc;
	}
	
	public void setPersonDesc(java.lang.String personDesc) {
		this.personDesc = personDesc;
	}
	

	@Column(name = "createTime", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
	public java.util.Date getCreateTime() {
		return this.createTime;
	}
	
	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}
	

	@Column(name = "updateTime", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
	public java.util.Date getUpdateTime() {
		return this.updateTime;
	}
	
	public void setUpdateTime(java.util.Date updateTime) {
		this.updateTime = updateTime;
	}
	
	@Column(name = "auditStatus", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
	public java.lang.String getAuditStatus() {
		return this.auditStatus;
	}
	
	public void setAuditStatus(java.lang.String auditStatus) {
		this.auditStatus = auditStatus;
	}
	
	@Column(name = "headImg", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public java.lang.String getHeadImg() {
		return this.headImg;
	}
	
	public void setHeadImg(java.lang.String headImg) {
		this.headImg = headImg;
	}
	
	@Column(name = "longitude", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public Long getLongitude() {
		return this.longitude;
	}
	
	public void setLongitude(Long longitude) {
		this.longitude = longitude;
	}
	
	@Column(name = "latitude", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public Long getLatitude() {
		return this.latitude;
	}
	
	public void setLatitude(Long latitude) {
		this.latitude = latitude;
	}
	
	@Column(name = "vipLevel", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
	public java.lang.String getVipLevel() {
		return this.vipLevel;
	}
	
	public void setVipLevel(java.lang.String vipLevel) {
		this.vipLevel = vipLevel;
	}
	

	@Column(name = "vipOpenTime", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
	public java.util.Date getVipOpenTime() {
		return this.vipOpenTime;
	}
	
	public void setVipOpenTime(java.util.Date vipOpenTime) {
		this.vipOpenTime = vipOpenTime;
	}
	
	@Column(name = "vipEndTime", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
	public java.util.Date getVipEndTime() {
		return this.vipEndTime;
	}
	
	public void setVipEndTime(java.util.Date vipEndTime) {
		this.vipEndTime = vipEndTime;
	}
	
	@Column(name = "online", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
	public java.lang.String getOnline() {
		return this.online;
	}
	
	public void setOnline(java.lang.String online) {
		this.online = online;
	}
	
	@Column(name = "lastLoginArea", unique = false, nullable = true, insertable = true, updatable = true, length = 128)
	public java.lang.String getLastLoginArea() {
		return this.lastLoginArea;
	}
	
	public void setLastLoginArea(java.lang.String lastLoginArea) {
		this.lastLoginArea = lastLoginArea;
	}
	

	@Column(name = "lastLoginTime", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
	public java.util.Date getLastLoginTime() {
		return this.lastLoginTime;
	}
	
	public void setLastLoginTime(java.util.Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	
	@Column(name = "visitNum", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getVisitNum() {
		return this.visitNum;
	}
	
	public void setVisitNum(java.lang.Integer visitNum) {
		this.visitNum = visitNum;
	}
	
	@Column(name = "followNum", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getFollowNum() {
		return this.followNum;
	}
	
	public void setFollowNum(java.lang.Integer followNum) {
		this.followNum = followNum;
	}
	
	@Column(name = "qqSecret", unique = false, nullable = true, insertable = true, updatable = true, length = 1)
	public java.lang.Integer getQqSecret() {
		return qqSecret;
	}
	
	public void setQqSecret(java.lang.Integer qqSecret) {
		this.qqSecret = qqSecret;
	}
	
	@Column(name = "mobileSecret", unique = false, nullable = true, insertable = true, updatable = true, length = 1)
	public java.lang.Integer getMobileSecret() {
		return mobileSecret;
	}
	
	public void setMobileSecret(java.lang.Integer mobileSecret) {
		this.mobileSecret = mobileSecret;
	}
	
	@Column(name = "hxPassword", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public java.lang.String getHxPassword() {
		return this.hxPassword;
	}
	
	public void setHxPassword(java.lang.String hxPassword) {
		this.hxPassword = hxPassword;
	}
	
	@Column(name = "hxStatus", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getHxStatus() {
		return this.hxStatus;
	}
	
	public void setHxStatus(java.lang.Integer hxStatus) {
		this.hxStatus = hxStatus;
	}
	
	/*
	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("OpenId",getOpenId())
			.append("LoginName",getLoginName())
			.append("NickName",getNickName())
			.append("Password",getPassword())
			.append("Sex",getSex())
			.append("Birthday",getBirthday())
			.append("Height",getHeight())
			.append("Weight",getWeight())
			.append("Mobile",getMobile())
			.append("Qq",getQq())
			.append("Address",getAddress())
			.append("Education",getEducation())
			.append("Profession",getProfession())
			.append("MonthIncome",getMonthIncome())
			.append("MarryStatus",getMarryStatus())
			.append("Constellation",getConstellation())
			.append("PersonDesc",getPersonDesc())
			.append("CreateTime",getCreateTime())
			.append("UpdateTime",getUpdateTime())
			.append("AuditStatus",getAuditStatus())
			.append("HeadImg",getHeadImg())
			.append("Longitude",getLongitude())
			.append("Latitude",getLatitude())
			.append("VipLevel",getVipLevel())
			.append("VipOpenTime",getVipOpenTime())
			.append("Online",getOnline())
			.append("LastLoginTime",getLastLoginTime())
			.append("VisitNum",getVisitNum())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof LvAccount == false) return false;
		if(this == obj) return true;
		LvAccount other = (LvAccount)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}*/
}


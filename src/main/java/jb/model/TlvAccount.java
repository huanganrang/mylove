
/*
 * @author John
 * @date - 2015-07-04
 */

package jb.model;

import javax.persistence.*;

import java.util.Date;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@SuppressWarnings("serial")
@Entity
@Table(name = "lv_account")
@DynamicInsert(true)
@DynamicUpdate(true)
public class TlvAccount implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "LvAccount";
	public static final String ALIAS_ID = "主键";
	public static final String ALIAS_LOGIN_NAME = "登录账号";
	public static final String ALIAS_NICK_NAME = "昵称";
	public static final String ALIAS_PASSWORD = "登录密码";
	public static final String ALIAS_SEX = "性别（1、男；2、女）";
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
	public static final String ALIAS_ONLINE = "在线状态";
	public static final String ALIAS_LAST_LOGIN_TIME = "最近在线时间";
	public static final String ALIAS_VISIT_NUM = "来访数量";
	
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
	//@Length(max=20)
	private java.lang.String loginName;
	//@Length(max=20)
	private java.lang.String nickName;
	//@NotBlank @Length(max=20)
	private java.lang.String password;
	//@NotNull 
	private java.lang.Integer sex;
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
	//
	private java.lang.Integer education;
	//
	private java.lang.Integer profession;
	//
	private java.lang.Integer monthIncome;
	//
	private java.lang.Integer marryStatus;
	//
	private java.lang.Integer constellation;
	//@Length(max=65535)
	private java.lang.String personDesc;
	//@NotNull 
	private java.util.Date createTime;
	//
	private java.util.Date updateTime;
	//
	private java.lang.Integer auditStatus;
	//@Length(max=100)
	private java.lang.String headImg;
	//
	private Long longitude;
	//
	private Long latitude;
	//
	private java.lang.Integer vipLevel;
	//
	private java.util.Date vipOpenTime;
	//
	private java.lang.Integer online;
	//@NotNull 
	private java.util.Date lastLoginTime;
	//
	private java.lang.Integer visitNum;
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
	
	@Column(name = "password", unique = false, nullable = false, insertable = true, updatable = true, length = 20)
	public java.lang.String getPassword() {
		return this.password;
	}
	
	public void setPassword(java.lang.String password) {
		this.password = password;
	}
	
	@Column(name = "sex", unique = false, nullable = false, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getSex() {
		return this.sex;
	}
	
	public void setSex(java.lang.Integer sex) {
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
	
	@Column(name = "education", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getEducation() {
		return this.education;
	}
	
	public void setEducation(java.lang.Integer education) {
		this.education = education;
	}
	
	@Column(name = "profession", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getProfession() {
		return this.profession;
	}
	
	public void setProfession(java.lang.Integer profession) {
		this.profession = profession;
	}
	
	@Column(name = "monthIncome", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getMonthIncome() {
		return this.monthIncome;
	}
	
	public void setMonthIncome(java.lang.Integer monthIncome) {
		this.monthIncome = monthIncome;
	}
	
	@Column(name = "marryStatus", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getMarryStatus() {
		return this.marryStatus;
	}
	
	public void setMarryStatus(java.lang.Integer marryStatus) {
		this.marryStatus = marryStatus;
	}
	
	@Column(name = "constellation", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getConstellation() {
		return this.constellation;
	}
	
	public void setConstellation(java.lang.Integer constellation) {
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
	
	@Column(name = "auditStatus", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getAuditStatus() {
		return this.auditStatus;
	}
	
	public void setAuditStatus(java.lang.Integer auditStatus) {
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
	
	@Column(name = "vipLevel", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getVipLevel() {
		return this.vipLevel;
	}
	
	public void setVipLevel(java.lang.Integer vipLevel) {
		this.vipLevel = vipLevel;
	}
	

	@Column(name = "vipOpenTime", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
	public java.util.Date getVipOpenTime() {
		return this.vipOpenTime;
	}
	
	public void setVipOpenTime(java.util.Date vipOpenTime) {
		this.vipOpenTime = vipOpenTime;
	}
	
	@Column(name = "online", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getOnline() {
		return this.online;
	}
	
	public void setOnline(java.lang.Integer online) {
		this.online = online;
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
	
	
	/*
	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
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


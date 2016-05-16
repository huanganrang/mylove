
/*
 * @author John
 * @date - 2015-07-09
 */

package jb.model;

import javax.persistence.*;

import java.util.Date;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@SuppressWarnings("serial")
@Entity
@Table(name = "lv_address")
@DynamicInsert(true)
@DynamicUpdate(true)
public class TlvAddress implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "LvAddress";
	public static final String ALIAS_ID = "主键";
	public static final String ALIAS_OPEN_ID = "用户openId";
	public static final String ALIAS_CONSIGNEE = "收货人姓名";
	public static final String ALIAS_MOBILE = "手机号";
	public static final String ALIAS_PROVINCE = "所在省code";
	public static final String ALIAS_CITY = "所在市code";
	public static final String ALIAS_DISTRICT = "所在区code";
	public static final String ALIAS_ADDRESS = "详细地址";
	public static final String ALIAS_CREATE_TIME = "创建时间";
	public static final String ALIAS_UPDATE_TIME = "更新时间";
	
	//date formats
	public static final String FORMAT_CREATE_TIME = jb.util.Constants.DATE_FORMAT_For_Entity;
	public static final String FORMAT_UPDATE_TIME = jb.util.Constants.DATE_FORMAT_For_Entity;
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
	//@Length(max=36)
	private java.lang.String id;
	//@NotNull 
	private java.lang.Integer openId;
	//@Length(max=20)
	private java.lang.String consignee;
	//@Length(max=20)
	private java.lang.String mobile;
	//@Length(max=6)
	private java.lang.String province;
	//@Length(max=6)
	private java.lang.String city;
	//@Length(max=6)
	private java.lang.String district;
	//@Length(max=100)
	private java.lang.String address;
	//
	private java.util.Date createTime;
	//
	private java.util.Date updateTime;
	//columns END


		public TlvAddress(){
		}
		public TlvAddress(String id) {
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
	
	@Column(name = "openId", unique = false, nullable = false, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getOpenId() {
		return this.openId;
	}
	
	public void setOpenId(java.lang.Integer openId) {
		this.openId = openId;
	}
	
	@Column(name = "consignee", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
	public java.lang.String getConsignee() {
		return this.consignee;
	}
	
	public void setConsignee(java.lang.String consignee) {
		this.consignee = consignee;
	}
	
	@Column(name = "mobile", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
	public java.lang.String getMobile() {
		return this.mobile;
	}
	
	public void setMobile(java.lang.String mobile) {
		this.mobile = mobile;
	}
	
	@Column(name = "province", unique = false, nullable = true, insertable = true, updatable = true, length = 6)
	public java.lang.String getProvince() {
		return this.province;
	}
	
	public void setProvince(java.lang.String province) {
		this.province = province;
	}
	
	@Column(name = "city", unique = false, nullable = true, insertable = true, updatable = true, length = 6)
	public java.lang.String getCity() {
		return this.city;
	}
	
	public void setCity(java.lang.String city) {
		this.city = city;
	}
	
	@Column(name = "district", unique = false, nullable = true, insertable = true, updatable = true, length = 6)
	public java.lang.String getDistrict() {
		return this.district;
	}
	
	public void setDistrict(java.lang.String district) {
		this.district = district;
	}
	
	@Column(name = "address", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public java.lang.String getAddress() {
		return this.address;
	}
	
	public void setAddress(java.lang.String address) {
		this.address = address;
	}
	

	@Column(name = "createTime", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
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
	
	
	/*
	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("OpenId",getOpenId())
			.append("Consignee",getConsignee())
			.append("Mobile",getMobile())
			.append("Province",getProvince())
			.append("City",getCity())
			.append("District",getDistrict())
			.append("Address",getAddress())
			.append("CreateTime",getCreateTime())
			.append("UpdateTime",getUpdateTime())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof LvAddress == false) return false;
		if(this == obj) return true;
		LvAddress other = (LvAddress)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}*/
}


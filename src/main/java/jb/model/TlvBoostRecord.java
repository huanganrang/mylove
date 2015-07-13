
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
@Table(name = "lv_boost_record")
@DynamicInsert(true)
@DynamicUpdate(true)
public class TlvBoostRecord implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "LvBoostRecord";
	public static final String ALIAS_ID = "主键";
	public static final String ALIAS_OPEN_ID = "用户openId";
	public static final String ALIAS_ACTIVTY_ID = "挖宝活动ID";
	public static final String ALIAS_ASSIST_NUM = "助力数";
	public static final String ALIAS_VISIT_NUM = "查看数";
	public static final String ALIAS_BOOST_TIME = "征集时间";
	public static final String ALIAS_OPEN_TIME = "宝箱开启时间";
	public static final String ALIAS_OPEN_STATUS = "是否开启成功(1:成功；2：失败)";
	
	//date formats
	public static final String FORMAT_BOOST_TIME = jb.util.Constants.DATE_FORMAT_For_Entity;
	public static final String FORMAT_OPEN_TIME = jb.util.Constants.DATE_FORMAT_For_Entity;
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
	//@Length(max=36)
	private java.lang.String id;
	//@NotNull 
	private java.lang.Integer openId;
	//@NotBlank @Length(max=36)
	private java.lang.String activtyId;
	//
	private java.lang.Integer assistNum;
	//
	private java.lang.Integer visitNum;
	//@NotNull 
	private java.util.Date boostTime;
	//
	private java.util.Date openTime;
	//
	private java.lang.Integer openStatus;
	//columns END


		public TlvBoostRecord(){
		}
		public TlvBoostRecord(String id) {
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
	
	@Column(name = "activtyId", unique = false, nullable = false, insertable = true, updatable = true, length = 36)
	public java.lang.String getActivtyId() {
		return this.activtyId;
	}
	
	public void setActivtyId(java.lang.String activtyId) {
		this.activtyId = activtyId;
	}
	
	@Column(name = "assist_num", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getAssistNum() {
		return this.assistNum;
	}
	
	public void setAssistNum(java.lang.Integer assistNum) {
		this.assistNum = assistNum;
	}
	
	@Column(name = "visit_num", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getVisitNum() {
		return this.visitNum;
	}
	
	public void setVisitNum(java.lang.Integer visitNum) {
		this.visitNum = visitNum;
	}
	

	@Column(name = "boostTime", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
	public java.util.Date getBoostTime() {
		return this.boostTime;
	}
	
	public void setBoostTime(java.util.Date boostTime) {
		this.boostTime = boostTime;
	}
	

	@Column(name = "openTime", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
	public java.util.Date getOpenTime() {
		return this.openTime;
	}
	
	public void setOpenTime(java.util.Date openTime) {
		this.openTime = openTime;
	}
	
	@Column(name = "openStatus", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getOpenStatus() {
		return this.openStatus;
	}
	
	public void setOpenStatus(java.lang.Integer openStatus) {
		this.openStatus = openStatus;
	}
	
	
	private TlvBoostActivty tlvBoostActivty;
	public void setTlvBoostActivty(TlvBoostActivty tlvBoostActivty){
		this.tlvBoostActivty = tlvBoostActivty;
	}
	
	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "activtyId",nullable = false, insertable = false, updatable = false) 
	})
	public TlvBoostActivty getTlvBoostActivty() {
		return tlvBoostActivty;
	}
	
	/*
	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("OpenId",getOpenId())
			.append("ActivtyId",getActivtyId())
			.append("AssistNum",getAssistNum())
			.append("VisitNum",getVisitNum())
			.append("BoostTime",getBoostTime())
			.append("OpenTime",getOpenTime())
			.append("OpenStatus",getOpenStatus())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof LvBoostRecord == false) return false;
		if(this == obj) return true;
		LvBoostRecord other = (LvBoostRecord)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}*/
}


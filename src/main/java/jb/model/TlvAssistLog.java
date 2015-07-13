
/*
 * @author John
 * @date - 2015-07-12
 */

package jb.model;

import javax.persistence.*;

import java.util.Date;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@SuppressWarnings("serial")
@Entity
@Table(name = "lv_assist_log")
@DynamicInsert(true)
@DynamicUpdate(true)
public class TlvAssistLog implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "LvAssistLog";
	public static final String ALIAS_ID = "主键";
	public static final String ALIAS_BOOST_RECORD_ID = "挖宝记录ID";
	public static final String ALIAS_OPEN_ID = "助力人";
	public static final String ALIAS_ASSIST_TIME = "助力时间";
	
	//date formats
	public static final String FORMAT_ASSIST_TIME = jb.util.Constants.DATE_FORMAT_For_Entity;
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
	//@Length(max=36)
	private java.lang.String id;
	//@NotBlank @Length(max=36)
	private java.lang.String boostRecordId;
	//@NotNull 
	private java.lang.Integer openId;
	//@NotNull 
	private java.util.Date assistTime;
	//columns END


		public TlvAssistLog(){
		}
		public TlvAssistLog(String id) {
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
	
	@Column(name = "boostRecordId", unique = false, nullable = false, insertable = true, updatable = true, length = 36)
	public java.lang.String getBoostRecordId() {
		return this.boostRecordId;
	}
	
	public void setBoostRecordId(java.lang.String boostRecordId) {
		this.boostRecordId = boostRecordId;
	}
	
	@Column(name = "openId", unique = false, nullable = false, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getOpenId() {
		return this.openId;
	}
	
	public void setOpenId(java.lang.Integer openId) {
		this.openId = openId;
	}
	

	@Column(name = "assistTime", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
	public java.util.Date getAssistTime() {
		return this.assistTime;
	}
	
	public void setAssistTime(java.util.Date assistTime) {
		this.assistTime = assistTime;
	}
	
	
	private TlvBoostRecord tlvBoostRecord;
	public void setTlvBoostRecord(TlvBoostRecord tlvBoostRecord){
		this.tlvBoostRecord = tlvBoostRecord;
	}
	
	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "boostRecordId",nullable = false, insertable = false, updatable = false) 
	})
	public TlvBoostRecord getTlvBoostRecord() {
		return tlvBoostRecord;
	}
	
	/*
	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("BoostRecordId",getBoostRecordId())
			.append("OpenId",getOpenId())
			.append("AssistTime",getAssistTime())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof LvAssistLog == false) return false;
		if(this == obj) return true;
		LvAssistLog other = (LvAssistLog)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}*/
}



/*
 * @author John
 * @date - 2015-08-23
 */

package jb.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "lv_free_config")
@DynamicInsert(true)
@DynamicUpdate(true)
public class TlvFreeConfig implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "LvFreeConfig";
	public static final String ALIAS_ID = "主键";
	public static final String ALIAS_OPEN_ID = "openId";
	public static final String ALIAS_USED_NUM = "使用次数";
	public static final String ALIAS_FTYPE = "免费类型";
	
	//date formats
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
	//@Length(max=36)
	private java.lang.String id;
	//
	private java.lang.Integer openId;
	//
	private java.lang.Integer usedNum;
	//@Length(max=4)
	private java.lang.String ftype;
	//columns END


		public TlvFreeConfig(){
		}
		public TlvFreeConfig(String id) {
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
	
	@Column(name = "used_num", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getUsedNum() {
		return this.usedNum;
	}
	
	public void setUsedNum(java.lang.Integer usedNum) {
		this.usedNum = usedNum;
	}
	
	@Column(name = "ftype", unique = false, nullable = true, insertable = true, updatable = true, length = 4)
	public java.lang.String getFtype() {
		return this.ftype;
	}
	
	public void setFtype(java.lang.String ftype) {
		this.ftype = ftype;
	}
	
	
	/*
	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("OpenId",getOpenId())
			.append("UsedNum",getUsedNum())
			.append("Ftype",getFtype())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof LvFreeConfig == false) return false;
		if(this == obj) return true;
		LvFreeConfig other = (LvFreeConfig)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}*/
}


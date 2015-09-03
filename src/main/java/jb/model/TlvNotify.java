
/*
 * @author John
 * @date - 2015-09-04
 */

package jb.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "lv_notify")
@DynamicInsert(true)
@DynamicUpdate(true)
public class TlvNotify implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "LvNotify";
	public static final String ALIAS_ID = "主键";
	public static final String ALIAS_OPEN_ID = "用户openId";
	public static final String ALIAS_NOTIFY_OPEN_ID = "被打招呼用户openId";
	public static final String ALIAS_CREATE_TIME = "打招呼时间";
	
	//date formats
	public static final String FORMAT_CREATE_TIME = jb.util.Constants.DATE_FORMAT_For_Entity;
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
	//@Length(max=36)
	private java.lang.String id;
	//
	private java.lang.Integer openId;
	//
	private java.lang.Integer notifyOpenId;
	//
	private java.util.Date createTime;
	//columns END


		public TlvNotify(){
		}
		public TlvNotify(String id) {
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
	
	@Column(name = "notifyOpenId", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getNotifyOpenId() {
		return this.notifyOpenId;
	}
	
	public void setNotifyOpenId(java.lang.Integer notifyOpenId) {
		this.notifyOpenId = notifyOpenId;
	}
	

	@Column(name = "createTime", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
	public java.util.Date getCreateTime() {
		return this.createTime;
	}
	
	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}
	
	
	/*
	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("OpenId",getOpenId())
			.append("NotifyOpenId",getNotifyOpenId())
			.append("CreateTime",getCreateTime())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof LvNotify == false) return false;
		if(this == obj) return true;
		LvNotify other = (LvNotify)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}*/
}


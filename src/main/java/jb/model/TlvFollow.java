
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
@Table(name = "lv_follow")
@DynamicInsert(true)
@DynamicUpdate(true)
public class TlvFollow implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "LvFollow";
	public static final String ALIAS_ID = "主键";
	public static final String ALIAS_FROM_ACCOUNT_ID = "关注人ID";
	public static final String ALIAS_TO_ACCOUNT_ID = "被关注人ID";
	public static final String ALIAS_CREATE_TIME = "关注时间";
	
	//date formats
	public static final String FORMAT_CREATE_TIME = jb.util.Constants.DATE_FORMAT_For_Entity;
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
	//@Length(max=36)
	private java.lang.String id;
	//@NotBlank @Length(max=36)
	private java.lang.String fromAccountId;
	//@NotBlank @Length(max=36)
	private java.lang.String toAccountId;
	//@NotNull 
	private java.util.Date createTime;
	//columns END


		public TlvFollow(){
		}
		public TlvFollow(String id) {
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
	
	@Column(name = "fromAccountId", unique = false, nullable = false, insertable = true, updatable = true, length = 36)
	public java.lang.String getFromAccountId() {
		return this.fromAccountId;
	}
	
	public void setFromAccountId(java.lang.String fromAccountId) {
		this.fromAccountId = fromAccountId;
	}
	
	@Column(name = "toAccountId", unique = false, nullable = false, insertable = true, updatable = true, length = 36)
	public java.lang.String getToAccountId() {
		return this.toAccountId;
	}
	
	public void setToAccountId(java.lang.String toAccountId) {
		this.toAccountId = toAccountId;
	}
	

	@Column(name = "createTime", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
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
			.append("FromAccountId",getFromAccountId())
			.append("ToAccountId",getToAccountId())
			.append("CreateTime",getCreateTime())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof LvFollow == false) return false;
		if(this == obj) return true;
		LvFollow other = (LvFollow)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}*/
}


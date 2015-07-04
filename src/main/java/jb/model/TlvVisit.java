
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
@Table(name = "lv_visit")
@DynamicInsert(true)
@DynamicUpdate(true)
public class TlvVisit implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "LvVisit";
	public static final String ALIAS_ID = "主键";
	public static final String ALIAS_ACCOUNT_ID = "用户ID";
	public static final String ALIAS_VISIT_ACCOUNT_ID = "来访用户ID";
	public static final String ALIAS_CREATE_TIME = "来访时间";
	
	//date formats
	public static final String FORMAT_CREATE_TIME = jb.util.Constants.DATE_FORMAT_For_Entity;
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
	//@Length(max=36)
	private java.lang.String id;
	//@NotBlank @Length(max=36)
	private java.lang.String accountId;
	//@NotBlank @Length(max=36)
	private java.lang.String visitAccountId;
	//@NotNull 
	private java.util.Date createTime;
	//columns END


		public TlvVisit(){
		}
		public TlvVisit(String id) {
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
	
	@Column(name = "accountId", unique = false, nullable = false, insertable = true, updatable = true, length = 36)
	public java.lang.String getAccountId() {
		return this.accountId;
	}
	
	public void setAccountId(java.lang.String accountId) {
		this.accountId = accountId;
	}
	
	@Column(name = "visitAccountId", unique = false, nullable = false, insertable = true, updatable = true, length = 36)
	public java.lang.String getVisitAccountId() {
		return this.visitAccountId;
	}
	
	public void setVisitAccountId(java.lang.String visitAccountId) {
		this.visitAccountId = visitAccountId;
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
			.append("AccountId",getAccountId())
			.append("VisitAccountId",getVisitAccountId())
			.append("CreateTime",getCreateTime())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof LvVisit == false) return false;
		if(this == obj) return true;
		LvVisit other = (LvVisit)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}*/
}


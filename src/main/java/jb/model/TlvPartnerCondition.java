
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
@Table(name = "lv_partner_condition")
@DynamicInsert(true)
@DynamicUpdate(true)
public class TlvPartnerCondition implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "LvPartnerCondition";
	public static final String ALIAS_ID = "主键";
	public static final String ALIAS_ACCOUNT_ID = "用户ID";
	public static final String ALIAS_AGE = "年龄";
	public static final String ALIAS_ADDRESS = "居住地";
	public static final String ALIAS_HEIGHT = "身高";
	public static final String ALIAS_WEIGHT = "体重";
	public static final String ALIAS_EDUCATION = "学历";
	public static final String ALIAS_MONTH_INCOME = "月收入";
	
	//date formats
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
	//@Length(max=36)
	private java.lang.String id;
	//@NotBlank @Length(max=36)
	private java.lang.String accountId;
	//@Length(max=50)
	private java.lang.String age;
	//@Length(max=50)
	private java.lang.String address;
	//@Length(max=50)
	private java.lang.String height;
	//@Length(max=50)
	private java.lang.String weight;
	//@Length(max=50)
	private java.lang.String education;
	//@Length(max=50)
	private java.lang.String monthIncome;
	//columns END


		public TlvPartnerCondition(){
		}
		public TlvPartnerCondition(String id) {
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
	
	@Column(name = "age", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public java.lang.String getAge() {
		return this.age;
	}
	
	public void setAge(java.lang.String age) {
		this.age = age;
	}
	
	@Column(name = "address", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public java.lang.String getAddress() {
		return this.address;
	}
	
	public void setAddress(java.lang.String address) {
		this.address = address;
	}
	
	@Column(name = "height", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public java.lang.String getHeight() {
		return this.height;
	}
	
	public void setHeight(java.lang.String height) {
		this.height = height;
	}
	
	@Column(name = "weight", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public java.lang.String getWeight() {
		return this.weight;
	}
	
	public void setWeight(java.lang.String weight) {
		this.weight = weight;
	}
	
	@Column(name = "education", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public java.lang.String getEducation() {
		return this.education;
	}
	
	public void setEducation(java.lang.String education) {
		this.education = education;
	}
	
	@Column(name = "monthIncome", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public java.lang.String getMonthIncome() {
		return this.monthIncome;
	}
	
	public void setMonthIncome(java.lang.String monthIncome) {
		this.monthIncome = monthIncome;
	}
	
	
	/*
	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("AccountId",getAccountId())
			.append("Age",getAge())
			.append("Address",getAddress())
			.append("Height",getHeight())
			.append("Weight",getWeight())
			.append("Education",getEducation())
			.append("MonthIncome",getMonthIncome())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof LvPartnerCondition == false) return false;
		if(this == obj) return true;
		LvPartnerCondition other = (LvPartnerCondition)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}*/
}


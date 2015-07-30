
/*
 * @author John
 * @date - 2015-07-30
 */

package jb.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "lv_order")
@DynamicInsert(true)
@DynamicUpdate(true)
public class TlvOrder implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "LvOrder";
	public static final String ALIAS_ID = "主键";
	public static final String ALIAS_ORDER_NO = "订单号";
	public static final String ALIAS_OPEN_ID = "用户openId";
	public static final String ALIAS_VIP_LEVEL = "VIP等级";
	public static final String ALIAS_ORDER_STATUS = "订单状态";
	public static final String ALIAS_CHANNEL = "支付渠道";
	public static final String ALIAS_AMOUNT = "订单总金额";
	public static final String ALIAS_PAYTIME = "支付时间";
	public static final String ALIAS_CREATETIME = "创建时间";
	
	//date formats
	public static final String FORMAT_PAYTIME = jb.util.Constants.DATE_FORMAT_For_Entity;
	public static final String FORMAT_CREATETIME = jb.util.Constants.DATE_FORMAT_For_Entity;
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
	//@Length(max=36)
	private java.lang.String id;
	//@NotNull 
	private java.lang.Long orderNo;
	//
	private java.lang.Integer openId;
	//@Length(max=4)
	private java.lang.String vipLevel;
	//@Length(max=4)
	private java.lang.String orderStatus;
	//@Length(max=20)
	private java.lang.String channel;
	//
	private java.lang.Float amount;
	//
	private java.util.Date paytime;
	//
	private java.util.Date createtime;
	//columns END


		public TlvOrder(){
		}
		public TlvOrder(String id) {
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
	
	@Column(name = "order_no", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
	public java.lang.Long getOrderNo() {
		return this.orderNo;
	}
	
	public void setOrderNo(java.lang.Long orderNo) {
		this.orderNo = orderNo;
	}
	
	@Column(name = "openId", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getOpenId() {
		return this.openId;
	}
	
	public void setOpenId(java.lang.Integer openId) {
		this.openId = openId;
	}
	
	@Column(name = "vipLevel", unique = false, nullable = true, insertable = true, updatable = true, length = 4)
	public java.lang.String getVipLevel() {
		return this.vipLevel;
	}
	
	public void setVipLevel(java.lang.String vipLevel) {
		this.vipLevel = vipLevel;
	}
	
	@Column(name = "order_status", unique = false, nullable = true, insertable = true, updatable = true, length = 4)
	public java.lang.String getOrderStatus() {
		return this.orderStatus;
	}
	
	public void setOrderStatus(java.lang.String orderStatus) {
		this.orderStatus = orderStatus;
	}
	
	@Column(name = "channel", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
	public java.lang.String getChannel() {
		return this.channel;
	}
	
	public void setChannel(java.lang.String channel) {
		this.channel = channel;
	}
	
	@Column(name = "amount", unique = false, nullable = true, insertable = true, updatable = true, length = 12)
	public java.lang.Float getAmount() {
		return this.amount;
	}
	
	public void setAmount(java.lang.Float amount) {
		this.amount = amount;
	}
	

	@Column(name = "paytime", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
	public java.util.Date getPaytime() {
		return this.paytime;
	}
	
	public void setPaytime(java.util.Date paytime) {
		this.paytime = paytime;
	}
	

	@Column(name = "createtime", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
	public java.util.Date getCreatetime() {
		return this.createtime;
	}
	
	public void setCreatetime(java.util.Date createtime) {
		this.createtime = createtime;
	}
	
	
	/*
	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("OrderNo",getOrderNo())
			.append("OpenId",getOpenId())
			.append("VipLevel",getVipLevel())
			.append("OrderStatus",getOrderStatus())
			.append("Channel",getChannel())
			.append("Amount",getAmount())
			.append("Paytime",getPaytime())
			.append("Createtime",getCreatetime())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof LvOrder == false) return false;
		if(this == obj) return true;
		LvOrder other = (LvOrder)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}*/
}


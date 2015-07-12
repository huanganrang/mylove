
/*
 * @author John
 * @date - 2015-07-09
 */

package jb.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@SuppressWarnings("serial")
@Entity
@Table(name = "lv_boost_activty")
@DynamicInsert(true)
@DynamicUpdate(true)
public class TlvBoostActivty implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "LvBoostActivty";
	public static final String ALIAS_ID = "主键";
	public static final String ALIAS_GOODS_NAME = "活动商品名称";
	public static final String ALIAS_GOODS_PRICE = "市场价值";
	public static final String ALIAS_GOODS_IMG = "商品图片";
	public static final String ALIAS_ASSIST_NUM = "所需助力点数";
	public static final String ALIAS_START_TIME = "开始时间";
	public static final String ALIAS_END_TIME = "结束时间";
	public static final String ALIAS_GOODS_DETAIL_IMG = "商品详情图片";
	
	//date formats
	public static final String FORMAT_START_TIME = jb.util.Constants.DATE_FORMAT_For_Entity;
	public static final String FORMAT_END_TIME = jb.util.Constants.DATE_FORMAT_For_Entity;
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
	//@Length(max=36)
	private java.lang.String id;
	//@Length(max=100)
	private java.lang.String goodsName;
	//
	private java.lang.Double goodsPrice;
	//@Length(max=100)
	private java.lang.String goodsImg;
	//
	private java.lang.Integer assistNum;
	//
	private java.util.Date startTime;
	//
	private java.util.Date endTime;
	//@Length(max=100)
	private java.lang.String goodsDetailImg;
	//columns END


		public TlvBoostActivty(){
		}
		public TlvBoostActivty(String id) {
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
	
	@Column(name = "goods_name", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public java.lang.String getGoodsName() {
		return this.goodsName;
	}
	
	public void setGoodsName(java.lang.String goodsName) {
		this.goodsName = goodsName;
	}
	
	@Column(name = "goods_price", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
	public java.lang.Double getGoodsPrice() {
		return this.goodsPrice;
	}
	
	public void setGoodsPrice(java.lang.Double goodsPrice) {
		this.goodsPrice = goodsPrice;
	}
	
	@Column(name = "goods_img", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public java.lang.String getGoodsImg() {
		return this.goodsImg;
	}
	
	public void setGoodsImg(java.lang.String goodsImg) {
		this.goodsImg = goodsImg;
	}
	
	@Column(name = "assist_num", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getAssistNum() {
		return this.assistNum;
	}
	
	public void setAssistNum(java.lang.Integer assistNum) {
		this.assistNum = assistNum;
	}
	

	@Column(name = "startTime", unique = false, nullable = true, insertable = true, updatable = true, length = 8)
	public java.util.Date getStartTime() {
		return this.startTime;
	}
	
	public void setStartTime(java.util.Date startTime) {
		this.startTime = startTime;
	}
	

	@Column(name = "endTime", unique = false, nullable = true, insertable = true, updatable = true, length = 8)
	public java.util.Date getEndTime() {
		return this.endTime;
	}
	
	public void setEndTime(java.util.Date endTime) {
		this.endTime = endTime;
	}
	
	@Column(name = "goods_detail_img", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public java.lang.String getGoodsDetailImg() {
		return this.goodsDetailImg;
	}
	
	public void setGoodsDetailImg(java.lang.String goodsDetailImg) {
		this.goodsDetailImg = goodsDetailImg;
	}
	
	
	private Set<TlvBoostRecord> tlvBoostRecords = new HashSet<TlvBoostRecord>(0);
	public void setTlvBoostRecords(Set<TlvBoostRecord> tlvBoostRecords){
		this.tlvBoostRecords = tlvBoostRecords;
	}
	
	@OneToMany(cascade = { CascadeType.MERGE }, fetch = FetchType.LAZY, mappedBy = "tlvBoostActivty")
	public Set<TlvBoostRecord> getTlvBoostRecords() {
		return tlvBoostRecords;
	}
	
	/*
	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("GoodsName",getGoodsName())
			.append("GoodsPrice",getGoodsPrice())
			.append("GoodsImg",getGoodsImg())
			.append("AssistNum",getAssistNum())
			.append("StartTime",getStartTime())
			.append("EndTime",getEndTime())
			.append("GoodsDetailImg",getGoodsDetailImg())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof LvBoostActivty == false) return false;
		if(this == obj) return true;
		LvBoostActivty other = (LvBoostActivty)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}*/
}


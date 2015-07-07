package jb.pageModel;

/**
 * EasyUI 分页帮助类
 * 
 * @author John
 * 
 */
@SuppressWarnings("serial")
public class PageHelper implements java.io.Serializable {

	private int page = 1;// 当前页， 默认第1页
	private int rows = 20;// 每页显示记录数， 默认20条
	private String sort;// 排序字段
	private String order;// asc/desc

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

}

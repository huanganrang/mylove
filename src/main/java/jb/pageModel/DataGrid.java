package jb.pageModel;

import java.util.ArrayList;
import java.util.List;

/**
 * EasyUI DataGrid模型
 * 
 * @author John
 * 
 */
@SuppressWarnings("serial")
public class DataGrid implements java.io.Serializable {

	private Long total = 0L;
	@SuppressWarnings("rawtypes")
	private List rows = new ArrayList();
	private Long page; // 当前页数
	private Long pageSize; // 每页显示记录数

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	@SuppressWarnings("rawtypes")
	public List getRows() {
		return rows;
	}

	@SuppressWarnings("rawtypes")
	public void setRows(List rows) {
		this.rows = rows;
	}

	public Long getPage() {
		return page;
	}

	public void setPage(Long page) {
		this.page = page;
	}

	public Long getPageSize() {
		return pageSize;
	}

	public void setPageSize(Long pageSize) {
		this.pageSize = pageSize;
	}

}

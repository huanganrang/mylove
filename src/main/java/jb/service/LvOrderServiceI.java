package jb.service;

import jb.model.TlvOrder;
import jb.pageModel.DataGrid;
import jb.pageModel.LvOrder;
import jb.pageModel.PageHelper;

/**
 * 
 * @author John
 * 
 */
public interface LvOrderServiceI {

	/**
	 * 获取LvOrder数据表格
	 * 
	 * @param lvOrder
	 *            参数
	 * @param ph
	 *            分页帮助类
	 * @return
	 */
	public DataGrid dataGrid(LvOrder lvOrder, PageHelper ph);

	/**
	 * 添加LvOrder
	 * 
	 * @param lvOrder
	 */
	public void add(LvOrder lvOrder);

	/**
	 * 获得LvOrder对象
	 * 
	 * @param id
	 * @return
	 */
	public LvOrder get(String id);

	/**
	 * 修改LvOrder
	 * 
	 * @param lvOrder
	 */
	public void edit(LvOrder lvOrder);

	/**
	 * 删除LvOrder
	 * 
	 * @param id
	 */
	public void delete(String id);

	public TlvOrder updateStatus(LvOrder order);

}

package jb.service;

import jb.pageModel.LvNotify;
import jb.pageModel.DataGrid;
import jb.pageModel.PageHelper;

/**
 * 
 * @author John
 * 
 */
public interface LvNotifyServiceI {

	/**
	 * 获取LvNotify数据表格
	 * 
	 * @param lvNotify
	 *            参数
	 * @param ph
	 *            分页帮助类
	 * @return
	 */
	public DataGrid dataGrid(LvNotify lvNotify, PageHelper ph);

	/**
	 * 添加LvNotify
	 * 
	 * @param lvNotify
	 */
	public int add(LvNotify lvNotify);

	/**
	 * 获得LvNotify对象
	 * 
	 * @param id
	 * @return
	 */
	public LvNotify get(String id);

	/**
	 * 修改LvNotify
	 * 
	 * @param lvNotify
	 */
	public void edit(LvNotify lvNotify);

	/**
	 * 删除LvNotify
	 * 
	 * @param id
	 */
	public void delete(String id);

}

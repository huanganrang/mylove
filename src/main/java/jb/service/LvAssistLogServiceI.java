package jb.service;

import jb.pageModel.LvAssistLog;
import jb.pageModel.DataGrid;
import jb.pageModel.PageHelper;

/**
 * 
 * @author John
 * 
 */
public interface LvAssistLogServiceI {

	/**
	 * 获取LvAssistLog数据表格
	 * 
	 * @param lvAssistLog
	 *            参数
	 * @param ph
	 *            分页帮助类
	 * @return
	 */
	public DataGrid dataGrid(LvAssistLog lvAssistLog, PageHelper ph);

	/**
	 * 添加LvAssistLog
	 * 
	 * @param lvAssistLog
	 */
	public int add(LvAssistLog lvAssistLog);

	/**
	 * 获得LvAssistLog对象
	 * 
	 * @param id
	 * @return
	 */
	public LvAssistLog get(String id);

	/**
	 * 修改LvAssistLog
	 * 
	 * @param lvAssistLog
	 */
	public void edit(LvAssistLog lvAssistLog);

	/**
	 * 删除LvAssistLog
	 * 
	 * @param id
	 */
	public void delete(String id);

}

package jb.service;

import java.util.List;

import jb.pageModel.DataGrid;
import jb.pageModel.LvBoostActivty;
import jb.pageModel.PageHelper;

/**
 * 
 * @author John
 * 
 */
public interface LvBoostActivtyServiceI {

	/**
	 * 获取LvBoostActivty数据表格
	 * 
	 * @param lvBoostActivty
	 *            参数
	 * @param ph
	 *            分页帮助类
	 * @return
	 */
	public DataGrid dataGrid(LvBoostActivty lvBoostActivty, PageHelper ph);

	/**
	 * 添加LvBoostActivty
	 * 
	 * @param lvBoostActivty
	 */
	public void add(LvBoostActivty lvBoostActivty);

	/**
	 * 获得LvBoostActivty对象
	 * 
	 * @param id
	 * @return
	 */
	public LvBoostActivty get(String id);

	/**
	 * 修改LvBoostActivty
	 * 
	 * @param lvBoostActivty
	 */
	public void edit(LvBoostActivty lvBoostActivty);

	/**
	 * 删除LvBoostActivty
	 * 
	 * @param id
	 */
	public void delete(String id);

	public List<LvBoostActivty> findAllList();

}

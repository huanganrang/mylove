package jb.service;

import jb.pageModel.LvFollow;
import jb.pageModel.DataGrid;
import jb.pageModel.PageHelper;

/**
 * 
 * @author John
 * 
 */
public interface LvFollowServiceI {

	/**
	 * 获取LvFollow数据表格
	 * 
	 * @param lvFollow
	 *            参数
	 * @param ph
	 *            分页帮助类
	 * @return
	 */
	public DataGrid dataGrid(LvFollow lvFollow, PageHelper ph);

	/**
	 * 添加LvFollow
	 * 
	 * @param lvFollow
	 */
	public int add(LvFollow lvFollow);

	/**
	 * 获得LvFollow对象
	 * 
	 * @param id
	 * @return
	 */
	public LvFollow get(String id);

	/**
	 * 修改LvFollow
	 * 
	 * @param lvFollow
	 */
	public void edit(LvFollow lvFollow);

	/**
	 * 删除LvFollow
	 * 
	 * @param id
	 */
	public void delete(String id);

	public DataGrid dataGridAccount(LvFollow lvFollow, PageHelper ph);

	/**
	 * 取消关注
	 * @param f
	 */
	public int delete(LvFollow f);

}

package jb.service;

import jb.pageModel.LvAddress;
import jb.pageModel.DataGrid;
import jb.pageModel.PageHelper;

/**
 * 
 * @author John
 * 
 */
public interface LvAddressServiceI {

	/**
	 * 获取LvAddress数据表格
	 * 
	 * @param lvAddress
	 *            参数
	 * @param ph
	 *            分页帮助类
	 * @return
	 */
	public DataGrid dataGrid(LvAddress lvAddress, PageHelper ph);

	/**
	 * 添加LvAddress
	 * 
	 * @param lvAddress
	 */
	public void add(LvAddress lvAddress);

	/**
	 * 获得LvAddress对象
	 * 
	 * @param id
	 * @return
	 */
	public LvAddress get(String id);

	/**
	 * 修改LvAddress
	 * 
	 * @param lvAddress
	 */
	public void edit(LvAddress lvAddress);

	/**
	 * 删除LvAddress
	 * 
	 * @param id
	 */
	public void delete(String id);

	public LvAddress getByOpenId(Integer openId);

	public void saveOrUpdate(LvAddress address);

}

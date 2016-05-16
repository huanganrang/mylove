package jb.service;

import java.util.List;

import jb.pageModel.DataGrid;
import jb.pageModel.LvAccount;
import jb.pageModel.LvVisit;
import jb.pageModel.PageHelper;

/**
 * 
 * @author John
 * 
 */
public interface LvVisitServiceI {

	/**
	 * 获取LvVisit数据表格
	 * 
	 * @param lvVisit
	 *            参数
	 * @param ph
	 *            分页帮助类
	 * @return
	 */
	public DataGrid dataGrid(LvVisit lvVisit, PageHelper ph);

	/**
	 * 添加LvVisit
	 * 
	 * @param lvVisit
	 */
	public void add(LvVisit lvVisit);

	/**
	 * 获得LvVisit对象
	 * 
	 * @param id
	 * @return
	 */
	public LvVisit get(String id);

	/**
	 * 修改LvVisit
	 * 
	 * @param lvVisit
	 */
	public void edit(LvVisit lvVisit);

	/**
	 * 删除LvVisit
	 * 
	 * @param id
	 */
	public void delete(String id);

	public void saveOrUpdate(LvVisit lvVisit);

	public List<LvAccount> queryAllVisitAccount(Integer openId);

	public DataGrid dataGridAccount(LvVisit lvVisit, PageHelper ph);

}

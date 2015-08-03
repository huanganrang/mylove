package jb.service;

import java.util.List;

import jb.pageModel.LvArea;
import jb.pageModel.DataGrid;
import jb.pageModel.PageHelper;

/**
 * 
 * @author John
 * 
 */
public interface LvAreaServiceI {

	/**
	 * 获取LvArea数据表格
	 * 
	 * @param lvArea
	 *            参数
	 * @param ph
	 *            分页帮助类
	 * @return
	 */
	public DataGrid dataGrid(LvArea lvArea, PageHelper ph);

	/**
	 * 添加LvArea
	 * 
	 * @param lvArea
	 */
	public void add(LvArea lvArea);

	/**
	 * 获得LvArea对象
	 * 
	 * @param id
	 * @return
	 */
	public LvArea get(String id);

	/**
	 * 修改LvArea
	 * 
	 * @param lvArea
	 */
	public void edit(LvArea lvArea);

	/**
	 * 删除LvArea
	 * 
	 * @param id
	 */
	public void delete(String id);

	public List<LvArea> queryAllList(LvArea lvArea);

}

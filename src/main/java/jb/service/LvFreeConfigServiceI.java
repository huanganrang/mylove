package jb.service;

import java.util.List;

import jb.pageModel.LvFreeConfig;
import jb.pageModel.DataGrid;
import jb.pageModel.PageHelper;

/**
 * 
 * @author John
 * 
 */
public interface LvFreeConfigServiceI {

	/**
	 * 获取LvFreeConfig数据表格
	 * 
	 * @param lvFreeConfig
	 *            参数
	 * @param ph
	 *            分页帮助类
	 * @return
	 */
	public DataGrid dataGrid(LvFreeConfig lvFreeConfig, PageHelper ph);

	/**
	 * 添加LvFreeConfig
	 * 
	 * @param lvFreeConfig
	 */
	public void add(LvFreeConfig lvFreeConfig);

	/**
	 * 获得LvFreeConfig对象
	 * 
	 * @param id
	 * @return
	 */
	public LvFreeConfig get(String id);

	/**
	 * 修改LvFreeConfig
	 * 
	 * @param lvFreeConfig
	 */
	public void edit(LvFreeConfig lvFreeConfig);

	/**
	 * 删除LvFreeConfig
	 * 
	 * @param id
	 */
	public void delete(String id);

	public List<LvFreeConfig> findAllByParam(LvFreeConfig free);

	public int freeNumUsed(LvFreeConfig free);

}

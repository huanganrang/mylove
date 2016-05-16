package jb.service;

import jb.pageModel.LvPartnerCondition;
import jb.pageModel.DataGrid;
import jb.pageModel.PageHelper;

/**
 * 
 * @author John
 * 
 */
public interface LvPartnerConditionServiceI {

	/**
	 * 获取LvPartnerCondition数据表格
	 * 
	 * @param lvPartnerCondition
	 *            参数
	 * @param ph
	 *            分页帮助类
	 * @return
	 */
	public DataGrid dataGrid(LvPartnerCondition lvPartnerCondition, PageHelper ph);

	/**
	 * 添加LvPartnerCondition
	 * 
	 * @param lvPartnerCondition
	 */
	public void add(LvPartnerCondition lvPartnerCondition);

	/**
	 * 获得LvPartnerCondition对象
	 * 
	 * @param id
	 * @return
	 */
	public LvPartnerCondition get(String id);

	/**
	 * 修改LvPartnerCondition
	 * 
	 * @param lvPartnerCondition
	 */
	public void edit(LvPartnerCondition lvPartnerCondition);

	/**
	 * 删除LvPartnerCondition
	 * 
	 * @param id
	 */
	public void delete(String id);

	public LvPartnerCondition get(Integer openId);

	public void editByParam(LvPartnerCondition lvPartnerCondition) throws Exception ;

}

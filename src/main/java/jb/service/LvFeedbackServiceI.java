package jb.service;

import jb.pageModel.LvFeedback;
import jb.pageModel.DataGrid;
import jb.pageModel.PageHelper;

/**
 * 
 * @author John
 * 
 */
public interface LvFeedbackServiceI {

	/**
	 * 获取LvFeedback数据表格
	 * 
	 * @param lvFeedback
	 *            参数
	 * @param ph
	 *            分页帮助类
	 * @return
	 */
	public DataGrid dataGrid(LvFeedback lvFeedback, PageHelper ph);

	/**
	 * 添加LvFeedback
	 * 
	 * @param lvFeedback
	 */
	public void add(LvFeedback lvFeedback);

	/**
	 * 获得LvFeedback对象
	 * 
	 * @param id
	 * @return
	 */
	public LvFeedback get(String id);

	/**
	 * 修改LvFeedback
	 * 
	 * @param lvFeedback
	 */
	public void edit(LvFeedback lvFeedback);

	/**
	 * 删除LvFeedback
	 * 
	 * @param id
	 */
	public void delete(String id);

}
